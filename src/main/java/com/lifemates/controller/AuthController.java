package com.lifemates.controller;
import com.lifemates.dto.LoginRequest; // Importa la nueva clase
import com.lifemates.model.LoginAttempt;
import com.lifemates.service.LoginAttemptService;
import com.lifemates.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginAttemptService loginAttemptService;

    // Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request) {

        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        boolean success = false;

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            success = authentication.isAuthenticated();
            if (success) {
                loginAttemptService.registerLoginAttempt(loginRequest.getUsername(), true, ipAddress, userAgent);
                return ResponseEntity.ok("Inicio de sesión exitoso. Bienvenido, " + loginRequest.getUsername());
            }
        } catch (AuthenticationException e) {
            // Intento fallido
            loginAttemptService.registerLoginAttempt(loginRequest.getUsername(), false, ipAddress, userAgent);
            return ResponseEntity.status(401).body("Error de autenticación: " + e.getMessage());
        }

        // Si por algún motivo llega aquí, es un fallo
        loginAttemptService.registerLoginAttempt(loginRequest.getUsername(), false, ipAddress, userAgent);
        return ResponseEntity.status(401).body("Inicio de sesión fallido. Verifica tus credenciales.");
    }

    // Registrar un nuevo usuario (Solo para pruebas)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Set<String> roles) {
        try {
            userService.registerUser(username, email, password, roles);
            return ResponseEntity.ok("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener intentos de inicio de sesión (todos)
    @GetMapping("/login-attempts")
    public ResponseEntity<List<LoginAttempt>> getAllLoginAttempts() {
        return ResponseEntity.ok(loginAttemptService.getAllLoginAttempts());
    }
}

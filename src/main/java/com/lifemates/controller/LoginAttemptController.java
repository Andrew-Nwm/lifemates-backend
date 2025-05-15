package com.lifemates.controller;

import com.lifemates.model.LoginAttempt;
import com.lifemates.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login-attempts")
public class LoginAttemptController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    // Registrar un nuevo intento de inicio de sesión
    @PostMapping("/register")
    public ResponseEntity<?> registerLoginAttempt(
            @RequestParam String username,
            @RequestParam boolean success,
            @RequestParam String ipAddress,
            @RequestParam String userAgent) {

        loginAttemptService.registerLoginAttempt(username, success, ipAddress, userAgent);
        return ResponseEntity.ok("Intento de inicio de sesión registrado.");
    }

    // Obtener todos los intentos de inicio de sesión
    @GetMapping
    public ResponseEntity<List<LoginAttempt>> getAllLoginAttempts() {
        List<LoginAttempt> attempts = loginAttemptService.getAllLoginAttempts();
        return ResponseEntity.ok(attempts);
    }

    // Obtener intentos por nombre de usuario (filtros)
    @GetMapping("/by-username")
    public ResponseEntity<List<LoginAttempt>> getAttemptsByUsername(@RequestParam String username) {
        List<LoginAttempt> attempts = loginAttemptService.getAttemptsByUsername(username);
        return ResponseEntity.ok(attempts);
    }

    // Obtener intentos por dirección IP (filtros)
    @GetMapping("/by-ip")
    public ResponseEntity<List<LoginAttempt>> getAttemptsByIp(@RequestParam String ipAddress) {
        List<LoginAttempt> attempts = loginAttemptService.getAttemptsByIp(ipAddress);
        return ResponseEntity.ok(attempts);
    }
}

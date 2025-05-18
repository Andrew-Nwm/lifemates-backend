package com.lifemates.service;

import com.lifemates.model.LoginAttempt;
import com.lifemates.util.SnortLogWriter;
import com.lifemates.repository.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LoginAttemptService {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    // Registrar un nuevo intento de inicio de sesión

    public void registerLoginAttempt(String username, boolean success, String ipAddress, String userAgent) {
        LoginAttempt attempt = new LoginAttempt();
        attempt.setUsername(username);
        attempt.setSuccess(success);
        attempt.setIpAddress(ipAddress);
        attempt.setUserAgent(userAgent);
        attempt.setTimestamp(LocalDateTime.now());

        loginAttemptRepository.save(attempt);

        // Registrar también en el archivo .log monitoreado por Snort
        SnortLogWriter.logAttempt(username, success, ipAddress, userAgent);
    }


    // Obtener todos los intentos de inicio de sesión
    public List<LoginAttempt> getAllLoginAttempts() {
        return loginAttemptRepository.findAll();
    }

    // Obtener intentos por nombre de usuario
    public List<LoginAttempt> getAttemptsByUsername(String username) {
        return loginAttemptRepository.findByUsername(username);
    }

    // Obtener intentos por dirección IP
    public List<LoginAttempt> getAttemptsByIp(String ipAddress) {
        return loginAttemptRepository.findByIpAddress(ipAddress);
    }
}

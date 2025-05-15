package com.lifemates.service;

import com.lifemates.model.Alert;
import com.lifemates.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    // Registrar una alerta
    public Alert registerAlert(String type, String description, String ipAddress, String userAgent) {
        Alert alert = new Alert();
        alert.setType(type);
        alert.setDescription(description);
        alert.setIpAddress(ipAddress);
        alert.setUserAgent(userAgent);
        alert.setTimestamp(LocalDateTime.now());

        return alertRepository.save(alert);
    }

    // Obtener todas las alertas
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Obtener alertas por tipo
    public List<Alert> getAlertsByType(String type) {
        return alertRepository.findByType(type);
    }

    // Eliminar una alerta
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}

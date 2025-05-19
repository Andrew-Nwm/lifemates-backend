package com.lifemates.dto;

// No necesita ser una entidad, solo un POJO simple
public class LoginRequest {
    private String username;
    private String password;

    // Constructor vacío requerido para deserialización
    public LoginRequest() {}

    // Constructor con parámetros (opcional)
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
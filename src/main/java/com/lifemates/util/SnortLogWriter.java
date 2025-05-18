package com.lifemates.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SnortLogWriter {

    private static final String LOG_FILE_PATH = "/var/log/snort/lifemates_login.log";

    public static void logAttempt(String username, boolean success, String ip, String userAgent) {
        String timestamp = LocalDateTime.now().toString();
        String status = success ? "SUCCESS" : "FAILURE";
        String logEntry = String.format("[%s] LOGIN %s - user: %s - IP: %s - Agent: %s%n",
                timestamp, status, username, ip, userAgent);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to Snort log: " + e.getMessage());
        }
    }
}

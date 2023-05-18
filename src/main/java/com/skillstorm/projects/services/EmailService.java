package com.skillstorm.projects.services;

public interface EmailService {
    void sendEmailConfirmation(String recipientEmail, String subject, String content);
}
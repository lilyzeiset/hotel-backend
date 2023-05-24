package com.skillstorm.projects.services;

import com.skillstorm.projects.models.Reservation;

public interface EmailService {
    void sendEmailConfirmation(String recipientEmail, String subject, Reservation reservation);
}
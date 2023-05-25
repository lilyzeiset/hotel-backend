package com.skillstorm.projects.services;

import com.skillstorm.projects.dtos.ReservationDto;

public interface EmailService {
    void sendEmailConfirmation(String recipientEmail, String subject, ReservationDto reservationDto);
}
package com.skillstorm.projects.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.skillstorm.projects.models.Reservation;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailConfirmation(String recipientEmail, String subject, Reservation reservation) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            String content = buildEmailContent(reservation);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            // Handle exception
        }
    }
    
    private String buildEmailContent(Reservation reservation) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Dear ").append(reservation.getGuest().getName()).append(", your reservation has been confirmed.\n\n");
        contentBuilder.append("Reservation Details:\n");
        contentBuilder.append("Room Number: ").append(reservation.getRoom().getRoomNumber()).append("\n");
        contentBuilder.append("Check-in Date: ").append(reservation.getCheckInDate()).append("\n");
        contentBuilder.append("Check-out Date: ").append(reservation.getCheckOutDate()).append("\n");
        contentBuilder.append("No. of Guests: ").append(reservation.getNumberOfGuests()).append("\n");
        contentBuilder.append("Special Request: ").append(reservation.getSpecialRequests()).append("\n");

        return contentBuilder.toString();
    }
}

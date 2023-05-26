package com.skillstorm.projects.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.skillstorm.projects.dtos.ReservationDto;
import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.models.Room;
import com.skillstorm.projects.repositories.GuestRepository;
import com.skillstorm.projects.repositories.RoomRepository;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private GuestRepository guestRepository;
    
    @Autowired
    private RoomRepository roomRepository;

//    @Autowired
//    public EmailServiceImpl(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    @Override
    public void sendEmailConfirmation(String recipientEmail, String subject, ReservationDto reservationDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            String content = buildEmailContent(reservationDto);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            // Handle exception
        }
    }
    
    private String buildEmailContent(ReservationDto reservationDto) {
        StringBuilder contentBuilder = new StringBuilder();
        String newline = "<br />";
        
        Guest guest = guestRepository.findById(reservationDto.getGuestId()).orElse(null);
        Room room = roomRepository.findById(reservationDto.getRoomId()).orElse(null);
        
        contentBuilder.append("Dear ").append(guest != null ? guest.getName() : "Guest");
        contentBuilder.append(", your reservation has been confirmed.").append(newline).append(newline);
        contentBuilder.append("Reservation Details:").append(newline);
        contentBuilder.append("Room Number: ").append(room != null ? room.getRoomNumber() : "Room").append(newline);
        contentBuilder.append("Check-in Date: ").append(reservationDto.getCheckInDate()).append(newline);
        contentBuilder.append("Check-out Date: ").append(reservationDto.getCheckOutDate()).append(newline);
        contentBuilder.append("No. of Guests: ").append(reservationDto.getNumberOfGuests()).append(newline);
        contentBuilder.append("Special Request: ").append(reservationDto.getSpecialRequests()).append(newline);

        return contentBuilder.toString();
    }
}
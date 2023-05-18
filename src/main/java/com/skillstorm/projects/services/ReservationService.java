package com.skillstorm.projects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.projects.dtos.ReservationDto;
import com.skillstorm.projects.models.Reservation;
import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.models.Room;
import com.skillstorm.projects.repositories.GuestRepository;
import com.skillstorm.projects.repositories.ReservationRepository;
import com.skillstorm.projects.repositories.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService {

	@Autowired
    private ReservationRepository reservationRepository;
	
	@Autowired
    private GuestRepository guestRepository;
	
	@Autowired
    private RoomRepository roomRepository;
	
	private final EmailService emailService;

	public ReservationService(EmailService emailService) {
        this.emailService = emailService;
    }
	
    public ReservationDto createReservation(ReservationDto reservationData) {
        Guest guest = guestRepository.findById(reservationData.getGuestId())
                .orElseThrow(() -> new NoSuchElementException("Guest not found"));

        Room room = roomRepository.findById(reservationData.getRoomId())
                .orElseThrow(() -> new NoSuchElementException("Room not found"));

        Reservation reservation = new Reservation(
                guest,
                room,
                reservationData.getCheckInDate(),
                reservationData.getCheckOutDate(),
                reservationData.getNumberOfGuests(),
                reservationData.getSpecialRequests()
        );

        Reservation savedReservation = reservationRepository.save(reservation);
        
        // Send email confirmation
        String recipientEmail = guest.getEmail();
        String subject = "Reservation Confirmation";
        String content = "Dear " + guest.getName() + ", your reservation has been confirmed.";
        emailService.sendEmailConfirmation(recipientEmail, subject, content);

        return savedReservation.toDto();
    }
    
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found"));
        return reservation.toDto();
    }

    public ReservationDto updateReservation(Long id, ReservationDto reservationData) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found"));
        
        Guest guest = guestRepository.findById(reservationData.getGuestId())
                .orElseThrow(() -> new NoSuchElementException("Guest not found"));

        Room room = roomRepository.findById(reservationData.getRoomId())
                .orElseThrow(() -> new NoSuchElementException("Room not found"));
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationData.getCheckInDate());
        reservation.setCheckOutDate(reservationData.getCheckOutDate());
        reservation.setNumberOfGuests(reservationData.getNumberOfGuests());
        reservation.setSpecialRequests(reservationData.getSpecialRequests());

        Reservation updatedReservation = reservationRepository.save(reservation);
        
     // Send update confirmation
        String recipientEmail = guest.getEmail();
        String subject = "Reservation Updated";
        String content = "Dear " + guest.getName() + ", your reservation has been updated.";
        emailService.sendEmailConfirmation(recipientEmail, subject, content);
        
        return updatedReservation.toDto();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

package com.skillstorm.projects.services;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
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
                .orElseThrow();

        Room room = roomRepository.findById(reservationData.getRoomId())
                .orElseThrow();

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
                .toList();
    }

    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();
        return reservation.toDto();
    }

    public ReservationDto updateReservation(Long id, ReservationDto reservationData) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();
        
        Guest guest = guestRepository.findById(reservationData.getGuestId())
                .orElseThrow();

        Room room = roomRepository.findById(reservationData.getRoomId())
                .orElseThrow();
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationData.getCheckInDate());
        reservation.setCheckOutDate(reservationData.getCheckOutDate());
        reservation.setNumberOfGuests(reservationData.getNumberOfGuests());
        reservation.setSpecialRequests(reservationData.getSpecialRequests());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return updatedReservation.toDto();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

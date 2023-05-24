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
	
	
	/**
     * Retrieves all reservations for a given guest.
     *
     * @param guestId the ID of the guest
     * @return a list of reservations
     */
	public List<Reservation>getreservationsByGuestId(Long guestId){
		return reservationRepository.findByGuestId(guestId);
	}
	
	 /**
     * Creates a new reservation.
     *
     * @param reservationData the reservation data
     * @return the created reservation
     * @throws NoSuchElementException if guest or room is not found
     */
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
        emailService.sendEmailConfirmation(recipientEmail, subject, reservation);

        return savedReservation.toDto();
    }
    
    
    /**
     * Retrieves all reservations.
     *
     * @return a list of reservations
     */
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(Reservation::toDto)
                .collect(Collectors.toList());
    }

    
    /**
     * Retrieves a reservation by ID.
     *
     * @param id the ID of the reservation
     * @return the reservation
     * @throws NoSuchElementException if reservation is not found
     */
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found"));
        return reservation.toDto();
    }

    
    /**
     * Updates a reservation.
     *
     * @param the ID of the reservation
     * @param reservationData the updated reservation data
     * @return the updated reservation
     * @throws NoSuchElementException if reservation, guest, or room is not found
     */
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
        
     // Sends update confirmation
        String recipientEmail = guest.getEmail();
        String subject = "Reservation Updated";
        emailService.sendEmailConfirmation(recipientEmail, subject, reservation);
        
        return updatedReservation.toDto();
    }

    
    /**
     * Deletes a reservation.
     *
     * @param id the ID of the reservation to delete
     */
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

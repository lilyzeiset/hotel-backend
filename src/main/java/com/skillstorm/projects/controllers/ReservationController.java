package com.skillstorm.projects.controllers;

import com.skillstorm.projects.dtos.ReservationDto;
import com.skillstorm.projects.models.Reservation;
import com.skillstorm.projects.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
@Validated
public class ReservationController {

	@Autowired
    private ReservationService reservationService;

	
	/**
     * Create a new reservation.
     *
     * @param reservationDto The reservation data.
     * @return The created reservation.
     */
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody @Valid ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(createdReservation,HttpStatus.CREATED);
    }

    
    /**
     * Get all reservations.
     *
     * @return The list of all reservations.
     */
    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    
    /**
     * Get a reservation by ID.
     *
     * @param id The ID of the reservation.
     * @return The reservation with the given ID.
     */
    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable Long id) {
    	return reservationService.getReservationById(id);
    }

    
    /**
     * Get all reservations of a guest.
     *
     * @param guestId The ID of the guest.
     * @return The list of reservations for the guest.
     */
    @GetMapping("/guest/{guestId}")
    public List<Reservation> getReservationsByGuestId(@PathVariable Long guestId) {
    	return reservationService.getreservationsByGuestId(guestId);
    }
    
    /**
     * Update a reservation.
     *
     * @param id The ID of the reservation to update.
     * @param reservationDto The updated reservation data.
     * @return The updated reservation.
     */
    @PutMapping("/{id}")
    public ReservationDto updateReservation(@PathVariable Long id,@RequestBody @Valid ReservationDto reservationDto) {
    	return reservationService.updateReservation(id, reservationDto);
    }

    
    /**
     * Delete a reservation.
     *
     * @param id The ID of the reservation to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}


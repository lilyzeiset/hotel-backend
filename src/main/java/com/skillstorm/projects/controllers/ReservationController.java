package com.skillstorm.projects.controllers;

import com.skillstorm.projects.dtos.ReservationDto;
import com.skillstorm.projects.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
public class ReservationController {

	@Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody @Valid ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(createdReservation,HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable Long id) {
    	return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    public ReservationDto updateReservation(@PathVariable Long id,@RequestBody @Valid ReservationDto reservationDto) {
    	return reservationService.updateReservation(id, reservationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}


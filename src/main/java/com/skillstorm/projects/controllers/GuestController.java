package com.skillstorm.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.dtos.GuestDto;
import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.services.GuestService;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/guests")
@Validated
@CrossOrigin
public class GuestController {
	
	@Autowired
    private GuestService guestService;
	
	/**
	 * Performs authentication
	 * 
	 * @param principal
	 * @param auth
	 * @return The logged in user
	 */
	@PostMapping("/login")
	public ResponseEntity<GuestDto> login(Principal principal, Authentication auth) {
    	Guest user = (Guest) guestService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user.toDto());
	}

	/**
	 * Performs authentication
	 * 
	 * @param principal
	 * @param auth
	 * @return The logged in user
	 */
	@PostMapping("/login")
	public ResponseEntity<GuestDto> login(Principal principal, Authentication auth) {
    	Guest user = (Guest) guestService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user.toDto());
	}
	
	/**
     * Create a new guest.
     *
     * @param guestDto the guest data to create
     * @return ResponseEntity with the created guest
     */
    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody @Valid GuestDto guestDto) {
        GuestDto createdGuest = guestService.createGuest(guestDto);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    /**
     * Get all guests.
     *
     * @return the list of all guests
     */
    @GetMapping
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests();
    }

    /**
     * Get a guest by ID.
     *
     * @param id the ID of the guest
     * @return the guest with the specified ID
     */
    @GetMapping("/{id}")
    public GuestDto getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    /**
     * Update a guest.
     *
     * @param id       the ID of the guest to update
     * @param guestDto the updated guest data
     */
    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest(@PathVariable Long id, @RequestBody @Valid GuestDto guestDto) {
    	GuestDto updatedGuest = guestService.updateGuest(id, guestDto);
    	return ResponseEntity.ok(updatedGuest);
    }

    /**
     * Delete a guest.
     *
     * @param id the ID of the guest to delete
     */
    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }
}

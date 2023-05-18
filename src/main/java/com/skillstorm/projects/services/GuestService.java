package com.skillstorm.projects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skillstorm.projects.dtos.GuestDto;
import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.repositories.GuestRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class GuestService {

	@Autowired
    private GuestRepository guestRepository;

    public GuestDto createGuest(GuestDto guestData) {
    	// Checks if the email is already used
        if (guestRepository.existsByEmail(guestData.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Checks if the phone number is already used
        if (guestRepository.existsByPhoneNumber(guestData.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        Guest guest = new Guest(guestData.getId(), guestData.getName(), guestData.getEmail(), guestData.getPhoneNumber(), guestData.getAddress());
        return guestRepository.save(guest).toDto();
    }

    public List<GuestDto> getAllGuests() {
        return guestRepository.findAll()
        		.stream()
                .map(g -> g.toDto())
                .collect(Collectors.toList());
    }

    public GuestDto getGuestById(Long id) {
        return guestRepository.findById(id)
        		.orElseThrow(() -> new NoSuchElementException("Guest not found"))
        		.toDto();
    }

    public GuestDto updateGuest(Long id, GuestDto guestData) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Guest not found"));

        guest.setName(guestData.getName());
        guest.setEmail(guestData.getEmail());
        guest.setPhoneNumber(guestData.getPhoneNumber());
        guest.setAddress(guestData.getAddress());

        Guest updatedGuest = guestRepository.save(guest);
        return updatedGuest.toDto();
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}

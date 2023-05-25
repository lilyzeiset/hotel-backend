package com.skillstorm.projects.controllers;

import com.skillstorm.projects.dtos.RoomDto;
import com.skillstorm.projects.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {

	@Autowired
    private RoomService roomService;

	
	@GetMapping("/available")
	public ResponseEntity<List<RoomDto>> findAvailableRooms(
	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "numGuests", required = false) Integer numGuests,
	        @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
	        @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
	        @RequestParam(value = "numResultsPerPage") int numResultsPerPage,
	        @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {

	    List<RoomDto> availableRooms = roomService.findAvailableRooms(
	            startDate, endDate, numGuests, minPrice, maxPrice, numResultsPerPage, pageNumber);

	    return ResponseEntity.ok(availableRooms);
	}
	
	@GetMapping("/available/total")
	public ResponseEntity<Integer> findAvailableRoomsTotal(
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "numGuests", required = false) Integer numGuests,
	        @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
	        @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {
		
		return ResponseEntity.ok(roomService.findAvailableRoomsTotal(startDate, endDate, 0, minPrice, maxPrice));
		
	}

	@GetMapping("/available/total")
	public ResponseEntity<Integer> findAvailableRoomsTotal(
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	        @RequestParam(value = "numGuests", required = false) Integer numGuests,
	        @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
	        @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {
		
		return ResponseEntity.ok(roomService.findAvailableRoomsTotal(startDate, endDate, 0, minPrice, maxPrice));
		
	}
	
     /**
     * Creates a new room.
     *
     * @param roomDto the room data to create
     * @return the created room
     */
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomDto roomDto) {
        RoomDto createdRoom = roomService.createRoom(roomDto);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms
     */
    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    /**
     * Retrieves a room by ID.
     *
     * @param id the ID of the room to retrieve
     * @return the room with the given ID
     */
    @GetMapping("/{id}")
    public RoomDto getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    /**
     * Updates a room by ID.
     *
     * @param id       the ID of the room to update
     * @param roomDto  the updated room data
     * @return the updated room
     */
    @PutMapping("/{id}")
    public RoomDto updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDto roomDto) {
        return roomService.updateRoom(id, roomDto);
    }

    /**
     * Deletes a room by ID.
     *
     * @param id the ID of the room to delete
     * @return a response indicating the success of the operation
     */
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}

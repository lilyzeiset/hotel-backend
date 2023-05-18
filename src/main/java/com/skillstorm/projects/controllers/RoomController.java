package com.skillstorm.projects.controllers;

import com.skillstorm.projects.dtos.RoomDto;
import com.skillstorm.projects.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {

	@Autowired
    private RoomService roomService;

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

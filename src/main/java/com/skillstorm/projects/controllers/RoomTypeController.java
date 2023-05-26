package com.skillstorm.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.projects.dtos.RoomTypeDto;
import com.skillstorm.projects.services.RoomTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roomtypes")
@CrossOrigin
public class RoomTypeController {

	@Autowired
    private RoomTypeService roomTypeService;

	/**
     * Creates a new room type.
     *
     * @param roomTypeDto The room type data to create.
     * @return The created room type.
     */
    @PostMapping
    public ResponseEntity<RoomTypeDto> createRoomType(@Valid @RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto createdRoomType = roomTypeService.createRoomType(roomTypeDto);
        return new ResponseEntity<>(createdRoomType, HttpStatus.CREATED);
    }

    /**
     * Retrieves all room types.
     *
     * @return A list of all room types.
     */
    @GetMapping
    public List<RoomTypeDto> getAllRoomTypes() {
    	return roomTypeService.getAllRoomTypes();
    }

    /**
     * Retrieves a room type by ID.
     *
     * @param id The ID of the room type to retrieve.
     * @return The room type with the specified ID.
     */
    @GetMapping("/{id}")
    public RoomTypeDto getRoomTypeById(@PathVariable Long id) {
    	return roomTypeService.getRoomTypeById(id);
    }

    /**
     * Updates a room type.
     *
     * @param id          The ID of the room type to update.
     * @param roomTypeDto The updated room type data.
     * @return The updated room type.
     */
    @PutMapping("/{id}")
    public RoomTypeDto updateRoomType(@PathVariable Long id, @Valid @RequestBody RoomTypeDto roomTypeDto) {
    	return roomTypeService.updateRoomType(id, roomTypeDto);
    }

    /**
     * Deletes a room type by ID.
     *
     * @param id The ID of the room type to delete.
     * @return A response with no content.
     */
    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable Long id) {
        roomTypeService.deleteRoomType(id);
        }
}


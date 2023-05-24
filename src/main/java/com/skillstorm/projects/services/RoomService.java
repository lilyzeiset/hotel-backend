package com.skillstorm.projects.services;

import com.skillstorm.projects.dtos.RoomDto;
import com.skillstorm.projects.models.Room;
import com.skillstorm.projects.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Service
@Transactional
public class RoomService {

	@Autowired
    private RoomRepository roomRepository;

	
	/**
     * Find available rooms based on the search parameters.
     *
     * @param startDate         The start date of the search.
     * @param endDate           The end date of the search.
     * @param numGuests         The number of guests.
     * @param minPrice          The minimum nightly rate.
     * @param maxPrice          The maximum nightly rate.
     * @param numResultsPerPage The number of results per page.
     * @param pageNumber        The page number.
     * @return The list of available rooms.
     */
	public List<RoomDto> findAvailableRooms(
			@NotNull LocalDate startDate,
			@NotNull LocalDate endDate,
			@Min(1) int numGuests,
	        BigDecimal minPrice,
	        BigDecimal maxPrice,
	        int numResultsPerPage,
	        int pageNumber) {

	    List<Room> availableRooms = roomRepository.findAvailableRooms(
	            startDate, endDate, numGuests, minPrice, maxPrice,
	            PageRequest.of(pageNumber, numResultsPerPage));

	    List<RoomDto> availableRoomDtos = availableRooms.stream()
	            .map(Room::toDto)
	            .collect(Collectors.toList());

	    //return new PageImpl<>(availableRoomDtos, availableRooms.getPageable(), availableRooms.getTotalElements());
	    return availableRoomDtos;
	}
	
	public int findAvailableRoomsTotal(
			@NotNull LocalDate startDate,
			@NotNull LocalDate endDate,
			@Min(1) int numGuests,
	        BigDecimal minPrice,
	        BigDecimal maxPrice) {
		
		return roomRepository.findAvailableRoomsTotal(startDate, endDate, numGuests, minPrice, maxPrice);
	}


	/**
     * Create a new room.
     *
     * @param roomData The room data.
     * @return The created room.
     */	
	public RoomDto createRoom(RoomDto roomData) {
        Room room = new Room(roomData.getId(), roomData.getRoomType(), roomData.getRoomNumber(), roomData.getNightlyRate());
        return roomRepository.save(room).toDto();
    }

	/**
     * Get all rooms.
     *
     * @return The list of all rooms.
     */
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
    }

    
    /**
     * Get a room by ID.
     *
     * @param id The ID of the room.
     * @return The room with the given ID.
     * @throws NoSuchElementException if the room is not found.
     */
    public RoomDto getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found with id: " + id))
                .toDto();
    }

    
    /**
     * Update a room.
     *
     * @param id        The ID of the room to update.
     * @param roomData  The updated room data.
     * @return The updated room.
     * @throws IllegalArgumentException if the room is not found.
     */
    public RoomDto updateRoom(Long id, RoomDto roomData) {
        // Retrieve the existing Room entity from the database
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();

            // Update the properties of the existing Room with the data from the DTO
            room.setRoomType(roomData.getRoomType());
            room.setRoomNumber(roomData.getRoomNumber());
            room.setNightlyRate(roomData.getNightlyRate());

            // Save the updated Room entity
            Room updatedRoom = roomRepository.save(room);

            // Convert the updated Room entity to a DTO and return it
            return updatedRoom.toDto();
        } else {
            throw new IllegalArgumentException("Room not found");
        }
    }

    
    /**
     * Delete a room.
     *
     * @param id The ID of the room to delete.
     */
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}


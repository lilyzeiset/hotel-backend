package com.skillstorm.projects.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skillstorm.projects.dtos.RoomTypeDto;
import com.skillstorm.projects.models.RoomType;
import com.skillstorm.projects.repositories.RoomTypeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service
@Transactional
public class RoomTypeService {

	@Autowired
    private RoomTypeRepository roomTypeRepository;

    /**
     * Creates a new room type.
     *
     * @param roomTypeDto The room type data to create.
     * @return The created room type.
     */
    public RoomTypeDto createRoomType(@Valid RoomTypeDto roomTypeData) {
        RoomType roomType = new RoomType(roomTypeData.getId(), roomTypeData.getName(), roomTypeData.getDescription(), roomTypeData.getMaxOccupancy());
        return roomTypeRepository.save(roomType).toDto();
    }

    /**
     * Retrieves all room types.
     *
     * @return A list of all room types.
     */
    public List<RoomTypeDto> getAllRoomTypes() {
    	return roomTypeRepository.findAll()
    			.stream()
    			.map(RoomType::toDto)
    			.collect(Collectors.toList());
    }

    /**
     * Retrieves a room type by ID.
     *
     * @param id The ID of the room type to retrieve.
     * @return The room type with the specified ID.
     */
    public RoomTypeDto getRoomTypeById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id)
        		.orElseThrow(() -> new NoSuchElementException("Room type not found with id: " + id));
        return roomType.toDto();
    }

    /**
     * Updates a room type.
     *
     * @param id          The ID of the room type to update.
     * @param roomTypeDto The updated room type data.
     * @return The updated room type.
     */
    public RoomTypeDto updateRoomType(Long id, @Valid RoomTypeDto roomTypeData) {
        // Retrieve the existing RoomType entity from the database
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);
        if (optionalRoomType.isPresent()) {
            RoomType roomType = optionalRoomType.get();

            // Update the properties of the existing RoomType with the data from the DTO
            roomType.setName(roomTypeData.getName());
            roomType.setDescription(roomTypeData.getDescription());
            roomType.setMaxOccupancy(roomTypeData.getMaxOccupancy());

            // Save the updated RoomType entity
            RoomType updatedRoomType = roomTypeRepository.save(roomType);

            // Convert the updated RoomType entity to a DTO and return it
            return updatedRoomType.toDto();
        } else {
            throw new IllegalArgumentException("RoomType not found");
        }
    }


    /**
     * Deletes a room type by ID.
     *
     * @param id The ID of the room type to delete.
     */
    public void deleteRoomType(Long id) {
        roomTypeRepository.deleteById(id);
    }
}

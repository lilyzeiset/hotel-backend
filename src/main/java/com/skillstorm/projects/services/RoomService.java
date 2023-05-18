package com.skillstorm.projects.services;

import com.skillstorm.projects.dtos.RoomDto;
import com.skillstorm.projects.models.Room;
import com.skillstorm.projects.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class RoomService {

	@Autowired
    private RoomRepository roomRepository;

    public RoomDto createRoom(RoomDto roomData) {
        Room room = new Room(roomData.getId(), roomData.getRoomType(), roomData.getRoomNumber(), roomData.getNightlyRate());
        return roomRepository.save(room).toDto();
    }

    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(Room::toDto)
                .collect(Collectors.toList());
    }

    public RoomDto getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found with id: " + id))
                .toDto();
    }

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

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}


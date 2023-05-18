package com.skillstorm.projects.models;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.skillstorm.projects.dtos.RoomDto;


@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull(message = "Room type is required")
    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @NotBlank(message = "Room number is required")
    @Size(max = 10)
    @Column(name = "roomnumber", unique = true)	
    private String roomNumber;

    @NotNull(message = "Nightly rate is required")
    @Positive(message = "Nightly rate must be positive")
    @Column(name = "nightlyrate")
    private BigDecimal nightlyRate;
    
    public Room() {
		super();
	} 
    
    /**
     * Creates a new Room object.
     *
     * @param roomType    The room type of the room.
     * @param roomNumber  The room number of the room.
     * @param nightlyRate The nightly rate of the room.
     */
    public Room(Long id, RoomType roomType, String roomNumber, BigDecimal nightlyRate) {
    	this.id = id;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.nightlyRate = nightlyRate;
    }
    
    public RoomDto toDto() {
    	return new RoomDto(id, roomType, roomNumber, nightlyRate);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public BigDecimal getNightlyRate() {
		return nightlyRate;
	}

	public void setNightlyRate(BigDecimal nightlyRate) {
		this.nightlyRate = nightlyRate;
	}
    
    
    
}


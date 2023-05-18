package com.skillstorm.projects.dtos;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import com.skillstorm.projects.models.RoomType;


public class RoomDto {

    private Long id;

//    @Positive
//    private RoomType roomTypeId;

    @NotBlank
    @Size(max = 10)
    private String roomNumber;

    @Positive
    @NotNull
    private BigDecimal nightlyRate;
    
    @Autowired
    private RoomType roomType;

    /**
     * Constructs a new RoomDto with the given properties.
     *
     * @param roomTypeId   the ID of the room type
     * @param roomNumber   the room number
     * @param nightlyRate  the nightly rate
     */
    public RoomDto(Long id, RoomType roomType, String roomNumber, BigDecimal nightlyRate) {
    	this.id = id;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.nightlyRate = nightlyRate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public RoomType getRoomTypeId() {
//		return roomTypeId;
//	}
//
//	public void setRoomTypeId(RoomType roomTypeId) {
//		this.roomTypeId = roomTypeId;
//	}

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

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
    
    
}

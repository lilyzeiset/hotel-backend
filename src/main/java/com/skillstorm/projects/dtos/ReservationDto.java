package com.skillstorm.projects.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillstorm.projects.models.Guest;
import com.skillstorm.projects.models.Room;

public class ReservationDto {

    private Long id;
    
    @JsonIgnore
    private Long guestId; 
    
    @JsonIgnore
    private Long roomId;
    
    @NotNull
    private Guest guest;

    @NotNull
    private Room room;

    @NotNull
    private LocalDate checkInDate;

    @NotNull
    private LocalDate checkOutDate;

    @Positive
    @Min(1)
    private int numberOfGuests;

    @NotEmpty
    private String specialRequests;

    /**
     * Creates a new ReservationDto object.
     *
     * @param id              The ID of the reservation.
     * @param guest           The guest associated with the reservation.
     * @param room            The room associated with the reservation.
     * @param checkInDate     The check-in date of the reservation.
     * @param checkOutDate    The check-out date of the reservation.
     * @param numberOfGuests  The number of guests for the reservation.
     * @param specialRequests The special requests for the reservation.
     */
    public ReservationDto(Long id,Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate,
        int numberOfGuests, String specialRequests) {
        this.id = id;
        this.guestId = guest.getId();
        this.roomId = room.getId();
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.specialRequests = specialRequests;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public String getSpecialRequests() {
		return specialRequests;
	}

	public void setSpecialRequests(String specialRequests) {
		this.specialRequests = specialRequests;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
    
}


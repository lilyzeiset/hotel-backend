package com.skillstorm.projects.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class ReservationDto {

    private Long id;
    
    private Long guestId; 
    
    private Long roomId;
    
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
    public ReservationDto(Long id,Long guestId, Long roomId, LocalDate checkInDate, LocalDate checkOutDate,
        int numberOfGuests, String specialRequests) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.specialRequests = specialRequests;
    }
    
    
	public ReservationDto() {
		super();
	}

	public ReservationDto(Long guestId, Long roomId, @NotNull LocalDate checkInDate, @NotNull LocalDate checkOutDate,
			@Positive @Min(1) int numberOfGuests, @NotEmpty String specialRequests) {
		super();
		this.guestId = guestId;
		this.roomId = roomId;
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


	@Override
	public String toString() {
		return "ReservationDto [id=" + id + ", guestId=" + guestId + ", roomId=" + roomId + ", checkInDate="
				+ checkInDate + ", checkOutDate=" + checkOutDate + ", numberOfGuests=" + numberOfGuests
				+ ", specialRequests=" + specialRequests + "]";
	}
    
}


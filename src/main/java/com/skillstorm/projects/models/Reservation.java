package com.skillstorm.projects.models;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.skillstorm.projects.dtos.ReservationDto;
import java.time.LocalDate;


@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    @NotNull(message = "Guest is required")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @NotNull(message = "Room is required")
    private Room room;

    @Column(name = "checkindate")
    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date must be in the present or future")
    private LocalDate checkInDate;

    @Column(name = "checkoutdate")
    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOutDate;

    @Column(name = "numberofguests")
    @NotNull(message = "Number of guests is required")
    @Min(value = 1, message = "Number of guests must be at least 1")
    private int numberOfGuests;

    @Column(name = "specialrequests", length = 1000)
    @Size(max = 1000, message = "Special requests must not exceed 1000 characters")
    private String specialRequests;

    /**
     * Converts the Reservation entity to a ReservationDto object.
     *
     * @return The ReservationDto object.
     */
    public ReservationDto toDto() {
        return new ReservationDto(id, guest, room, checkInDate, checkOutDate, numberOfGuests, specialRequests);
    }
    
    public Reservation() {}
    
    public Reservation(Long id, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, String specialRequests) {
        this.id = id;
    	this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.specialRequests = specialRequests;
    }

    public Reservation(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuests, String specialRequests) {
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
    
    
}

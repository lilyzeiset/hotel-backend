package com.skillstorm.projects.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) class for Guest.
 * Represents the guest details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    /**
     * The ID of the guest.
     */
	private Long id;

    /**
     * The name of the guest.
     */
	@NotBlank
    @Size(max = 50)
    private String name;

    /**
     * The email address of the guest.
     */
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255)
    private String email;

    /**
     * The phone number of the guest.
     */
	@NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    /**
     * The address of the guest.
     */
	@NotBlank
    @Size(max = 255)
    private String address;
    
    public GuestDto(Long id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
	    
}

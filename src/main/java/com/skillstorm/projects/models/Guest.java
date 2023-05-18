package com.skillstorm.projects.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.skillstorm.projects.dtos.GuestDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "guest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "phonenumber", unique = true)
    private String phoneNumber;

    @NotBlank
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    
    public GuestDto toDto() {
    	return new GuestDto(id, name, email, phoneNumber, address);
    }

    public Guest() {}
    
	public Guest(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Email @Size(max = 255) String email,
			@NotBlank @Size(max = 20) String phoneNumber, @NotBlank @Size(max = 255) String address) {
		super();
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
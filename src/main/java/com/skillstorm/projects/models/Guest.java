package com.skillstorm.projects.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.skillstorm.projects.dtos.GuestDto;


@Entity
@Table(name = "guest")
public class Guest implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    @NotBlank
    @Size(min = 8, max = 100)
    @Column(name = "password")
    private String password;
    
    public GuestDto toDto() {
    	return new GuestDto(id, name, email, phoneNumber, address, password);
    }

    public Guest() {}
    
	public Guest(Long id, @NotBlank @Size(max = 50) String name, @NotBlank @Email @Size(max = 255) String email,
			@NotBlank @Size(max = 20) String phoneNumber, @NotBlank @Size(max = 255) String address, @NotBlank @Size(min = 8, max = 100) String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
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
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
    
}
package com.skillstorm.projects.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.skillstorm.projects.dtos.RoomTypeDto;
import lombok.*;

@Entity
@Table(name = "roomtype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @Size(max = 1000)
    @Column(name = "description")
    private String description;

    @NotNull(message = "Max occupancy is required")
    @Positive(message = "Max occupancy must be a positive value")
    @Column(name = "maxoccupancy")
    private Integer maxOccupancy;

    public RoomType() {}
   
    public RoomType(Long id, String name, String description, Integer maxOccupancy) {
    	this.id = id;
        this.name = name;
        this.description = description;
        this.maxOccupancy = maxOccupancy;
    }
    
    public RoomTypeDto toDto() {
    	return new RoomTypeDto(id, name, description, maxOccupancy);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
    
    
}


package com.skillstorm.projects.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDto {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Positive
    private Integer maxOccupancy;

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

	public RoomTypeDto(Long id, @NotBlank @Size(max = 50) String name, @Size(max = 1000) String description,
			@NotNull @Positive Integer maxOccupancy) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.maxOccupancy = maxOccupancy;
	}

    
}


package com.skillstorm.projects.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillstorm.projects.models.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{

	boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    
    
}

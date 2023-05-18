package com.skillstorm.projects.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.skillstorm.projects.models.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	List<Reservation> findByGuestId(Long guestId);
	
	@Query("SELECT r FROM Reservation r WHERE r.checkInDate > CURRENT_DATE")
    List<Reservation> findFutureReservations();
	
}

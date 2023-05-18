package com.skillstorm.projects.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.projects.models.Room;
import com.skillstorm.projects.models.RoomType;

public interface RoomRepository extends JpaRepository<Room, Long>{

	List<Room> findByRoomType(RoomType roomType);
	List<Room> findByNightlyRateBetween(BigDecimal minRate, BigDecimal maxRate);
	
	@Query("SELECT r FROM Room r LEFT JOIN Reservation res WHERE (res.checkInDate > :endDate OR res.checkOutDate < :startDate OR res IS NULL)")
    List<Room> findAvailableRoomsForDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query("SELECT r FROM Room r JOIN RoomType rt LEFT JOIN Reservation res WHERE (res.checkInDate <= :endDate AND res.checkOutDate >= :startDate) OR res IS NULL AND rt.maxOccupancy >= :numGuests")
	List<Room> findAvailableRooms(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("numGuests") int numGuests);

}

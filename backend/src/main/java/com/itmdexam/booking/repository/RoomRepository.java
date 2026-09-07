package com.itmdexam.booking.repository;

import com.itmdexam.booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

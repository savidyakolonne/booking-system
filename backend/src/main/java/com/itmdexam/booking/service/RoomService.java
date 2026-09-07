package com.itmdexam.booking.service;

import com.itmdexam.booking.dto.RoomRequest;
import com.itmdexam.booking.entity.Room;
import com.itmdexam.booking.exception.ResourceNotFoundException;
import com.itmdexam.booking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){

        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    public Room createRoom(RoomRequest request){

        Room room = new Room();

        room.setName(request.getName());
        room.setCapacity(request.getCapacity());
        room.setLocation(request.getLocation());
        room.setAvailable(request.getAvailable());

        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, RoomRequest request){

        Room room = getRoomById(id);

        room.setName(request.getName());
        room.setCapacity(request.getCapacity());
        room.setLocation(request.getLocation());
        room.setAvailable(request.getAvailable());

        return roomRepository.save(room);
    }

    public void deleteRoom(Long id){

        Room room = getRoomById(id);

        roomRepository.delete(room);
    }
}
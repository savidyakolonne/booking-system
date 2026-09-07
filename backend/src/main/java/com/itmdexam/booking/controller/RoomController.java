package com.itmdexam.booking.controller;

import com.itmdexam.booking.dto.RoomRequest;
import com.itmdexam.booking.entity.Room;
import com.itmdexam.booking.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(
            @Valid @RequestBody RoomRequest request){

        return roomService.createRoom(request);
    }

    @PutMapping("/{id}")
    public Room updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request){

        return roomService.updateRoom(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

}

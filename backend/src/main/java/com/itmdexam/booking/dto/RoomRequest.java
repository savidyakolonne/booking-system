package com.itmdexam.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RoomRequest {

    @NotBlank(message = "Room name is required")
    private String name;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;

    private String location;

    @NotNull(message = "Availability is required")
    private Boolean available;

    public RoomRequest(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getCapacity(){
        return capacity;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public Boolean getAvailable(){
        return available;
    }

    public void setAvailable(Boolean available){
        this.available = available;
    }
}

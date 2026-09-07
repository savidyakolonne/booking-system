package com.itmdexam.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingResponse {

    private Long id;

    private Long roomId;
    private String roomName;

    private Long employeeId;
    private String employeeName;

    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String purpose;

    public BookingResponse() {
    }

    public BookingResponse(
            Long id,
            Long roomId,
            String roomName,
            Long employeeId,
            String employeeName,
            LocalDate bookingDate,
            LocalTime startTime,
            LocalTime endTime,
            String purpose
    ) {
        this.id = id;
        this.roomId = roomId;
        this.roomName = roomName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
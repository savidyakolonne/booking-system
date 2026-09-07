package com.itmdexam.booking.service;

import com.itmdexam.booking.dto.BookingResponse;
import com.itmdexam.booking.entity.Booking;
import com.itmdexam.booking.entity.Employee;
import com.itmdexam.booking.entity.Room;
import com.itmdexam.booking.exception.BusinessException;
import com.itmdexam.booking.exception.ResourceNotFoundException;
import com.itmdexam.booking.repository.BookingRepository;
import com.itmdexam.booking.repository.EmployeeRepository;
import com.itmdexam.booking.repository.RoomRepository;
import com.itmdexam.booking.dto.BookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            RoomRepository roomRepository,
            EmployeeRepository employeeRepository
    ){
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.employeeRepository = employeeRepository;
    }

    // get all bookings
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // get booking by id
    public Booking getBookingById(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found"));
    }

    public BookingResponse getBookingResponseById(Long id) {

        Booking booking = getBookingById(id);

        return mapToResponse(booking);
    }

    // create booking
    public BookingResponse createBooking(BookingRequest request){

        // find room
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        // find employee
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // check room availability
        if(!room.getAvailable()){
            throw new BusinessException("Room is not available");
        }

        // check time availability
        if(!request.getStartTime().isBefore(request.getEndTime())){
            throw new BusinessException("Start time must be before end time");
        }

        // check overlapping booking
        boolean overlapping =
                bookingRepository.existsOverlappingBooking(
                        room,
                        request.getBookingDate(),
                        request.getStartTime(),
                        request.getEndTime()
                );

        if(overlapping){
            throw new BusinessException(
                    "Room is already booked for this time period"
            );
        }

        Booking booking = new Booking();

        booking.setRoom(room);
        booking.setEmployee(employee);
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setPurpose(request.getPurpose());

        Booking savedBooking = bookingRepository.save(booking);

        return mapToResponse(savedBooking);
    }

    // update booking
    public BookingResponse updateBooking(Long id, BookingRequest request) {

        Booking booking = getBookingById(id);

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (!room.getAvailable()) {
            throw new BusinessException("Room is not available");
        }

        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new BusinessException(
                    "Start time must be before end time"
            );
        }

        boolean overlapping =
                bookingRepository.existsOverlappingBookingForUpdate(
                        room,
                        request.getBookingDate(),
                        request.getStartTime(),
                        request.getEndTime(),
                        id
                );

        if (overlapping) {
            throw new BusinessException(
                    "Room is already booked for this time period"
            );
        }

        booking.setRoom(room);
        booking.setEmployee(employee);
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setPurpose(request.getPurpose());

        Booking savedBooking = bookingRepository.save(booking);

        return mapToResponse(savedBooking);
    }

    // delete booking
    public void deleteBooking(Long id){

        Booking booking = getBookingById(id);

        bookingRepository.delete(booking);
    }

    private BookingResponse mapToResponse(Booking booking){
        return new BookingResponse(
                booking.getId(),

                booking.getRoom().getId(),
                booking.getRoom().getName(),

                booking.getEmployee().getId(),
                booking.getEmployee().getName(),

                booking.getBookingDate(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getPurpose()
        );
    }
}

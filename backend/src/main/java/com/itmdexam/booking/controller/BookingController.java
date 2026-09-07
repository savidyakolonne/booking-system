package com.itmdexam.booking.controller;

import com.itmdexam.booking.dto.BookingRequest;
import com.itmdexam.booking.dto.BookingResponse;
import com.itmdexam.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingResponseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(
            @Valid @RequestBody BookingRequest request
    ) {
        return bookingService.createBooking(request);
    }

    @PutMapping("/{id}")
    public BookingResponse updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequest request
    ) {
        return bookingService.updateBooking(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
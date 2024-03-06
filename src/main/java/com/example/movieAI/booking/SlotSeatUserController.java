package com.example.movieAI.booking;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slot-seat")
public class SlotSeatUserController {

    private final SlotSeatUserService slotSeatUserService;

    @Autowired
    public SlotSeatUserController(SlotSeatUserService slotSeatUserService) {
        this.slotSeatUserService = slotSeatUserService;
    }

    @GetMapping("/booked-seats/{movieTimeslotId}")
    public List<String> getBookedSeats(@PathVariable Long movieTimeslotId) {
        return slotSeatUserService.getBookedSeatsByMovieTimeslotId(movieTimeslotId);
    }

    @GetMapping("/holding-seats/{movieTimeslotId}")
    public List<String> getHoldingSeats(@PathVariable Long movieTimeslotId) {
        return slotSeatUserService.getHoldingSeatsByMovieTimeslotId(movieTimeslotId);
    }

    @GetMapping("/booked-or-holding-seats")
    public List<SlotSeatUserEntity> getBookedOrHoldingSeats(@RequestParam Long movieTimeslotId, @RequestParam String seatId) {
        return slotSeatUserService.findBookedOrHoldingSeats(movieTimeslotId, seatId);
    }

    @PostMapping("/create-holding-seat")
    public SlotSeatUserEntity createHoldingSeat(@RequestParam Long movieTimeslotId, @RequestParam String seatId, @RequestParam Long userId) {

        return slotSeatUserService.createHoldingSeat(movieTimeslotId, seatId, userId);
    }

    @DeleteMapping("/delete-seat")
    public void deleteSeat(@RequestParam Long movieTimeslotId, @RequestParam Long userId, @RequestParam String seatId) {
        slotSeatUserService.deleteSlotSeatUser(movieTimeslotId, userId, seatId);
    }

    @GetMapping("/user-bookings/{userId}")
    public List<String> getUserBookings(@PathVariable Long userId) {
        return slotSeatUserService.getUserBookings(userId);
    }

    @PostMapping("/update-seat-status-to-booked")
    public ResponseEntity<String> updateSeatStatusToBooked(
            @RequestParam Long movieTimeslotId,
            @RequestParam Long userId,
            @RequestParam String seatId) {
        try {
            slotSeatUserService.updateSeatStatusToBooked(movieTimeslotId, userId, seatId);
            return ResponseEntity.ok("Seat status updated to booked successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}

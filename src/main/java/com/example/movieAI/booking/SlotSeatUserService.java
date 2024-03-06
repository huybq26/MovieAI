package com.example.movieAI.booking;

import com.example.movieAI.auth.UserEntity;
import com.example.movieAI.auth.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class SlotSeatUserService {

    private final SlotSeatUserRepository slotSeatUserRepository;
    private final MovieTimeslotRepository movieTimeslotRepository;
    private final UserRepository userRepository;

    @Autowired
    public SlotSeatUserService(SlotSeatUserRepository slotSeatUserRepository,
                               MovieTimeslotRepository movieTimeslotRepository,
                               UserRepository userRepository) {
        this.slotSeatUserRepository = slotSeatUserRepository;
        this.movieTimeslotRepository = movieTimeslotRepository;
        this.userRepository = userRepository;
    }

    public List<String> getBookedSeatsByMovieTimeslotId(Long movieTimeslotId) {
        return slotSeatUserRepository.findBookedSeatsByMovieTimeslotId(movieTimeslotId);
    }

    public List<String> getHoldingSeatsByMovieTimeslotId(Long movieTimeslotId) {
        return slotSeatUserRepository.findHoldingSeatsByMovieTimeslotId(movieTimeslotId);
    }

    public List<SlotSeatUserEntity> findBookedOrHoldingSeats(Long movieTimeslotId, String seatId) {
        return slotSeatUserRepository.findBookedOrHoldingSeats(movieTimeslotId, seatId);
    }

    public void updateSeatStatusToBooked(Long movieTimeslotId, Long userId, String seatId) {
        slotSeatUserRepository.updateSeatStatusToBooked(movieTimeslotId, userId, seatId);
    }

    public SlotSeatUserEntity createHoldingSeat(Long movieTimeslotId, String seatId, Long userId) {
        SlotSeatUserEntity slotSeatUserEntity = new SlotSeatUserEntity();

        MovieTimeslotEntity movieTimeslotEntity = movieTimeslotRepository.findById(movieTimeslotId)
                .orElseThrow(() -> new EntityNotFoundException("MovieTimeslotEntity not found with id: " + movieTimeslotId));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserEntity not found with id: " + userId));

        slotSeatUserEntity.setMovieTimeSlot(movieTimeslotEntity);
        slotSeatUserEntity.setUser(userEntity);

        slotSeatUserEntity.setSeatId(seatId);
        slotSeatUserEntity.setStatus(SlotSeatUserEntity.SeatStatus.HOLDING);

        return slotSeatUserRepository.save(slotSeatUserEntity);
    }


    public void deleteSlotSeatUser(Long movieTimeslotId, Long userId, String seatId) {
        slotSeatUserRepository.deleteByMovieTimeslotIdAndUserIdAndSeatId(movieTimeslotId, userId, seatId);
    }

    public List<String> getUserBookings(Long userId) {
        return slotSeatUserRepository.findUserBookings(userId);
    }
}

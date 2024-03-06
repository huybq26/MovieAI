package com.example.movieAI.booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotSeatUserRepository extends JpaRepository<SlotSeatUserEntity, Long> {
    @Query("SELECT s.seatId FROM SlotSeatUserEntity s WHERE s.status = 'booked' AND s.movieTimeSlot.id = :movieTimeslotId")
    List<String> findBookedSeatsByMovieTimeslotId(@Param("movieTimeslotId") Long movieTimeslotId);

    @Query("SELECT s.seatId FROM SlotSeatUserEntity s WHERE s.status = 'holding' AND s.movieTimeSlot.id = :movieTimeslotId")
    List<String> findHoldingSeatsByMovieTimeslotId(@Param("movieTimeslotId") Long movieTimeslotId);

    @Query("SELECT s FROM SlotSeatUserEntity s WHERE (s.status = 'booked' OR s.status = 'holding') AND s.movieTimeSlot.id = :movieTimeslotId AND s.seatId = :seatId")
    List<SlotSeatUserEntity> findBookedOrHoldingSeats(
            @Param("movieTimeslotId") Long movieTimeslotId,
            @Param("seatId") String seatId
    );

    @Transactional
    @Modifying
    @Query("UPDATE SlotSeatUserEntity s SET s.status = 'booked' WHERE s.movieTimeSlot.id = :movieTimeslotId AND s.user.id = :userId AND s.seatId = :seatId")
    void updateSeatStatusToBooked(@Param("movieTimeslotId") Long movieTimeslotId, @Param("userId") Long userId, @Param("seatId") String seatId);

    @Transactional
    @Modifying
    @Query("DELETE FROM SlotSeatUserEntity s WHERE s.movieTimeSlot.id = :movieTimeslotId AND s.user.id = :userId AND s.seatId = :seatId")
    void deleteByMovieTimeslotIdAndUserIdAndSeatId(@Param("movieTimeslotId") Long movieTimeslotId, @Param("userId") Long userId, @Param("seatId") String seatId);

    @Query("SELECT CONCAT(m.name, ' - ', t.time, ' - ', ssu.seatId) " +
            "FROM SlotSeatUserEntity ssu " +
            "INNER JOIN ssu.movieTimeSlot mt " +
            "INNER JOIN mt.movie m " +
            "INNER JOIN mt.timeSlot t " +
            "WHERE ssu.user.id = :userId")
    List<String> findUserBookings(@Param("userId") Long userId);
}

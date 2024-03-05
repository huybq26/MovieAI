package com.example.movieAI.booking;

import com.example.movieAI.auth.UserEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "slot_seat_user")
public class SlotSeatUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "movie_timeslot_id", referencedColumnName = "id")
    private MovieTimeslotEntity movieTimeSlot;

    @Column(name = "seat_id")
    private String seatId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SeatStatus status;

    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp time;



    public enum SeatStatus {
        AVAIL, HOLDING, BOOKED
    }
}

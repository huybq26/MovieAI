package com.example.movieAI.booking;

import com.example.movieAI.film_management.MovieEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "timeslot")
public class TimeslotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private TheaterEntity theater;

    @Column(name = "type", nullable = false, length = 2)
    private String type;

    @Column(name = "time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @OneToMany(mappedBy = "timeSlot")
    private Set<MovieTimeslotEntity> movieTimeSlots;

    public TimeslotEntity() {
    }

    public TimeslotEntity(TheaterEntity theater, String type, Date time) {
        this.theater = theater;
        this.type = type;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TheaterEntity getTheater() {
        return theater;
    }

    public void setTheater(TheaterEntity theater) {
        this.theater = theater;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

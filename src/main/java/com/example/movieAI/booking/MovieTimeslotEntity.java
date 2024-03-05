package com.example.movieAI.booking;

import com.example.movieAI.film_management.MovieEntity;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movie_timeslot")
public class MovieTimeslotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private TimeslotEntity timeSlot;

    @OneToMany(mappedBy = "movieTimeSlot", cascade = CascadeType.ALL)
    private Set<SlotSeatUserEntity> slotSeatUsers;

    public MovieTimeslotEntity() {
    }

    public MovieTimeslotEntity(MovieEntity movie, TimeslotEntity timeSlot, Set<SlotSeatUserEntity> slotSeatUsers) {
        this.movie = movie;
        this.timeSlot = timeSlot;
        this.slotSeatUsers = slotSeatUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public TimeslotEntity getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeslotEntity timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Set<SlotSeatUserEntity> getSlotSeatUsers() {
        return slotSeatUsers;
    }

    public void setSlotSeatUsers(Set<SlotSeatUserEntity> slotSeatUsers) {
        this.slotSeatUsers = slotSeatUsers;
    }
}

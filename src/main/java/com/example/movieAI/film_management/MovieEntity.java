package com.example.movieAI.film_management;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name="Movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "movie_id_seq")
    @SequenceGenerator(name = "movie_id_seq", sequenceName = "movie_id_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String image;

    @Column(nullable = false, length = 255)
    private String trailer;



    @Column(nullable = false, length = 100)
    private String languages;

    @Column(nullable = false)
    private LocalDate release_date;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private int is_current;

    @Column(nullable = false)
    private float rating;

    private int running_time;



    public MovieEntity(String name,
                       String image,
                       String trailer,
                       String languages,
                       LocalDate release_date,
                       String description,
                       int is_current,
                       float rating,
                       int running_time) {
        this.name = name;
        this.image = image;
        this.trailer = trailer;
        this.languages = languages;
        this.release_date = release_date;
        this.description = description;
        this.is_current = is_current;
        this.rating = rating;
        this.running_time = running_time;
    }

    public MovieEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_current() {
        return is_current;
    }

    public void setIs_current(int is_current) {
        this.is_current = is_current;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRunning_time() {
        return running_time;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", trailer='" + trailer + '\'' +
                ", languages='" + languages + '\'' +
                ", release_date=" + release_date +
                ", description='" + description + '\'' +
                ", is_current=" + is_current +
                ", rating=" + rating +
                ", running_time=" + running_time +
                '}';
    }
}
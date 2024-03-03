package com.example.movieAI.film_management;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
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

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<ActorEntity> actors;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<DirectorEntity> directors;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<TagEntity> tags;



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

    public String getRelease_date() {
        return String.valueOf(release_date);
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

    public String getActor() {
        return actors.stream()
                .map(ActorEntity::getName)
                .collect(Collectors.joining(","));
    }

    public String getDirector() {
        return directors.stream()
                .map(DirectorEntity::getName)
                .collect(Collectors.joining(","));
    }

    public String getTag() {
        return tags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.joining(","));
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
                ", actors=" + actors +
                ", directors=" + directors +
                ", tags=" + tags +
                '}';
    }
}
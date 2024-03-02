package com.example.movieAI.film_management;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "Actor")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<MovieEntity> movies;

    @Column(nullable = false, length = 255)
    private String name;

    @Column
    private LocalDate birthday;

    @Column(length = 50)
    private String country;

    @Column
    private String shortDesc;

    @Column(length = 255)
    private String Image;

    public ActorEntity(String name, LocalDate birthday, String country, String shortDesc, String image) {
        this.name = name;
        this.birthday = birthday;
        this.country = country;
        this.shortDesc = shortDesc;
        Image = image;
    }

    public ActorEntity() {

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ActorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
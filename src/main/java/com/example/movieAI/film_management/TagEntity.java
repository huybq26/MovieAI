package com.example.movieAI.film_management;

import jakarta.persistence.*;

@Entity(name = "Tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    public TagEntity(String name) {
        this.name = name;
    }

    public TagEntity() {

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

    @Override
    public String toString() {
        return "TagEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
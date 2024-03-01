package com.example.movieAI.film_management;

import jakarta.persistence.*;


@Entity(name = "Trivia")
public class TriviaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    private Long id;

    @Column
    private String content;

    public TriviaEntity() {
    }

    public TriviaEntity(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TriviaEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
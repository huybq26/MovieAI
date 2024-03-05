package com.example.movieAI.booking;

import jakarta.persistence.*;

@Entity
@Table(name = "price")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", length = 5)
    private String type;

    @Column(name = "isvip")
    private Boolean isVip;

    @Column(name = "price")
    private Float price;

    public PriceEntity() {
    }

    public PriceEntity(String type, Boolean isVip, Float price) {
        this.type = type;
        this.isVip = isVip;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

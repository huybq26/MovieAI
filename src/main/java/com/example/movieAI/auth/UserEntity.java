package com.example.movieAI.auth;

import com.example.movieAI.booking.SlotSeatUserEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<SlotSeatUserEntity> slotSeatUsers;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

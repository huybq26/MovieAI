package com.example.movieAI.auth;

import com.example.movieAI.booking.TimeslotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailOrPhone(String email, String phone);
    boolean existsByEmailOrPhone(String email, String phone);
}

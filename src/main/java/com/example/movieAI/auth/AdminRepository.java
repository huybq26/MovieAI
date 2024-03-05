package com.example.movieAI.auth;

import com.example.movieAI.booking.TimeslotEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
}

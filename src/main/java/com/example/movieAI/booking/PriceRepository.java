package com.example.movieAI.booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PriceRepository extends CrudRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p WHERE p.type = :type AND p.isVip = true")
    Optional<PriceEntity> findVipPriceByType(@Param("type") String type);

    @Query("SELECT p FROM PriceEntity p WHERE p.type = :type AND p.isVip = false")
    Optional<PriceEntity> findRegularPriceByType(@Param("type") String type);
}

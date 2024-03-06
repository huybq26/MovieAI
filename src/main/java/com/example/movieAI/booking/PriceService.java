package com.example.movieAI.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<PriceEntity> findVipPriceByType(String type) {
        return priceRepository.findVipPriceByType(type);
    }

    public Optional<PriceEntity> findRegularPriceByType(String type) {
        return priceRepository.findRegularPriceByType(type);
    }

}

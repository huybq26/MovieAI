package com.example.movieAI.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/vip/{type}")
    public Optional<PriceEntity> getVipPrice(@PathVariable String type) {
        return priceService.findVipPriceByType(type);
    }

    @GetMapping("/regular/{type}")
    public Optional<PriceEntity> getRegularPrice(@PathVariable String type) {
        return priceService.findRegularPriceByType(type);
    }
}

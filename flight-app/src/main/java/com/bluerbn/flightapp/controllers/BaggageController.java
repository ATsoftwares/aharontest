package com.bluerbn.flightapp.controllers;

import com.bluerbn.flightapp.cache.Cache;
import com.bluerbn.flightapp.models.CacheModel;
import com.bluerbn.flightapp.services.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aharon Bar-El
 */
@RestController
public class BaggageController {

    @Autowired
    private BaggageService baggageService;

    @Autowired
    private Cache<String, CacheModel<?>> cache;

    @GetMapping("/check-in/destination/{destinationId}/baggage/{baggageId}")
    public boolean checkIn(@PathVariable("destinationId") Integer destinationId, @PathVariable("baggageId") String baggageId) {
        String key = String.format("destination:%s&baggage:%s", destinationId, baggageId);
        CacheModel<?> cacheModel = cache.get(key);
        if (cacheModel != null) {
            return (Boolean) cacheModel.getValue();
        }
        boolean result = baggageService.checkIn(destinationId, baggageId);
        cache.add(key, new CacheModel<>(System.currentTimeMillis(), result));
        return result;
    }
}

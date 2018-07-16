package com.bluerbn.flightapp.controllers;

import com.bluerbn.flightapp.cache.Cache;
import com.bluerbn.flightapp.models.CacheModel;
import com.bluerbn.flightapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aharon Bar-El
 */
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private Cache<String, CacheModel<?>> cache;

    @GetMapping("/tickets/{ticketId}")
    public Boolean getTicket(@PathVariable("ticketId") Integer ticketId) {
        String key = String.format("ticket:%s", ticketId);
        CacheModel<?> cacheModel = cache.get(key);
        if (cacheModel != null) {
            return (Boolean) cacheModel.getValue();
        }
        Boolean result = ticketService.isTicketAvailable(ticketId);
        cache.add(key, new CacheModel<>(System.currentTimeMillis(), result));
        return result;
    }
}

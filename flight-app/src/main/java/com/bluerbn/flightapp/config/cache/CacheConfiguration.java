package com.bluerbn.flightapp.config.cache;


import com.bluerbn.flightapp.cache.Cache;
import com.bluerbn.flightapp.cache.MemoryCache;
import com.bluerbn.flightapp.config.cache.properties.CacheProperties;
import com.bluerbn.flightapp.models.CacheModel;
import com.bluerbn.flightapp.models.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

import static com.bluerbn.flightapp.config.BeanNamesConstants.TICKET_CACHE;

/**
 * @author Aharon Bar-El
 */
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheProperties cacheProperties() {
        return new CacheProperties();
    }

    @Bean(TICKET_CACHE)
    public Cache<String, CacheModel<?>> inMemoryCache(CacheProperties properties) {
        ConcurrentHashMap<String, CacheModel<?>> map = new ConcurrentHashMap<>();
        MemoryCache memoryCache = new MemoryCache(properties, map);
        memoryCache.add("1", new CacheModel<>(System.currentTimeMillis(),
                new Ticket(1, "A1")));
        memoryCache.add("2", new CacheModel<>(System.currentTimeMillis() + 4000,
                new Ticket(2, "A3")));
        memoryCache.add("3", new CacheModel<>(System.currentTimeMillis() + 7000,
                new Ticket(3, "A2")));
        return memoryCache;
    }
}

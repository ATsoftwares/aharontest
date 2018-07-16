package com.bluerbn.flightapp.config.cache.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Aharon Bar-El
 */
@ConfigurationProperties(prefix = "cache")
@Getter
@Setter
public class CacheProperties {
    /**
     * time to live in milis
     */
    private long timeToLive = 10000;
    /**
     * max size to save in cache
     */
    private int size = 10;
    /**
     * interval to check if to remove object from the cache
     */
    private long cacheInterval = 2000;
}

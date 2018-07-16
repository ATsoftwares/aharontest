package com.bluerbn.flightapp.cache;

import com.bluerbn.flightapp.config.cache.properties.CacheProperties;
import com.bluerbn.flightapp.models.CacheModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Aharon Bar-El
 */
@AllArgsConstructor
@Slf4j
public class MemoryCache implements Cache<String, CacheModel<?>> {

    private final CacheProperties properties;
    private final ConcurrentHashMap<String, CacheModel<?>> cache;

    @PostConstruct
    public void start() {
        CompletableFuture.runAsync(this::startCaching);
    }

    @PreDestroy
    public void destroy() {
        log.info("performing destroy method");
        clear();
    }

    private void startCaching() {
        log.info("starting to work with cache");
        while (true) {
            try {
                cache.forEach((k, v) -> {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - v.getTime() >= properties.getTimeToLive()) {
                        remove(k);
                        log.info("removed {} from cache", v.getValue());
                    }
                });
                Thread.sleep(properties.getCacheInterval());
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void add(String key, CacheModel<?> value) {
        if (!cache.contains(key)) {
            if (cache.size() < properties.getSize()) {
                cache.put(key, value);
                log.info("added new value with key {} to cache", key);
            }
        } else {
            cache.replace(key, new CacheModel<>(System.currentTimeMillis(), value.getValue()));
            log.info("updated value with key {} in cache", key);
        }
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public CacheModel get(String key) {
        return cache.get(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int size() {
        return cache.size();
    }
}

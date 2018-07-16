package com.bluerbn.flightapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aharon Bar-El
 */
@Getter
@Setter
@AllArgsConstructor
public class CacheModel<T> {
    private long time;
    private T value;
}

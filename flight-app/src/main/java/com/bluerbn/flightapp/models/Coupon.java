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
public class Coupon {

    private final Integer id;
    private final Double discount;
}


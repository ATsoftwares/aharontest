package com.bluerbn.flightapp.services;

/**
 * @author Aharon Bar-El
 */
public interface CouponService {

    Double getPriceAfterDiscount(Integer couponId, Double price);
}

package com.bluerbn.flightapp.mock;

import com.bluerbn.flightapp.models.Coupon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Aharon Bar-El
 */
public class CouponDBMock implements DBMock<Coupon> {

    @Override
    public List<Coupon> getData() {
        return Arrays.asList(
                new Coupon(1, getCouponRandomly()),
                new Coupon(2, getCouponRandomly()),
                new Coupon(3, getCouponRandomly()));
    }

    private List<Double> getDiscounts() {
        return Arrays.asList(0.1, 0.5, 0.6);
    }

    private Double getCouponRandomly() {
        Random random = new Random();
        return getDiscounts().get(random.nextInt(2));
    }
}

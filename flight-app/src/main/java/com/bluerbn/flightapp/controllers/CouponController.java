package com.bluerbn.flightapp.controllers;

import com.bluerbn.flightapp.cache.Cache;
import com.bluerbn.flightapp.models.CacheModel;
import com.bluerbn.flightapp.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aharon Bar-El
 */
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private Cache<String, CacheModel<?>> cache;

    @GetMapping("/coupon/{couponId}/price/{price}")
    public Double getPriceAfterDiscount(@PathVariable("couponId") Integer couponId, @PathVariable("price") Double price) {
        String key = String.format("coupon:%s&price:%s", couponId, price);
        CacheModel<?> cacheModel = cache.get(key);
        if (cacheModel != null) {
            return (Double) cacheModel.getValue();
        }
        Double result = couponService.getPriceAfterDiscount(couponId, price);
        cache.add(key, new CacheModel<>(System.currentTimeMillis(), result));
        return result;
    }
}

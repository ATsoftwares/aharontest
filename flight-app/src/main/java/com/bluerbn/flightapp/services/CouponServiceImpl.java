package com.bluerbn.flightapp.services;

import com.bluerbn.flightapp.mock.DBMock;
import com.bluerbn.flightapp.models.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aharon Bar-El
 */
@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    @Autowired
    private DBMock<Coupon> couponDBMock;

    @Override
    public Double getPriceAfterDiscount(Integer couponId, Double price) {
        List<Coupon> collect = couponDBMock.getData().stream()
                .filter(coupon -> coupon.getId().equals(couponId))
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            log.info("found coupon with id {}", couponId);
            return price - (price * collect.get(0).getDiscount());
        } else {
            log.info("did not found coupon with id {}", couponId);
            return 0.0;
        }
    }


}

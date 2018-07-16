package com.bluerbn.flightapp.config;

import com.bluerbn.flightapp.mock.BaggageDBMock;
import com.bluerbn.flightapp.mock.CouponDBMock;
import com.bluerbn.flightapp.mock.DBMock;
import com.bluerbn.flightapp.mock.TicketsDBMock;
import com.bluerbn.flightapp.models.Baggage;
import com.bluerbn.flightapp.models.Coupon;
import com.bluerbn.flightapp.models.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aharon Bar-El
 */
@Configuration
@Slf4j
public class MockConfiguration {

    @Bean(BeanNamesConstants.TICKET_DB)
    public DBMock<Ticket> ticketsDBMock() {
        log.info("ticket mock db loaded");
        return new TicketsDBMock();
    }

    @Bean(BeanNamesConstants.COUPON_DB)
    public DBMock<Coupon> couponDBMock() {
        log.info("coupon mock db loaded");
        return new CouponDBMock();
    }

    @Bean(BeanNamesConstants.BAGGAGE_DB)
    public DBMock<Baggage> baggageDBMock() {
        log.info("baggage mock db loaded");
        return new BaggageDBMock();
    }
}

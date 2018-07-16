package com.bluerbn.flightapp.services;

import com.bluerbn.flightapp.mock.DBMock;
import com.bluerbn.flightapp.models.Baggage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aharon Bar-El
 */
@Service
@Slf4j
public class BaggageServiceImpl implements BaggageService {

    @Autowired
    private DBMock<Baggage> baggageDBMock;

    @Override
    public boolean checkIn(Integer destinationId, String baggageId) {
        log.info("check if check-in success for destination {} and baggage {}", destinationId, baggageId);
        return baggageDBMock.getData().stream().anyMatch(baggage ->
                baggage.getBaggageId().equals(baggageId) && baggage.getDestinationId().equals(destinationId));
    }
}

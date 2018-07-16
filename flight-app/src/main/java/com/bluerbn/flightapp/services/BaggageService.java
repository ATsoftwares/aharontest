package com.bluerbn.flightapp.services;

/**
 * @author Aharon Bar-El
 */
public interface BaggageService {

    boolean checkIn(Integer destinationId, String baggageId);
}

package com.bluerbn.flightapp.mock;

import com.bluerbn.flightapp.models.Baggage;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aharon Bar-El
 */
public class BaggageDBMock implements DBMock<Baggage> {

    @Override
    public List<Baggage> getData() {
        return Arrays.asList(new Baggage("a1", 1),
                new Baggage("a2", 2),
                new Baggage("a3", 3));
    }
}

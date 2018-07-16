package com.bluerbn.flightapp.mock;

import com.bluerbn.flightapp.models.Ticket;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aharon Bar-El
 */
public class TicketsDBMock implements DBMock<Ticket> {

    @Override
    public List<Ticket> getData() {
        return Arrays.asList(new Ticket(1, "aa"), new Ticket(2, "bb"), new Ticket(3, "cc"));
    }
}

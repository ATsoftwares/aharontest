package com.bluerbn.flightapp.services;

import com.bluerbn.flightapp.mock.DBMock;
import com.bluerbn.flightapp.models.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Aharon Bar-El
 */
@Component
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    private DBMock<Ticket> ticketDBMock;

    @Override
    public boolean isTicketAvailable(Integer id) {
        log.info("checking if ticket with id {} is available", id);
        return ticketDBMock.getData().stream().anyMatch(t -> t.getId().equals(id));
    }
}

package com.bluerbn.flightapp.mock;

import java.util.List;

/**
 * @author Aharon Bar-El
 */
public interface DBMock<T> {

    List<T> getData();
}

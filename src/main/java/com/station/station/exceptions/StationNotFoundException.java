package com.station.station.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class StationNotFoundException extends RuntimeException {
    public StationNotFoundException(long stationId) {
        super(String.format("Station with id: %d was not found", stationId));
    }

    public StationNotFoundException(String stationName) {
        super(String.format("Station with name: %s was not found", stationName));
    }
}

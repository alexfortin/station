package com.station.station.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String stationName) {
        super(String.format("Station with name: %s already exists", stationName));
    }
}

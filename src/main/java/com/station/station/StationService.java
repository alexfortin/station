package com.station.station;

import com.station.station.exceptions.DuplicateNameException;
import com.station.station.exceptions.StationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationService {

    @Autowired
    StationMapper mapper;

    public Station findStation(long stationId) {
        Station station = mapper.find(stationId);
        if(station == null) {
            throw new StationNotFoundException(stationId);
        }
        return station;
    }

    public List<Station> findAllStations() {
        return mapper.findAll();
    }

    public void createStation(Station station) {
        Station existingStation = mapper.findByName(station.getName());
        if(existingStation != null) {
            throw new DuplicateNameException(existingStation.getName());
        }
        mapper.insertStation(station);
    }

    public void removeStation(long stationId) {
        mapper.removeStation(stationId);
    }

    public void updateStation(Station station) {
        Station oldStation = mapper.find(station.getStationId());
        if(oldStation == null) {
            throw new StationNotFoundException(station.getStationId());
        }
        mapper.updateStation(station);
    }

    public List<Station> findHdEnabledStations() {
        return mapper.findHdEnabledStations();
    }

    public Station findByName(String stationName) {
        Station station = mapper.findByName(stationName);
        if(station == null) {
            throw new StationNotFoundException(stationName);
        }
        return mapper.findByName(stationName);
    }
}

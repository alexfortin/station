package com.station.station;

import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationService {

    @Autowired
    StationMapper mapper;

    @SneakyThrows
    public Station findStation(long stationId) {
        Station station = mapper.find(stationId);
        if(station == null) {
            throw new NotFoundException(String.format("Station with id:%d does no exist", stationId));
        }
        return station;
    }

    public List<Station> findAllStations() {
        return mapper.findAll();
    }

    public void createStation(Station station) {
        mapper.insertStation(station);
    }

    public void removeStation(long stationId) {
        mapper.removeStation(stationId);
    }

    @SneakyThrows
    public void updateStation(Station station) {
        Station oldStation = mapper.find(station.getStationId());
        if(oldStation == null) {
            throw new NotFoundException(String.format("Station with id:%d does no exist", station.getStationId()));
        }
        mapper.updateStation(station);
    }
}

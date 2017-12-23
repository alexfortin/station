package com.station.station;

import org.apache.ibatis.javassist.NotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationApplicationTests {
    @Autowired
    StationService stationService;

    @After
    public void cleanup() {
        List<Station> stationList = stationService.findAllStations();
        for(Station station : stationList) {
            stationService.removeStation(station.getStationId());
        }
    }

    @Test
    public void testBasicStationCrud() {
        Station station = Station.builder().name("test").build();
        stationService.createStation(station);
        List<Station> result = stationService.findAllStations();
        assertEquals(1, result.size());
        station = result.get(0);
        assertEquals("test", result.get(0).getName());

        station.setName("new");
        stationService.updateStation(station);
        Station newStation = stationService.findStation(station.getStationId());
        assertEquals("new", newStation.getName());

        stationService.removeStation(station.getStationId());
        List<Station> stationList = stationService.findAllStations();
        assertEquals(0, stationList.size());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundFind() {
        stationService.findStation(1);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdaet() {
        stationService.updateStation(Station.builder().stationId(1).build());
    }
}

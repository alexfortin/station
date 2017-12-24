package com.station.station;

import com.station.station.exceptions.DuplicateNameException;
import com.station.station.exceptions.StationNotFoundException;
import org.junit.After;
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

    @Test(expected = StationNotFoundException.class)
    public void testNotFoundFindStation() {
        stationService.findStation(1);
    }

    @Test(expected = StationNotFoundException.class)
    public void testNotFoundUpdateStation() {
        stationService.updateStation(Station.builder().stationId(1).build());
    }

    @Test
    public void testHdEnabled() {
        Station station = Station.builder().hdEnabled(false).build();
        stationService.createStation(station);
        List<Station> result = stationService.findHdEnabledStations();
        assertEquals(0, result.size());

        Station hdEnabledStation =  Station.builder().hdEnabled(true).build();
        stationService.createStation(hdEnabledStation);
        result = stationService.findHdEnabledStations();
        assertEquals(1, result.size());
        assertEquals(true, result.get(0).isHdEnabled());
    }

    @Test
    public void testFindByName() {
        Station station = Station.builder().name("CaseInsensitive").build();
        stationService.createStation(station);
        Station result = stationService.findByName("caseinsENSITIVE");
        assertEquals("CaseInsensitive", result.getName());
    }

    @Test(expected = StationNotFoundException.class)
    public void testStationNameNotFound() {
        stationService.findByName("not found");
    }

    @Test(expected = DuplicateNameException.class)
    public void testCreateSameName() {
        String name = "test";
        stationService.createStation(Station.builder().name(name).build());
        stationService.createStation(Station.builder().name(name).build());
    }
}

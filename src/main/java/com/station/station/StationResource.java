package com.station.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/station")
public class StationResource {
    @Autowired
    StationService stationService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Station> getAllStation() {
        return stationService.findAllStations();
    }

    @RequestMapping(path = "/{stationId}", method = RequestMethod.GET)
    public @ResponseBody
    Station getStation(@PathVariable(value = "stationId") long stationId) {
        return stationService.findStation(stationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    void createStation(@RequestBody Station station) {
        stationService.createStation(station);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    void updateStation(@RequestBody Station station) {
        stationService.updateStation(station);
    }

    @RequestMapping(path = "find", method = RequestMethod.GET)
    public @ResponseBody
    Station find(@RequestParam String stationName) {
        return stationService.findByName(stationName);
    }

    @RequestMapping(path = "hdEnabled", method = RequestMethod.GET)
    public @ResponseBody
    List<Station> findAllHdEnabled() {
        return stationService.findHdEnabledStations();
    }

    @RequestMapping(path = "/{stationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteStation(@PathVariable(value = "stationId") long stationId) {
        stationService.removeStation(stationId);
    }
}

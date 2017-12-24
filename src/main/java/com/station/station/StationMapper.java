package com.station.station;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("select * from station")
    List<Station> findAll();

    @Select("select * from station where hd_enabled = true")
    List<Station> findHdEnabledStations();

    @Select("select * from station where lower(name) like lower(#{name})")
    Station findByName(String stationName);

    @Select("select * from station where station_id = #{stationId}")
    Station find(long stationId);

    @Insert("insert into station (name, hd_enabled, call_sign) values (#{name}, #{hdEnabled}, #{callSign})")
    void insertStation(Station station);

    @Delete("delete from station where station_id = #{stationId}")
    void removeStation(long stationId);

    @Update("update station set NAME = #{name}, hd_enabled = #{hdEnabled}, call_sign = #{callSign} where station_id = #{stationId}")
    void updateStation(Station station);
}

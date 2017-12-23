package com.station.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Station {
    private long stationId;
    private String name;
    private boolean hdEnabled;
    private String callSign;
}

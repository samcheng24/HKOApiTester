package com.sam.enums;

import java.util.HashMap;
import java.util.Map;

public enum DataTypeEarthquakeEnum {
    QUICK_EARTHQUAKE_MESSAGES("qem"),
    LOCALLY_FELT_EARTH_TREMOR_REPORT("feltearthquake");

    private String value;

    DataTypeEarthquakeEnum(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    private static final Map<String, DataTypeEarthquakeEnum> lookup = new HashMap<>();
    static { //Create reverse lookup hash map
        for(DataTypeEarthquakeEnum d : DataTypeEarthquakeEnum.values())
            lookup.put(d.toString(), d);
    }

    // Given the value, lookup for the Enum Identifier
    static public DataTypeEarthquakeEnum lookup(String value) {
        return lookup.get(value);
    }

}

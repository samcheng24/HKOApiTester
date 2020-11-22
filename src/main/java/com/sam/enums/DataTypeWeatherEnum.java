package com.sam.enums;

import java.util.HashMap;
import java.util.Map;

public enum DataTypeWeatherEnum {
    LOCAL_WEATHER_FORECAST("flw"),
    NINE_DAY_WEATHER_FORECAST("fnd"),
    CURRENT_WEATHER_REPORT("rhrread"),
    WEATHER_WARNING_SUMMARY("warnsum"),
    WEATHER_WARNING_INFORMATION("warningInfo"),
    SPECIAL_WEATHER_TIPS("swt"),
    INCORRECT_DATA_TYPE("gibberish"); // NOTE: For testing only. This is not supported by Hk Weather API

    private String value;

    DataTypeWeatherEnum(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    private static final Map<String, DataTypeWeatherEnum> lookup = new HashMap<>();
    static { //Create reverse lookup hash map
        for(DataTypeWeatherEnum d : DataTypeWeatherEnum.values())
            lookup.put(d.toString(), d);
    }

    // Given the value, lookup for the Enum Identifier
    static public DataTypeWeatherEnum lookup(String value) {
        return lookup.get(value);
    }

}

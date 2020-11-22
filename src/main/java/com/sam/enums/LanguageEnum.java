package com.sam.enums;

import java.util.HashMap;
import java.util.Map;

public enum LanguageEnum {
    ENGLISH("en"),
    TRADITIONAL_CHINESE("tc"),
    SIMPLIFIED_CHINESE("sc"),
    KOREAN("kr"); // NOTE: For testing only. This is not supported by Hk Weather API

    private String value;

    LanguageEnum(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    private static final Map<String, LanguageEnum> lookup = new HashMap<>();
    static { //Create reverse lookup hash map
        for(LanguageEnum d : LanguageEnum.values())
            lookup.put(d.toString(), d);
    }

    // Given the value, lookup for the Enum Identifier
    static public LanguageEnum lookup(String value) {
        return lookup.get(value);
    }

}

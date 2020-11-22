package com.sam.testDataProviders;

import com.sam.enums.DataTypeWeatherEnum;
import com.sam.enums.LanguageEnum;
import org.testng.annotations.DataProvider;

public class TestCasesDataProvider {
    @DataProvider
    public static Object[][] TESTCASES_WEATHER_HAPPY() {
        Object[][] testData = new Object[][]{};
        testData = new Object[][]{
                { "Happy Test 1 - Current Weather Report - English",
                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
                        LanguageEnum.ENGLISH },
                { "Happy Test 2 - Current Weather Report - Traditional Chinese",
                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
                        LanguageEnum.TRADITIONAL_CHINESE },
                { "Happy Test 3 - Current Weather Report - Simplified Chinese",
                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
                        LanguageEnum.SIMPLIFIED_CHINESE },
                { "Happy Test 4 - Current Weather Report - empty language",
                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
                        null },
                // Test more Variants of Data Type here
        };
        return testData;
    }

    @DataProvider
    public static Object[][] TESTCASES_WEATHER_SAD() {
        Object[][] testData = new Object[][]{};
        testData = new Object[][]{
                { "Sad Test 1 - Current Weather Report - null dataType",
                        null,
                        LanguageEnum.ENGLISH },
                { "Sad Test 2 - Current Weather Report - incorrect dataType",
                        DataTypeWeatherEnum.INCORRECT_DATA_TYPE,
                        LanguageEnum.ENGLISH },
                { "Sad Test 3 - Current Weather Report - unsupported language",
                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
                        LanguageEnum.KOREAN },
                // Test more Variants of Data Type here
        };
        return testData;
    }

    @DataProvider
    public static Object[][] TESTCASES_WEATHER_BAD() {
        Object[][] testData = new Object[][]{};
        testData = new Object[][]{
//                { "Test 1 - Current Weather Report",
//                        DataTypeWeatherEnum.CURRENT_WEATHER_REPORT,
//                        LanguageEnum.ENGLISH },
        };

        return testData;
    }
}

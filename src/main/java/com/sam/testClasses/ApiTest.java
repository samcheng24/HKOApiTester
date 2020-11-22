package com.sam.testClasses;

import com.google.gson.Gson;
import com.sam.models.HkoWeatherApi;
import com.sam.models.WeatherResponse;
import com.sam.testDataProviders.TestCasesDataProvider;
import com.sam.enums.DataTypeWeatherEnum;
import com.sam.enums.LanguageEnum;
import com.sam.enums.ResponseKeyEnum;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.http.HttpMethod;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.sam.utility.Constants.WEATHER_GOV_URL_ERROR;

public class ApiTest {
    private static final Logger LOGGER = LogManager.getLogger(ApiTest.class.getName());

    @BeforeMethod
    public void beforeMethod(Object[] dataProviderArgs) {
        String testName = (String) dataProviderArgs[0]; // To log test name for all Test Methods
        LOGGER.info("Running: " + testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            if(result.getStatus() == ITestResult.SUCCESS) {
                LOGGER.info("*********** passed **********");
            } else if(result.getStatus() == ITestResult.FAILURE) {
                LOGGER.info("*********** Failed ***********");
            } else if(result.getStatus() == ITestResult.SKIP ){
                LOGGER.info("*********** Skipped ***********");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "TESTCASES_WEATHER_HAPPY", dataProviderClass = TestCasesDataProvider.class)
    public void testWeatherHappy(String testName, DataTypeWeatherEnum dataTypeWeather, LanguageEnum language){
        // Request
        Map<ResponseKeyEnum, Object> httpResponse = HkoWeatherApi.sendRequestToWeatherAPI(HttpMethod.GET, dataTypeWeather, language);

        // Assertion on Response
            Assert.assertEquals(
                    httpResponse.get(ResponseKeyEnum.RESPONSE_CODE),
                    HttpStatus.SC_OK);

        // Get date now
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LOGGER.info("Date Time now: " + dateTimeNow.toString());

        // Get date from response: i.e. "updateTime":"2020-11-22T00:02:00+08:00"
        String response = (String) httpResponse.get(ResponseKeyEnum.RESPONSE_CONTENT);
        WeatherResponse weatherResponse = new Gson().fromJson(response , WeatherResponse.class);
        LocalDateTime dateTimeFromResponse = LocalDateTime.parse(weatherResponse.getUpdateTime().substring(0, weatherResponse.getUpdateTime().length()-6));
        LOGGER.info("Date Time from response: " + dateTimeFromResponse);

        // Assert on Content: Date
        Assert.assertEquals(dateTimeNow.getYear(), dateTimeFromResponse.getYear());
        Assert.assertEquals(dateTimeNow.getMonth(), dateTimeFromResponse.getMonth());
        Assert.assertEquals(dateTimeNow.getDayOfMonth(), dateTimeFromResponse.getDayOfMonth());

        // Assert on Header
        Map<String, List<String>> headers = (Map<String, List<String>>) httpResponse.get(ResponseKeyEnum.RESPONSE_HEADER);
        Assert.assertEquals(headers.get("Content-Type").toString(), "[application/json; charset=utf-8]");
    }

    @Test(dataProvider = "TESTCASES_WEATHER_SAD", dataProviderClass = TestCasesDataProvider.class)
    public void testWeatherSad(String testName, DataTypeWeatherEnum dataTypeWeather, LanguageEnum language){
        // Request
        Map<ResponseKeyEnum, Object> httpResponse = HkoWeatherApi.sendRequestToWeatherAPI(HttpMethod.GET, dataTypeWeather, language);

        // Assertion on Response
        Assert.assertEquals(
                httpResponse.get(ResponseKeyEnum.RESPONSE_CODE),
                HttpStatus.SC_OK);

        // Assert on Content: Error
        String response = (String) httpResponse.get(ResponseKeyEnum.RESPONSE_CONTENT);
        Assert.assertEquals(response, WEATHER_GOV_URL_ERROR);

        // Assert on Header
        Map<String, List<String>> headers = (Map<String, List<String>>) httpResponse.get(ResponseKeyEnum.RESPONSE_HEADER);
        Assert.assertEquals(headers.get("Content-Type").toString(), "[text/html; charset=utf-8]");
    }

    @Test(dataProvider = "TESTCASES_WEATHER_BAD", dataProviderClass = TestCasesDataProvider.class)
    public void testWeatherBad(String testName, DataTypeWeatherEnum dataTypeWeather, LanguageEnum language){
        // TODO: Test on what triggers a 404\
    }
}

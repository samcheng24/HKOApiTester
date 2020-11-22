package com.sam.models;

import com.sam.enums.DataTypeEarthquakeEnum;
import com.sam.enums.DataTypeWeatherEnum;
import com.sam.enums.LanguageEnum;
import com.sam.enums.ResponseKeyEnum;
import com.sam.utility.Constants;
import com.sam.utility.HttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.remote.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Model class to interface to HKO Weather API
 * API Spec: https://data.weather.gov.hk/weatherAPI/doc/HKO_Open_Data_API_Documentation.pdf
 */
public class HkoWeatherApi {

    public static Map<ResponseKeyEnum, Object> sendRequestToWeatherAPI(HttpMethod httpMethod, DataTypeWeatherEnum dataTypeWeather, LanguageEnum language) {
        String requestParams = HkoWeatherApi.genReqParamWeather(dataTypeWeather, language);

        return HttpClient.send(Constants.WEATHER_GOV_URL, httpMethod, requestParams);
    }

    // Weather Information API
    private static String genReqParamWeather(DataTypeWeatherEnum dataTypeWeather, LanguageEnum language) {
        List<NameValuePair> paramsList = new ArrayList<>();
        if(dataTypeWeather!=null)paramsList.add(new BasicNameValuePair("dataType", dataTypeWeather.toString()));
        if(language!=null)paramsList.add(new BasicNameValuePair("lang", language.toString()));
        return URLEncodedUtils.format(paramsList, "UTF-8");
    }

    // Earthquake Information API
    private static String genReqParamEarthquake(DataTypeEarthquakeEnum dataTypeEarthquake, LanguageEnum language) {
        List<NameValuePair> paramsList = new ArrayList<>();
        if(dataTypeEarthquake!=null)paramsList.add(new BasicNameValuePair("dataType", dataTypeEarthquake.toString()));
        if(language!=null)paramsList.add(new BasicNameValuePair("lang", language.toString()));
        return URLEncodedUtils.format(paramsList, "UTF-8");
    }

    // OPEN DATA (CLIMATE AND WEATHER INFORMATION) API
    // TODO
}

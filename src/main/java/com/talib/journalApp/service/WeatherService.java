package com.talib.journalApp.service;

import com.talib.journalApp.api.response.WeatherResponse;
import com.talib.journalApp.cache.AppCache;
import com.talib.journalApp.constants.Placeholders;
import com.talib.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }



//    //for POST API
//    public WeatherResponse setWeather(String city){
//        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
//        //we can also send header through post
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.set("Key","Value");
//
//        User user = User.builder().userName("Sufiyan").password("Sufiyan").build();
//        HttpEntity<User> httpEntity1=new HttpEntity<>(user,httpHeaders);
//
//        String requestBody="{\n"+
//                "\"userName\":\"Sufiyan\",\n"+
//                "\"password\":\"Sufiyan\"\n"+"}";
//        HttpEntity<String> httpEntity=new HttpEntity<>(requestBody);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }
}

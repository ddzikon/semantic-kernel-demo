package org.example.ai;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class WeatherClient {
    private static final String WEATHER_API_KEY;
    private static final String CURRENT_WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    static {
        WEATHER_API_KEY = System.getenv("WEATHER_API_KEY");
        if (StringUtils.isBlank(WEATHER_API_KEY)) {
            throw new IllegalStateException("requires env var WEATHER_API_KEY");
        }
    }

    public String getCurrent(String location) {
        log.info("Requesting weather for location {}...", location);

        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri(CURRENT_WEATHER_URL
                        + "?key="
                        + WEATHER_API_KEY
                        + "&q="
                        + location
                        + "&aqi=no")
                .retrieve()
                .body(String.class);
    }
}

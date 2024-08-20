package org.example.ai;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestClient;
import java.util.function.Function;

@Slf4j
class WeatherFunction implements Function<WeatherFunction.Request, WeatherFunction.Response> {

    private static final String WEATHER_API_KEY;
    private static final String CURRENT_WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    public record Request(String location) {}
    public record Response(String output) {}

    static {
        WEATHER_API_KEY = System.getenv("WEATHER_API_KEY");
        if (StringUtils.isBlank(WEATHER_API_KEY)) {
            throw new IllegalStateException("requires env var WEATHER_API_KEY");
        }
    }

    @Override
    public Response apply(Request location) {
        log.info("Requesting weather for location {}", location);

        RestClient restClient = RestClient.create();

        final var weatherResponse = restClient.get()
                .uri(CURRENT_WEATHER_URL
                        + "?key="
                        + WEATHER_API_KEY
                        + "&q="
                        + location
                        + "&aqi=no")
                .retrieve()
                .body(String.class);

        log.info("Current weather is:\n{}", weatherResponse);

        return new Response(weatherResponse);
    }
}

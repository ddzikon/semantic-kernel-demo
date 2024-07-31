package org.example.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
class WeatherFunction implements Function<WeatherFunction.Request, WeatherFunction.Response> {

    // FIXME provide api key from external sources
    private static final String WEATHER_API_KEY = "70ffddb5a5f14f078cc91622241807";
    private static final String CURRENT_WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    public record Request(String location) {}
    public record Response(String output) {}

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

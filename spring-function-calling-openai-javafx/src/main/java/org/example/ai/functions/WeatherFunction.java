package org.example.ai.functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ai.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherFunction implements Function<WeatherFunction.Request, WeatherFunction.Response> {

    public static final String FUNCTION_NAME = "currentWeather";
    public static final String FUNCTION_DESCRIPTION = "Get the current weather in location";

    public record Request(String location) {}
    public record Response(String output) {}

    private final WeatherClient weatherClient;

    @Override
    public Response apply(Request request) {
        log.info("Requesting weather for request {}", request);

        final var weatherResponse = weatherClient.getCurrent(request.location);

        log.info("Current weather is:\n{}", weatherResponse);

        return new Response(weatherResponse);
    }
}

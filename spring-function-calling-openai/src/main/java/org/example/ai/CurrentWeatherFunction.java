package org.example.ai;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class CurrentWeatherFunction implements Function<CurrentWeatherFunction.Request, CurrentWeatherFunction.Response> {

    public enum Unit { C, F }

    public record Request(String location, Unit unit) {}
    public record Response(double temp, Unit unit) {
        Response cToF() {
            return new Response(this.temp * 1.8 + 32, Unit.F);
        }
    }

    private static final Map<String, Response> CITY_TO_TEMP = Map.of(
            "Old Town", new Response(30.0, Unit.C),
            "London", new Response(10.0, Unit.C),
            "Warsaw", new Response(18.0, Unit.C)
    );

    @Override
    public Response apply(Request request) {
        log.info("current weather request: {}", request);
        final var response = CITY_TO_TEMP.get(request.location);
        return response != null
                ? Unit.C == request.unit ? response : response.cToF()
                : null;
    }
}

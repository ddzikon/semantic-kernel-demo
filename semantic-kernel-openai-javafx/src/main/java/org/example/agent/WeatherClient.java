package org.example.agent;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class WeatherClient {
    // FIXME provide api key from external sources
    private static final String WEATHER_API_KEY = "70ffddb5a5f14f078cc91622241807";
    private static final String CURRENT_WEATHER_URL = "http://api.weatherapi.com/v1/current.json";

    private final OkHttpClient client;

    public String getCurrent(String location) {
        log.info("Requesting weather for location {}...", location);

        final var url = HttpUrl.parse(CURRENT_WEATHER_URL).newBuilder()
                .addQueryParameter("key", WEATHER_API_KEY)
                .addQueryParameter("q", location)
                .addQueryParameter("aqi", "no")
                .build();

        final var request = new Request.Builder()
                .url(url)
                .build();

        try (final var response = client.newCall(request).execute()) {
            final var responseBody = response.body().string();
            log.info("Current weather for location {}:\n{}", location, responseBody);
            return responseBody;
        } catch (IOException e) {
            final var logErrorMessage = String.format("Error getting current weather for %s", location);
            log.error(logErrorMessage, e);
            return logErrorMessage;
        }
    }
}

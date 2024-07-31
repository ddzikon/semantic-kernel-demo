package org.example.ai;

import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class OpenAiChatOptionsProvider {

    public static final String CURRENT_WEATHER_FUNCTION_NAME = "currentWeather";

    @Bean
    OpenAiChatOptions provide() {
        return OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withFunction(CURRENT_WEATHER_FUNCTION_NAME)
                .withFunctionCallbacks(List.of(
                                new FunctionCallbackWrapper.Builder<>(new WeatherFunction())
                                        .withName(CURRENT_WEATHER_FUNCTION_NAME)
                                        .withDescription("Get the current weather in location")
                                        .build()
                        )
                )
                .build();
    }
}

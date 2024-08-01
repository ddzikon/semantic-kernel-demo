package org.example.ai;

import org.example.db.Repository;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class OpenAiChatOptionsProvider {

    public static final String CURRENT_WEATHER_FUNCTION_NAME = "currentWeather";

    @Autowired
    private Repository repository;

    @Bean
    OpenAiChatOptions provide() {
        return OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withFunction(CURRENT_WEATHER_FUNCTION_NAME)
                .withFunction("personFinder")
                .withFunction("personAdder")
                .withFunction("personLister")
                .withFunctionCallbacks(List.of(
                                new FunctionCallbackWrapper.Builder<>(new WeatherFunction())
                                        .withName(CURRENT_WEATHER_FUNCTION_NAME)
                                        .withDescription("Get the current weather in location")
                                        .build(),
                                new FunctionCallbackWrapper.Builder<>(new PersonFinder(repository))
                                        .withName("personFinder")
                                        .withDescription("Find a person info by person name")
                                        .build(),
                                new FunctionCallbackWrapper.Builder<>(new PersonAdder(repository))
                                        .withName("personAdder")
                                        .withDescription("Insert a new person into the database")
                                        .build(),
                                new FunctionCallbackWrapper.Builder<>(new PersonLister(repository))
                                        .withName("personLister")
                                        .withDescription("Find all persons")
                                        .build()
                        )
                ).build();
    }
}

package org.example.ai;

import org.example.ai.functions.AccommodationFunctions;
import org.example.ai.functions.PersonFunctions;
import org.example.ai.functions.WeatherFunction;
import org.example.model.entities.Person;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
class OpenAiConfigurationProvider {

    private static final String ENV_VAR_SPRING_AI_OPENAI_API_KEY_NAME = "SPRING_AI_OPENAI_API_KEY";
    private static final String SPRING_AI_OPENAI_API_KEY;

    static {
        SPRING_AI_OPENAI_API_KEY = System.getenv(ENV_VAR_SPRING_AI_OPENAI_API_KEY_NAME);

        if (SPRING_AI_OPENAI_API_KEY == null || SPRING_AI_OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("missing env var " + ENV_VAR_SPRING_AI_OPENAI_API_KEY_NAME);
        }
    }

    @Bean
    @Autowired
    OpenAiChatOptions openAiChatOptions(
            WeatherFunction weatherFunction,
            PersonFunctions.Lister personLister,
            PersonFunctions.Finder personFinder,
            PersonFunctions.Adder personAdder,
            AccommodationFunctions.HotelFinder hotelFinder,
            AccommodationFunctions.EmailFinder emailFinder,
            AccommodationFunctions.EmailSender emailSender
    ) {
        return OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withFunctions(Set.of(
                        WeatherFunction.FUNCTION_NAME,
                        PersonFunctions.Lister.FUNCTION_NAME,
                        PersonFunctions.Finder.FUNCTION_NAME,
                        PersonFunctions.Adder.FUNCTION_NAME,
                        AccommodationFunctions.HotelFinder.FUNCTION_NAME,
                        AccommodationFunctions.EmailFinder.FUNCTION_NAME,
                        AccommodationFunctions.EmailSender.FUNCTION_NAME))
                .withFunctionCallbacks(List.of(
                                new FunctionCallbackWrapper.Builder<>(weatherFunction)
                                        .withName(WeatherFunction.FUNCTION_NAME)
                                        .withDescription(WeatherFunction.FUNCTION_DESCRIPTION)
                                        .build(),
                        new FunctionCallbackWrapper.Builder<>(personLister)
                                .withName(PersonFunctions.Lister.FUNCTION_NAME)
                                .withDescription(PersonFunctions.Lister.FUNCTION_DESCRIPTION)
                                .build(),
                        new FunctionCallbackWrapper.Builder<>(personFinder)
                                .withName(PersonFunctions.Finder.FUNCTION_NAME)
                                .withDescription(PersonFunctions.Finder.FUNCTION_DESCRIPTION)
                                .build(),
                        new FunctionCallbackWrapper.Builder<>(personAdder)
                                .withName(PersonFunctions.Adder.FUNCTION_NAME)
                                .withDescription(PersonFunctions.Adder.FUNCTION_DESCRIPTION)
                                .build(),
                        new FunctionCallbackWrapper.Builder<>(hotelFinder)
                                .withName(AccommodationFunctions.HotelFinder.FUNCTION_NAME)
                                .withDescription(AccommodationFunctions.HotelFinder.FUNCTION_DESCRIPTION)
                                .build(),
                        new FunctionCallbackWrapper.Builder<>(emailFinder)
                                .withName(AccommodationFunctions.EmailFinder.FUNCTION_NAME)
                                .withDescription(AccommodationFunctions.EmailFinder.FUNCTION_DESCRIPTION)
                                .build(),
                        new FunctionCallbackWrapper.Builder<>(emailSender)
                                .withName(AccommodationFunctions.EmailSender.FUNCTION_NAME)
                                .withDescription(AccommodationFunctions.EmailSender.FUNCTION_DESCRIPTION)
                                .build()
                        )
                )
                .build();
    }

    @Bean
    OpenAiApi openAiApi() {
        return new OpenAiApi(SPRING_AI_OPENAI_API_KEY);
    }

    @Bean
    OpenAiAudioApi openAiAudioApi() {
        return new OpenAiAudioApi(SPRING_AI_OPENAI_API_KEY);
    }
}

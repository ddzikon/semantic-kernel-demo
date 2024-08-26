package org.example.ai;

import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
    OpenAiChatOptions openAiChatOptions(WeatherFunction weatherFunction) {
        return OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withFunction(WeatherFunction.FUNCTION_NAME)
                .withFunctionCallbacks(List.of(
                                new FunctionCallbackWrapper.Builder<>(weatherFunction)
                                        .withName(WeatherFunction.FUNCTION_NAME)
                                        .withDescription("Get the current weather in location")
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

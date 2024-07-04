package org.example.ai;

import lombok.NoArgsConstructor;
import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ChatService {

    private static final String SPRING_AI_OPENAI_API_KEY;

    @Autowired
    private Repository repository;

    static {
        SPRING_AI_OPENAI_API_KEY = System.getenv("SPRING_AI_OPENAI_API_KEY");

        if (SPRING_AI_OPENAI_API_KEY == null || SPRING_AI_OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("missing env var SPRING_AI_OPENAI_API_KEY");
        }
    }

    public String askGpt(String message) {
        OpenAiApi openAiApi = new OpenAiApi(SPRING_AI_OPENAI_API_KEY);

        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
//                .withFunction("myPersons")
//                .withFunction("currentWeather")
//                .withFunctionCallbacks(List.of(
//                        new FunctionCallbackWrapper.Builder<>(new MyFunction())
//                                .withName("myPersons")
//                                .withDescription("Get my data.")
////                                .withResponseConverter(persons -> persons.stream().map(Person::toString).collect(Collectors.joining(", ")))
//                                .build(),
//                        new FunctionCallbackWrapper.Builder<>(new MockWeatherService())
//                                .withName("currentWeather")
//                                .withDescription("Get the current weather in location")
//                                .build()
//                        )
//                )
                .build();


        ChatModel chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions);

        return chatModel.call(new Prompt(message)).getResult().getOutput().getContent();
    }

    private static class MyFunction implements Function<MyFunction.Request, String> {

        @Autowired
        private Repository repository;

        public record Request(){}

        @Override
        public String apply(MyFunction.Request o) {
            return repository.findAll().stream()
                    .map(Person::toString)
                    .collect(Collectors.joining(", "));
        }
    }

    @Configuration
    static class Config {

        @Bean
        @Description("Get list of my persons")
        public Function<MyFunction.Request, String> myPersons() {
            return new MyFunction();
        }
    }
}

package org.example.ai;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatService {

    private static final String SPRING_AI_OPENAI_API_KEY;

    @Autowired
    private OpenAiChatOptions openAiChatOptions;

    static {
        SPRING_AI_OPENAI_API_KEY = System.getenv("SPRING_AI_OPENAI_API_KEY");

        if (SPRING_AI_OPENAI_API_KEY == null || SPRING_AI_OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("missing env var SPRING_AI_OPENAI_API_KEY");
        }
    }

    public String askGpt(String message) {
        OpenAiApi openAiApi = new OpenAiApi(SPRING_AI_OPENAI_API_KEY);

        ChatModel chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions);

        return chatModel.call(new Prompt(message)).getResult().getOutput().getContent();
    }
}

package org.example.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.ChatEntryViewModel;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.FunctionMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatService {

    private final OpenAiApi openAiApi;
    private final OpenAiChatOptions openAiChatOptions;
    private final ChatEntryViewModel chatEntryViewModel;

    public String askGpt(String message) {
        ChatModel chatModel = new OpenAiChatModel(openAiApi, openAiChatOptions);

        log.info("Preparing prompt for message:\n{}", message);

        chatEntryViewModel.storeChatEntry(MessageType.USER.name(), message);

        List<Message> chatHistory = chatEntryViewModel.getAllChatEntries().values().stream()
                .map(messageTypeToEntry -> {
                    MessageType messageType = MessageType.valueOf(messageTypeToEntry.getLeft());
                    String entry = messageTypeToEntry.getRight();

                    return switch (messageType) {
                        case USER -> new UserMessage(entry);
                        case ASSISTANT -> new AssistantMessage(entry);
                        case SYSTEM -> new SystemMessage(entry);
                        case FUNCTION -> new FunctionMessage(entry);
                        default ->
                                throw new IllegalArgumentException("Unknown message type: " + messageType + " of entry: " + entry);
                    };
                }).collect(Collectors.toCollection(ArrayList::new));

        final int lastMessageIndex = chatHistory.size() - 1;
        final int systemMessageIndex = Math.max(lastMessageIndex, 0);

        chatHistory.add(systemMessageIndex, new SystemMessage("You are helpful assistant. Before calling a function, that manipulates some data, you will ask the user for confirmation and present the parameters you would like to pass to the function."));

        AssistantMessage chatOutput = chatModel.call(new Prompt(chatHistory)).getResult().getOutput();
        log.info("Message sent, awaiting response...");

        String responseContent = chatOutput.getContent();
        log.info("Response received:\n{}", responseContent);

        chatEntryViewModel.storeChatEntry(chatOutput.getMessageType().name(), responseContent);

        return responseContent;
    }
}

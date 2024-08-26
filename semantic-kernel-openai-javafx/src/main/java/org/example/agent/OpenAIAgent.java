package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.ChatEntryViewModel;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class OpenAIAgent {

    private final ChatServiceWithFunctions chatServiceWithFunctions;
    private final ChatEntryViewModel chatEntryViewModel;

    public String askGpt(String message) {
        log.info("Generating message for the chat: {}", message);

        chatEntryViewModel.storeChatEntry(AuthorRole.USER.name(), message);

        List<ChatMessageContent> chatEntries = chatEntryViewModel.getAllChatEntries().values().stream()
                .map(stringStringPair -> new ChatMessageContent(
                                AuthorRole.valueOf(stringStringPair.getLeft()),
                                stringStringPair.getRight()
                        )
                ).toList();
        ChatHistory chatHistory = new ChatHistory(chatEntries);

        log.info("Sending message, waiting for response...");
        ChatMessageContent<?> chatResponse = chatServiceWithFunctions.interact(chatHistory);

        String chatResponseContent = chatResponse.getContent();

        log.info("Response received {}", chatResponse);

        chatEntryViewModel.storeChatEntry(chatResponse.getAuthorRole().name(), chatResponseContent);

        return chatResponseContent;
    }
}

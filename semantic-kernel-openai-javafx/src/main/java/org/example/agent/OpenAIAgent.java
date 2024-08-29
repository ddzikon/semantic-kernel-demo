package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.ChatEntryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                ).collect(Collectors.toCollection(ArrayList::new));

        final int lastMessageIndex = chatEntries.size() - 1;
        final int systemMessageIndex = Math.max(lastMessageIndex, 0);

        chatEntries.add(systemMessageIndex, new ChatMessageContent(AuthorRole.SYSTEM, "You are helpful assistant. Each action you take that affects other systems or people (for example data manipulation, sending messages, etc.) should be confirmed by the user you assist. You will present the parameters you would like to pass to the function executing the action and ask the user for confirmation. The confirmation is not required for read-only operations."));

        ChatHistory chatHistory = new ChatHistory(chatEntries);

        log.warn("CHAT HISTORY:");
        chatHistory.getMessages().forEach(messageContent -> System.out.println(messageContent.getAuthorRole() + ": " + messageContent.getContent()));

        log.info("Sending message, waiting for response...");
        ChatMessageContent<?> chatResponse = chatServiceWithFunctions.interact(chatHistory);

        String chatResponseContent = chatResponse.getContent();

        log.info("Response received {}", chatResponse);

        chatEntryViewModel.storeChatEntry(chatResponse.getAuthorRole().name(), chatResponseContent);

        return chatResponseContent;
    }
}

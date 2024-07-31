package org.example.agent;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import com.google.inject.Inject;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.ChatEntryViewModel;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Agent {
    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    static {
        if (OPENAI_API_KEY == null || OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("requires env var OPENAI_API_KEY");
        }
    }

    private final Plugin plugin;
    private final ChatEntryViewModel chatEntryViewModel;

    public String askGpt(String message) {
        log.info("Generating message for the chat: {}", message);

        OpenAIAsyncClient openAIClient = new OpenAIClientBuilder()
                .credential(new KeyCredential(OPENAI_API_KEY))
                .buildAsyncClient();

        ChatCompletionService chatCompletionService = ChatCompletionService.builder()
                .withModelId("gpt-3.5-turbo")
                .withOpenAIAsyncClient(openAIClient)
                .build();

        KernelPlugin kernelPlugin = KernelPluginFactory.createFromObject(plugin, "plugin");

        Kernel kernel = Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(kernelPlugin)
                .build();

        chatEntryViewModel.storeChatEntry(AuthorRole.USER.name(), message);

        List<ChatMessageContent> chatEntries = chatEntryViewModel.getAllChatEntries().values().stream()
                .map(stringStringPair -> new ChatMessageContent(
                                AuthorRole.valueOf(stringStringPair.getLeft()),
                                stringStringPair.getRight()
                        )
                ).toList();
        ChatHistory chatHistory = new ChatHistory(chatEntries);

        InvocationContext invocationContext = InvocationContext.builder()
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .build();

        log.info("Sending message, waiting for response...");
        List<ChatMessageContent<?>> conversation = chatCompletionService.getChatMessageContentsAsync(
                chatHistory,
                kernel,
                invocationContext
        ).block();

        ChatMessageContent<?> chatResponse = conversation.get(conversation.size() - 1);
        String chatResponseContent = chatResponse.getContent();

        log.info("Response received {}", chatResponse);

        chatEntryViewModel.storeChatEntry(chatResponse.getAuthorRole().name(), chatResponseContent);

        return chatResponseContent;
    }
}

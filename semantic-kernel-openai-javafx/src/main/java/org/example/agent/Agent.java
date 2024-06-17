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
import org.example.db.ChatEntry;
import org.example.db.Repository;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Agent {
    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    static {
        if (OPENAI_API_KEY == null || OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("requires env var OPENAI_API_KEY");
        }
    }

    private final Plugin plugin;
    private final Repository repository;

    public String askGpt(String question) {
        assert plugin != null;

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

        repository.storeEntry(ChatEntry.builder()
                .role(AuthorRole.USER.name())
                .entry(question)
                .build()
        );
        List<ChatMessageContent> chatEntries = repository.findAllChatEntries().stream()
                .map(chatEntry ->
                        new ChatMessageContent(AuthorRole.valueOf(chatEntry.getRole()), chatEntry.getEntry()))
                .toList();
        ChatHistory chatHistory = new ChatHistory(chatEntries);

        InvocationContext invocationContext = InvocationContext.builder()
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .build();

        List<ChatMessageContent<?>> conversation = chatCompletionService.getChatMessageContentsAsync(
                chatHistory,
                kernel,
                invocationContext
        ).block();

        ChatMessageContent<?> chatResponse = conversation.get(conversation.size() - 1);

        repository.storeEntry(ChatEntry.builder()
                .role(chatResponse.getAuthorRole().name())
                .entry(chatResponse.getContent())
                .build()
        );

        return chatResponse.getContent();
    }
}

package org.example.agent;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Agent {

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    static {
        if (OPENAI_API_KEY == null || OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("requires env var OPENAI_API_KEY");
        }
    }

    @Autowired
    private Plugin plugin;

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

        InvocationContext invocationContext = InvocationContext.builder()
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .build();

        List<ChatMessageContent<?>> conversation = chatCompletionService.getChatMessageContentsAsync(
                question,
                kernel,
                invocationContext
        ).block();

        return conversation.get(conversation.size() - 1).getContent();
    }
}

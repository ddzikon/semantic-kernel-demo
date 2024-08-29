package org.example.agent;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.google.inject.Inject;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class ChatServiceWithFunctions {

    private final ChatCompletionService chatCompletionService;
    private final InvocationContext invocationContext;
    private final Kernel kernel;

    @Inject
    public ChatServiceWithFunctions(OpenAIAsyncClient openAIAsyncClient, PersonPlugin personPlugin, WeatherPlugin weatherPlugin, AccommodationPlugin accommodationPlugin) {
        this.chatCompletionService = ChatCompletionService.builder()
                .withModelId("gpt-3.5-turbo")
                .withOpenAIAsyncClient(openAIAsyncClient)
                .build();

        this.invocationContext = InvocationContext.builder()
                .withToolCallBehavior(ToolCallBehavior.allowAllKernelFunctions(true))
                .build();

        KernelPlugin kernelPersonPlugin = KernelPluginFactory.createFromObject(personPlugin, "personPlugin");
        KernelPlugin kernelWeatherPlugin = KernelPluginFactory.createFromObject(weatherPlugin, "weatherPlugin");
        KernelPlugin kernelAccommodationPlugin = KernelPluginFactory.createFromObject(accommodationPlugin, "accommodationPlugin");

        this.kernel = Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .withPlugin(kernelPersonPlugin)
                .withPlugin(kernelWeatherPlugin)
                .withPlugin(kernelAccommodationPlugin)
                .build();
    }

    public ChatMessageContent interact(ChatHistory chatHistory) {
        List<ChatMessageContent<?>> conversation = chatCompletionService.getChatMessageContentsAsync(
                chatHistory,
                kernel,
                invocationContext
        ).block();

        return conversation.getLast();
    }
}

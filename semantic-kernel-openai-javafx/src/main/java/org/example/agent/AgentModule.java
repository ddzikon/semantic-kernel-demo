package org.example.agent;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AgentModule extends AbstractModule {

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    static {
        if (OPENAI_API_KEY == null || OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("requires env var OPENAI_API_KEY");
        }
    }

    @Provides
    @Singleton
    public OpenAIAsyncClient openAIAsyncClient() {
        return new OpenAIClientBuilder()
                .credential(new KeyCredential(OPENAI_API_KEY))
                .buildAsyncClient();
    }
}

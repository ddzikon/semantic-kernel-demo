package org.example.viewmodel;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.agent.OpenAIAgent;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AgentInteractor {

    private final OpenAIAgent openAIAgent;

    public void askGpt(String message) {
        this.openAIAgent.askGpt(message);
    }
}

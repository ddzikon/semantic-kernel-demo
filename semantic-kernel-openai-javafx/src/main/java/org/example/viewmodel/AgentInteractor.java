package org.example.viewmodel;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.agent.Agent;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AgentInteractor {

    private final Agent agent;

    public void askGpt(String message) {
        this.agent.askGpt(message);
    }
}

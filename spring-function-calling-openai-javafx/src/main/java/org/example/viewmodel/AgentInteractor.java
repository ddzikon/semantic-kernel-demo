package org.example.viewmodel;

import lombok.RequiredArgsConstructor;
import org.example.ai.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// FIXME reorganise arch to avoid circular reference
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AgentInteractor {

    private final ChatService chatService;

    public void askGpt(String message) {
        this.chatService.askGpt(message);
    }
}

package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.example.ai.ChatService;
import org.example.model.entities.ChatEntry;
import org.example.model.repositories.ChatEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChatEntryViewModel {

    @Getter
    private final ObservableList<String> chatHistoryProperty;

    private final ChatEntryRepository chatEntryRepository;

    @Autowired
    public ChatEntryViewModel(ChatEntryRepository chatEntryRepository) {
        this.chatEntryRepository = chatEntryRepository;

        final var initialChatEntries = chatEntryRepository.findAll().stream()
                .map(ChatEntry::getEntry)
                .toList();

        this.chatHistoryProperty = FXCollections.observableArrayList(initialChatEntries);
    }

    public void storeChatEntry(String authorRole, String message) {
        final var newChatEntry = ChatEntry.builder().entry(message).role(authorRole).build();
        final var storedChatEntry = chatEntryRepository.save(newChatEntry);

        chatHistoryProperty.add(storedChatEntry.getEntry());
    }

    public Map<Integer, Pair<String, String>> getAllChatEntries() {
        return chatEntryRepository.findAll().stream()
                .collect(Collectors.toMap(
                        ChatEntry::getId,
                        entry -> Pair.of(entry.getRole(), entry.getEntry())
                ));
    }
}

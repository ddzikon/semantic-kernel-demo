package org.example.viewmodel;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.example.model.entities.ChatEntry;
import org.example.model.repositories.ChatEntryRepository;

import java.util.Map;
import java.util.stream.Collectors;

public class ChatEntryViewModel {

    @Getter
    private final ObservableList<String> chatHistoryProperty;

    private final ChatEntryRepository chatEntryRepository;

    @Inject
    public ChatEntryViewModel(ChatEntryRepository chatEntryRepository) {
        this.chatEntryRepository = chatEntryRepository;

        final var initialChatEntries = chatEntryRepository.findAllChatEntries().stream()
                .map(ChatEntry::getEntry)
                .toList();

        this.chatHistoryProperty = FXCollections.observableArrayList(initialChatEntries);
    }

    public void storeChatEntry(String authorRole, String message) {
        final var newChatEntry = ChatEntry.builder().entry(message).role(authorRole).build();
        final var storedChatEntry = chatEntryRepository.storeEntry(newChatEntry);

        chatHistoryProperty.add(storedChatEntry.getEntry());
    }

    public Map<Integer, Pair<String, String>> getAllChatEntries() {
        return chatEntryRepository.findAllChatEntries().stream()
                .collect(Collectors.toMap(
                        ChatEntry::getId,
                        entry -> Pair.of(entry.getRole(), entry.getEntry())
                ));
    }
}

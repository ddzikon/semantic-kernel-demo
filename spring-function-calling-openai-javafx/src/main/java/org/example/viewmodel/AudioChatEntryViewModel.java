package org.example.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.model.entities.AudioChatEntry;
import org.example.model.repositories.AudioChatEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AudioChatEntryViewModel {

    @Getter
    private final ObservableList<String> audioChatHistoryProperty;

    private final AudioChatEntryRepository audioChatEntryRepository;

    @Autowired
    public AudioChatEntryViewModel(AudioChatEntryRepository audioChatEntryRepository) {
        this.audioChatEntryRepository = audioChatEntryRepository;

        final var initialChatEntries = audioChatEntryRepository.findAll().stream()
                .map(AudioChatEntry::getEntry)
                .toList();

        this.audioChatHistoryProperty = FXCollections.observableArrayList(initialChatEntries);
    }

    public void storeAudioChatEntry(String entry) {
        final var audioChatEntry = AudioChatEntry.builder().entry(entry).build();
        final var storedEntry = audioChatEntryRepository.save(audioChatEntry);

        audioChatHistoryProperty.add(storedEntry.getEntry());
    }
}

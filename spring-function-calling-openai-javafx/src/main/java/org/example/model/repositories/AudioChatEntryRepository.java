package org.example.model.repositories;

import org.example.model.entities.AudioChatEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioChatEntryRepository extends JpaRepository<AudioChatEntry, Integer> {
}

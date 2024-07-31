package org.example.model.repositories;

import org.example.model.entities.ChatEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEntryRepository extends JpaRepository<ChatEntry, Integer> {

}

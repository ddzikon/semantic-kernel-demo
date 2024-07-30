package org.example.model.repositories;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.model.entities.ChatEntry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChatEntryRepository {

    private final SessionFactory sessionFactory;

    public ChatEntry storeEntry(ChatEntry chatEntry) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(chatEntry);
            tx.commit();
            return chatEntry;
        }
    }

    public List<ChatEntry> findAllChatEntries() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ChatEntry order by id", ChatEntry.class).list();
        }
    }
}

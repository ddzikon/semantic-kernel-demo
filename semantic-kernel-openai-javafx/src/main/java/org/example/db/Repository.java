package org.example.db;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.gui.DataHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Repository  {

    private final SessionFactory sessionFactory;

    public Person save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(person);
            tx.commit();
            return person;
        } finally {
            refreshGuiDbState();
        }
    }

    public List<Person> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Person order by id", Person.class).list();
        }
    }

    public Person findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String query = String.format("from Person where name = %s", name);
            List<Person> results = session.createQuery(query, Person.class).list();
            if (results.isEmpty() || results.size() != 1) {
                throw new RuntimeException("multiple persons with the same name");
            }
            return results.get(0);
        }
    }

    public List<ChatEntry> storeEntry(ChatEntry chatEntry) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(chatEntry);
            tx.commit();
            return findAllChatEntries();
        } finally {
            refreshChatConversation();
        }
    }

    public List<ChatEntry> findAllChatEntries() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from ChatEntry order by id", ChatEntry.class).list();
        }
    }

    private void refreshGuiDbState() {
        DataHolder.dbStateProperty.setAll(findAll());
    }

    private void refreshChatConversation() {
        DataHolder.chatHistoryProperty.setAll(findAllChatEntries().stream()
                .map(ChatEntry::getEntry)
                .toList()
        );
    }
}

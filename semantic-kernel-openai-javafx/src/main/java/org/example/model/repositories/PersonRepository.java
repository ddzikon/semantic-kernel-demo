package org.example.model.repositories;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.model.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PersonRepository {

    private final SessionFactory sessionFactory;

    public Person save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(person);
            tx.commit();
            return person;
        }
    }

    public List<Person> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Person order by id", Person.class).list();
        }
    }

    public List<Person> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String query = String.format("from Person where name = %s", name);
            return session.createQuery(query, Person.class).list();
        }
    }
}

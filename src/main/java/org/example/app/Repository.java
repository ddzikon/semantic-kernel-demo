package org.example.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Person, Integer> {
    Person findByName(String name);
}

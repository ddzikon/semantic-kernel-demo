package org.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);
}

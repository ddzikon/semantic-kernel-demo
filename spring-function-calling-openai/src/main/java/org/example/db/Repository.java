package org.example.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Person, Integer> {

}

package com.example.memerchSpring2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    List<ClientEntity> findByNameContainsIgnoreCase(String name);

    List<ClientEntity> findBySurnameContainsIgnoreCase(String surname);

    List<ClientEntity> findByNameContainsOrSurnameContainsAllIgnoreCase(String name,
            String surname);

    // List<ClientEntity> findByNameContainsAndSurnameContainsAllIgnoreCase(String
    // name, String surname) {
    // List<ClientEntity> clientsByName = findByNameContainsIgnoreCase(name);
    // List<ClientEntity> clientsBySurname =
    // findBySurnameContainsIgnoreCase(surname);
    // clientsByName.addAll(clientsBySurname);
    // return clientsByName;
    // }

    @Query("SELECT c FROM ClientEntity c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%')) AND UPPER(c.surname) LIKE UPPER(CONCAT('%', :surname, '%'))")
    List<ClientEntity> findByFullName(String name, String surname);
}

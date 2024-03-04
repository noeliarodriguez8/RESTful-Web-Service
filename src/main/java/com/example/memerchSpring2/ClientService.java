package com.example.memerchSpring2;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientEntity> getClients() {
        return repository.findAll();
    }

    public ClientEntity create(NewClient newClient) {
        return repository.saveAndFlush(new ClientEntity(newClient.dni(), newClient.name(),
                newClient.surname(), newClient.address()));
    }

    public List<ClientEntity> search(ClientSearch clientSearch) {
        if (StringUtils.hasText(clientSearch.name()) //
                && StringUtils.hasText(clientSearch.surname())) {
            return repository.findByNameContainsOrSurnameContainsAllIgnoreCase(clientSearch.name(),
                    clientSearch.surname());
        }
        if (StringUtils.hasText(clientSearch.name())) {
            return repository.findByNameContainsIgnoreCase(clientSearch.name());
        }
        if (StringUtils.hasText(clientSearch.surname())) {
            return repository.findBySurnameContainsIgnoreCase(clientSearch.surname());
        }
        return Collections.emptyList();
    }

    public List<ClientEntity> search(UniversalSearch search) {
        ClientEntity probe = new ClientEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setSurname(search.value());
        }
        Example<ClientEntity> example = Example.of(probe,
                ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

}

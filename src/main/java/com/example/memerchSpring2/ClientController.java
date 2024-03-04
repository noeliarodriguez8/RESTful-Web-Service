package com.example.memerchSpring2;

import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class ClientController {

    private final ClientRepository repository;
    private final ClientModelAssembler assembler;
    // private static final String template = "Hello, %s!";
    // private final AtomicInteger counter = new AtomicInteger();

    ClientController(ClientRepository repository, ClientModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // @GetMapping("/welcome")
    // public ClientEntity client(@RequestParam(value = "name", defaultValue =
    // "customer") String name) {
    // return new ClientEntity(String.format(template, name));
    // }

    @GetMapping("/clients-search-name-surname")
    public List<ClientEntity> getClientsByNameAndSurname(@RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname) {
        return repository.findByFullName(name, surname);
    }

    @GetMapping("/clients-search-name")
    public List<ClientEntity> getClientsByName(@RequestParam(value = "name") String name) {
        return repository.findByNameContainsIgnoreCase(name);
    }

    // @GetMapping("/clients")
    // List<ClientEntity> all() {
    // return repository.findAll();
    // }

    @SuppressWarnings("null")
    @GetMapping("/clients")
    CollectionModel<EntityModel<ClientEntity>> all() {
        List<EntityModel<ClientEntity>> clients = repository.findAll().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }

    @SuppressWarnings("null")
    @PostMapping("/clients")
    ClientEntity newClient(@RequestBody ClientEntity newClient) {
        return repository.save(newClient);
    }

    // Single item
    // @SuppressWarnings("null")
    // @GetMapping("/clients/{clientID}")
    // ClientEntity one(@PathVariable Integer clientID) {
    // return repository.findById(clientID).orElseThrow(() -> new
    // ClientNotFoundException(clientID));
    // }

    @SuppressWarnings("null")
    @GetMapping("/clients/{clientID}")
    EntityModel<ClientEntity> one(@PathVariable Integer clientID) {
        ClientEntity client = repository.findById(clientID).orElseThrow(() -> new ClientNotFoundException(clientID));
        return assembler.toModel(client);
    }

    @SuppressWarnings("null")
    @PutMapping("/clients/{clientID}")
    ClientEntity replaceClient(@RequestBody ClientEntity newClient, @PathVariable Integer clientID) {

        return repository.findById(clientID)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setSurname(newClient.getSurname());
                    return repository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setClientID(clientID);
                    return repository.save(newClient);
                });
    }

    // @DeleteMapping("/clients/{clientID}")
    // void deleteClient(@PathVariable Integer clientID) {
    // repository.deleteById(clientID);
    // }

}

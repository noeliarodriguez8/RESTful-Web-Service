package com.example.memerchSpring2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ClientModelAssembler implements RepresentationModelAssembler<ClientEntity, EntityModel<ClientEntity>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<ClientEntity> toModel(ClientEntity client) {
        return EntityModel.of(client, //
                linkTo(methodOn(ClientController.class).one(client.getClientID())).withSelfRel(),
                linkTo(methodOn(ClientController.class).all()).withRel("clients"));
    }

}

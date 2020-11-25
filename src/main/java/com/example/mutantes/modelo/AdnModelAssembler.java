package com.example.mutantes.modelo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.mutantes.controlador.AdnController;


@Component
public class AdnModelAssembler implements RepresentationModelAssembler<Adn, EntityModel<Adn>>{

    @Override
    public EntityModel<Adn> toModel(Adn adn) {
	return EntityModel.of(adn, //
		linkTo(methodOn(AdnController.class).one(adn.getId())).withSelfRel(),
	        linkTo(methodOn(AdnController.class).all()).withRel("mutant"),
	        linkTo(methodOn(AdnController.class).stats()).withRel("stats"));
    }

}

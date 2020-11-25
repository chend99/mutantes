package com.example.mutantes.modelo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import com.example.mutantes.controlador.AdnController;

@Component
public class StatsModelAssembler implements RepresentationModelAssembler<Stats, EntityModel<Stats>> {

    @Override
    public EntityModel<Stats> toModel(Stats stats) {

	return EntityModel.of(stats, //
		linkTo(methodOn(AdnController.class).stats()).withSelfRel(),
		linkTo(methodOn(AdnController.class).all()).withRel("mutant"));
    }

}

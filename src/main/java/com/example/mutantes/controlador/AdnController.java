package com.example.mutantes.controlador;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.mutantes.excepciones.AdnNotFoundException;
import com.example.mutantes.modelo.Adn;
import com.example.mutantes.modelo.AdnModelAssembler;
import com.example.mutantes.modelo.Stats;
import com.example.mutantes.modelo.StatsModelAssembler;
import com.example.mutantes.repository.AdnRepository;
import com.example.mutantes.servicios.AdnService;
import com.example.mutantes.servicios.StatsService;

@RestController
public class AdnController {
    private final AdnRepository repositorio;
    private final AdnModelAssembler adnAssembler;
    private final StatsModelAssembler statsAssembler;

    @Autowired
    private AdnService adnService;
    @Autowired
    private StatsService statsService;

    public AdnController(AdnRepository repositorio, AdnModelAssembler adnAssembler,StatsModelAssembler statsAssembler) {
	this.repositorio = repositorio;
	this.adnAssembler = adnAssembler;
	this.statsAssembler = statsAssembler;
    }

    @Autowired
    public void setAdnService(AdnService adnService) {
	this.adnService = adnService;
    }
    @Autowired
    public void setStatsService(StatsService statsService) {
	this.statsService = statsService;
    }
    
    
    @GetMapping("/mutant")
    public CollectionModel<EntityModel<Adn>> all() {

	List<EntityModel<Adn>> employees = repositorio.findAll().stream() //
		.map(adnAssembler::toModel) //
		.collect(Collectors.toList());

	return CollectionModel.of(employees, 
		linkTo(methodOn(AdnController.class).all()).withSelfRel(),
		linkTo(methodOn(AdnController.class).stats()).withRel("stats"));
    }

    @GetMapping("/mutant/{id}")
    public EntityModel<Adn> one(@PathVariable Long id) {

	Adn adn = repositorio.findById(id) //
		.orElseThrow(() -> new AdnNotFoundException(id));

	return adnAssembler.toModel(adn);
    }
    @GetMapping("/stats")
    public ResponseEntity<?> stats(){
	EntityModel<Stats> entityModel = statsAssembler.toModel(statsService.getStats(repositorio));
	return ResponseEntity.ok(entityModel);
    }
    
    @PostMapping("/mutant")
    public ResponseEntity<?> newAdn(@RequestBody Adn newAdn) {

	if (!adnService.isValidADN(newAdn)) {
	    return ResponseEntity //
		    .status(HttpStatus.BAD_REQUEST)
		    .body(Collections.singletonMap("error", "El Adn no puede ser nulo."));
	} else {
	    adnService.setMutant(newAdn);
	    EntityModel<Adn> entityModel = adnAssembler.toModel(repositorio.save(newAdn));
	    if (!entityModel.getContent().isMutant()) {
		return ResponseEntity //
			.status(HttpStatus.FORBIDDEN).body(entityModel);
	    }
	    return ResponseEntity //
		    .ok(entityModel);
	}
    }

    @PutMapping("/mutant/{id}")
    public ResponseEntity<?> updateAdn(@RequestBody Adn newAdn, @PathVariable Long id) {

	Adn updatedAdn = repositorio.findById(id) //
		.map(adn -> {
		    adn.setAdn(newAdn.getAdn());
		    adnService.setMutant(adn);
		    return repositorio.save(adn);
		}).orElseGet(() -> {
		    newAdn.setId(id);
		    return repositorio.save(newAdn);
		});

	EntityModel<Adn> entityModel = adnAssembler.toModel(updatedAdn);

	return ResponseEntity //
		.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
		.body(entityModel);
    }

    @DeleteMapping("/mutant/{id}")
    public ResponseEntity<?> deleteAdn(@PathVariable Long id) {

	repositorio.deleteById(id);

	return ResponseEntity.noContent().build();
    }

}

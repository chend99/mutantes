package com.example.mutantes.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Adn {

    private @Id @GeneratedValue Long id;
    private String[] adn;
    private boolean mutant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getAdn() {
	return adn;
    }

    public void setAdn(String[] adn) {
	this.adn = adn;
    }

    public boolean isMutant() {
	return mutant;
    }

    public void setMutant(boolean mutant) {
	this.mutant = mutant;
    }
}

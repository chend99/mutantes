package com.example.mutantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mutantes.modelo.Adn;

public interface AdnRepository extends JpaRepository<Adn, Long> {
    double countByMutant(boolean mutant);
}

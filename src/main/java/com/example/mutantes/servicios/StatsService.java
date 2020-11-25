package com.example.mutantes.servicios;

import org.springframework.stereotype.Service;

import com.example.mutantes.modelo.Stats;
import com.example.mutantes.repository.AdnRepository;

@Service
public class StatsService {
    public Stats getStats(AdnRepository repositorio) {
	double mutantes = repositorio.countByMutant(true);  
	double total = repositorio.count();
	double ratio = mutantes/total;
	return new Stats(mutantes,total,ratio);
    }
}

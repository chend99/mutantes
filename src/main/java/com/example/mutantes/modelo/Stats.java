package com.example.mutantes.modelo;

public class Stats {
    private double count_mutant_dna, count_human_dna, ratio;

    public double getCount_mutant_dna() {
	return count_mutant_dna;
    }

    public void setCount_mutant_dna(double count_mutant_dna) {
	this.count_mutant_dna = count_mutant_dna;
    }

    public Stats(double count_mutant_dna, double count_human_dna, double ratio) {
	super();
	this.count_mutant_dna = count_mutant_dna;
	this.count_human_dna = count_human_dna;
	this.ratio = ratio;
    }

    public double getCount_human_dna() {
	return count_human_dna;
    }

    public void setCount_human_dna(double count_human_dna) {
	this.count_human_dna = count_human_dna;
    }

    public double getRatio() {
	return ratio;
    }

    public void setRatio(double ratio) {
	this.ratio = ratio;
    }
}

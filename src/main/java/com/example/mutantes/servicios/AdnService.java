package com.example.mutantes.servicios;

import org.springframework.stereotype.Service;

import com.example.mutantes.modelo.Adn;
import com.example.mutantes.util.Utility;

@Service
public class AdnService {
    private final String[] dnasMutante = { "AAAA", "TTTT", "CCCC", "GGGG" };

    public boolean isValidADN(Adn adn) {
	try {
	    if (adn == null || adn.getAdn().length <= 0) {
		return false;
	    }
	} catch (NullPointerException e) {
	    return false;
	}
	return true;
    }

    public void setMutant(Adn adn) {
	adn.setMutant(isMutant(adn.getAdn()));
    }

    private boolean isMutant(String[] adn) {
	char[][] matrizADN = Utility.arrayStringAmatrizChar(adn);

	for (String dnaMutante : dnasMutante) {
	    if (Utility.busquedaPatron(matrizADN, dnaMutante)) {
		return true;
	    }
	}

	return false;
    }
}

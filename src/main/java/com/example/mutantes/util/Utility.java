package com.example.mutantes.util;

public class Utility {

    private Utility() {
    }

    /**
     * Convierte un Array de String a una Matriz de Char.
     * 
     * @param array de string
     * @return matriz de char
     */
    public static char[][] arrayStringAmatrizChar(String[] array) {
	char[][] matriz = new char[array.length][];
	for (int i = 0; i < array.length; i++) {
	    matriz[i] = array[i].toCharArray();
	}
	return matriz;
    }

    /**
     * Busca un string en las 8 direcciones posibles.
     * 
     * @param matriz la matriz que se utilizará en la búsqueda
     * @param fila   la fila de inicio
     * @param col    la columna de inicio
     * @param busq   el string de búsqueda
     * @return <b>true</b> si se encontro, <b>false</b> si no.
     */
    private static boolean busqueda2D(char[][] matriz, int fila, int col, String busq) {
	// Para buscar en las 8 direcciones posibles
	int[] x = { -1, -1, 0, +1, +1, +1, 0, -1 };
	int[] y = { 0, -1, -1, -1, 0, +1, +1, +1 };

	// Si el primer caracter del string (busq) no coincide con el punto de inicio
	// elegido de la matriz (matriz[fila][col]) retorná falso
	if (matriz[fila][col] != busq.charAt(0))
	    return false;

	// Se empezara a buscar el string (busq) en las 8 direcciones posibles empezando
	// por (fila,col)
	for (int dir = 0; dir < 8; dir++) {

	    int aux, fd = fila + x[dir], cd = col + y[dir];

	    // Como el primer caracter del String ya fue checkeado se buscara por los
	    // caracteres restantes
	    for (aux = 1; aux < busq.length(); aux++) {
		// Si la fila/columna esta fuera de límite, salir del ciclo
		if (fd >= matriz.length || fd < 0 || cd >= matriz.length || cd < 0)
		    break;

		// Si los caracteres no son iguales, salir del ciclo
		if (matriz[fd][cd] != busq.charAt(aux))
		    break;

		// Para moverse en otra dirección
		fd += x[dir];
		cd += y[dir];
	    }
	    // Si todos los caracteres coincidieron, entonces la variable aux tiene que ser
	    // igual al largo del string (busq)
	    if (aux == busq.length())
		return true;
	}
	return false;
    }

    /**
     * Busca un string en una matriz.
     * 
     * @param matriz la matriz que se utilizara en la búsqueda
     * @param busq   el string de búsqueda
     * @return <b>true</b> si se encontro el string en la matriz, <b>false</b> si
     *         no.
     */
    public static boolean busquedaPatron(char[][] matriz, String busq) {
	// Se recorre todos las posiciones de la matriz hasta encontrar por lo menos una
	// coincidencia
	for (int fila = 0; fila < matriz.length; fila++) {
	    for (int col = 0; col < matriz.length; col++) {
		if (busqueda2D(matriz, fila, col, busq))
		    return true;
	    }
	}
	return false;
    }
}

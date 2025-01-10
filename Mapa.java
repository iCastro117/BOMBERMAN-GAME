package co.edu.konradlorenz.model;

import java.util.Random;

public class Mapa {
    private char[][] mapa;
    private int filas;
    private int columnas;
    private static final char PARED = '#';
    private static final char PARED_FIJA = '■';
    private static final char ESPACIO_VACIO = '-';

    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new char[filas][columnas];
        generarMapa();
    }

    private void generarMapa() {
        Random random = new Random();

        // Generar paredes aleatorias
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || j == 0 || i == filas - 1 || j == columnas - 1 || random.nextDouble() < 0.2) {
                    mapa[i][j] = PARED;
                } else {
                    mapa[i][j] = ESPACIO_VACIO;
                }
            }
        }

        // Generar paredes fijas en las celdas pares
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    mapa[i][j] = PARED_FIJA;
                }
            }
        }
    }

  

    public boolean esMovimientoValido(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        return mapa[fila][columna] != '#'&& mapa [fila][columna] != '■'; // No se puede mover a una posición donde hay una pared
    }

    public char getPosicion(int fila, int columna) {
        return mapa[fila][columna];
    }

    public void setElemento(int fila, int columna, char elemento) {
        mapa[fila][columna] = elemento;
    }

 
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void imprimirMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

	public void reiniciarMapa() {
		generarMapa();
		
	}
}
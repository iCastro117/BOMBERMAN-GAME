package co.edu.konradlorenz.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.konradlorenz.model.*;

public class Controlador {
	private Bueno jugadorBueno;
	private Malo jugadorMalo;
	private Mapa mapa;
	private List<Bomba> bombas;

	public Controlador(Bueno jugadorBueno, Malo jugadorMalo, Mapa mapa) {
		this.jugadorBueno = jugadorBueno;
		this.jugadorMalo = jugadorMalo;
		this.mapa = mapa;
		this.bombas = new ArrayList<>();
	}

	

	public void colocarBomba(Personaje jugador) {
		// Obtener la posición actual del jugador
		int x = jugador.getColumna();
		int y = jugador.getFila();

		// Verificar si ya hay una bomba en la posición actual
		if (!hayBombaEnPosicion(x, y)) {
			// Crear una nueva bomba y agregarla a la lista
			Bomba bomba = new Bomba(x, y, 2, 1, 3000, mapa); // Ejemplo de parámetros
			bombas.add(bomba);
			System.out.println("Bomba colocada en: (" + x + ", " + y + ")");
		}
	}

	private boolean hayBombaEnPosicion(int x, int y) {
		for (Bomba bomba : bombas) {
			if (bomba.getX() == x && bomba.getY() == y) {
				return true;
			}
		}
		return false;
	}

	public void actualizarBombas() {
		List<Bomba> bombasExplotadas = new ArrayList<>();
		for (Bomba bomba : bombas) {
			bomba.actualizar(jugadorBueno, jugadorMalo);
			if (bomba.isExplotada()) {
				bombasExplotadas.add(bomba);
				System.out.println("Bomba explotada en: (" + bomba.getX() + ", " + bomba.getY() + ")");
			}
		}
		bombas.removeAll(bombasExplotadas); // Eliminar las bombas explotadas de la lista
	}

	 // Getters
	
	public Bueno getJugadorBueno() {
		return jugadorBueno;
	}

	public Malo getJugadorMalo() {
		return jugadorMalo;
	}

	public Mapa getMapa() {
		return mapa;
	}
    public List<Bomba> getBombas() {
        return bombas;
    }
	
}

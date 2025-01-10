package co.edu.konradlorenz.model;

public abstract class Personaje implements Movible {
	protected int fila;
	protected int columna;
	protected Mapa mapa;
	protected int movimientos;
	

	public Personaje(Mapa mapa, int filaInicial, int columnaInicial) {
		this.mapa = mapa;
		this.fila = filaInicial;
		this.columna = columnaInicial;
		this.movimientos = 0;
		
	}

	public abstract void recibirDanio(int danio);

	// Getters y setters necesarios
	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int[] getRectanguloPersonaje() {
		return null;
	}
}
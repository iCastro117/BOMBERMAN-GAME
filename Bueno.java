package co.edu.konradlorenz.model;

public class Bueno extends Personaje {

	private int vidas;
	private int ancho = 55;
	private int alto = 55;

	public Bueno(Mapa mapa, int filaInicial, int columnaInicial) {
		super(mapa, filaInicial, columnaInicial);
		this.vidas = 3;
	}

	@Override
	public int[] getRectanguloPersonaje() {
		return new int[] { columna, fila, ancho, alto };
	}

	@Override
	public boolean mover(int dx, int dy) {
		int nuevaFila = fila + dy;
		int nuevaColumna = columna + dx;

		if (mapa.esMovimientoValido(nuevaFila, nuevaColumna)) {
			if (mapa.getPosicion(nuevaFila, nuevaColumna) == '■') {

			}

			mapa.setElemento(fila, columna, '-');
			mapa.setElemento(nuevaFila, nuevaColumna, '▓');
			fila = nuevaFila;
			columna = nuevaColumna;
			movimientos++;
			return true;
		} else {
			System.out.println("Movimiento inválido.");
			System.out.println();
			return false;
		}
	}

	@Override
	public void recibirDanio(int danio) {
		vidas -= 1;
		if (vidas <= 0) {
			// Lógica para cuando el jugador muere
		}
	}

	// Getters y setters necesarios
	public int getVidas() {
		return vidas;
	}

	public void reiniciar() {
		fila = 1;
		columna = 1;
		vidas = 3;
	}

}

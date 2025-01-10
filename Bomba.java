package co.edu.konradlorenz.model;

public class Bomba {
	private int x, y; // Posición de la bomba en el mapa
	private int radio; // Alcance de la explosión
	private int danio; // Daño causado por la explosión
	private long tiempoExplosion; // Tiempo en milisegundos cuando la bomba explotará
	private boolean explotada; // Estado de la bomba
	private Mapa mapa; // Referencia al mapa para actualizar el estado de la bomba en el mapa

	public Bomba(int x, int y, int radio, int danio, long tiempoExplosion, Mapa mapa) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.danio = danio;
		this.tiempoExplosion = System.currentTimeMillis() + tiempoExplosion;
		this.explotada = false;
		this.mapa = mapa;
		mapa.setElemento(y, x, 'B'); // Colocar la bomba en el mapa
	}

	public void actualizar(Bueno bueno, Malo malo) {
		long tiempoActual = System.currentTimeMillis();
		if (tiempoActual >= tiempoExplosion && !explotada) {
			explotar(bueno, malo);
		}
	}

	private void explotar(Bueno bueno, Malo malo) {
		explotada = true;
		// Actualizar el mapa eliminando la bomba
		mapa.setElemento(y, x, '-');
		// Verificar la explosión en las cuatro direcciones
		afectarCasilla(bueno, malo, x, y); // La casilla de la bomba
		for (int i = 1; i <= radio; i++) {
			afectarCasilla(bueno, malo, x + i, y); // Derecha
			afectarCasilla(bueno, malo, x - i, y); // Izquierda
			afectarCasilla(bueno, malo, x, y + i); // Abajo
			afectarCasilla(bueno, malo, x, y - i); // Arriba
		}

	}

	private void afectarCasilla(Bueno bueno, Malo malo, int x, int y) {
		// Verificar si la casilla está dentro del mapa
		if (x >= 0 && x < mapa.getColumnas() && y >= 0 && y < mapa.getFilas()) {
			// Verificar si la casilla contiene un jugador y aplicar daño
			if (mapa.getPosicion(y, x) == '▓') {
				bueno.recibirDanio(danio);
			} else if (mapa.getPosicion(y, x) == '▒') {
				malo.recibirDanio(danio);
			} else if (mapa.getPosicion(y, x) == '#' && mapa.getPosicion(y, x) != '■') { // Evitar afectar las paredes
				// Actualizar la casilla en el mapa
				mapa.setElemento(y, x, '-');
			}
		}
	}

	// Métodos getters para obtener la posición, alcance y daño de la bomba
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isExplotada() {
		return explotada;
	}

	public int getDanio() {
		return danio;
	}
	public int getRadio() {
        return radio;
    }
}
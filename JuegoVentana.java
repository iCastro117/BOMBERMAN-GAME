package co.edu.konradlorenz.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import co.edu.konradlorenz.controller.Controlador;
import co.edu.konradlorenz.model.*;

public class JuegoVentana extends JFrame {
	private Mapa mapa;
	private Bueno jugadorBueno;
	private Malo jugadorMalo;
	private ArrayList<Bomba> bombas;
	private GamePanel panelJuego;
	private Controlador controlador;
	private Timer timer;
    private GameOver gameOver;

	private JLabel vidaBuenoLabel;
	private JLabel vidaMaloLabel;

	public JuegoVentana(Mapa mapa, Bueno jugadorBueno, Malo jugadorMalo) {

		setTitle("Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(560, 600);
		setLocationRelativeTo(null);

		this.mapa = mapa;
		this.jugadorBueno = jugadorBueno;
		this.jugadorMalo = jugadorMalo;
		this.bombas = new ArrayList<>();
		this.controlador = new Controlador(jugadorBueno, jugadorMalo, mapa);

		panelJuego = new GamePanel(mapa, jugadorBueno, jugadorMalo, bombas);
		add(panelJuego, BorderLayout.CENTER);

		GamePanel panelJuego = new GamePanel(mapa, jugadorBueno, jugadorMalo, controlador.getBombas());
		add(panelJuego, BorderLayout.CENTER);

		JPanel panelVida = new JPanel();
		panelVida.setLayout(new GridLayout(1, 2));

		vidaBuenoLabel = new JLabel("Vida Bueno: " + jugadorBueno.getVidas());
		vidaMaloLabel = new JLabel("Vida Malo: " + jugadorMalo.getVidas());

		panelVida.add(vidaBuenoLabel);
		panelVida.add(vidaMaloLabel);

		add(panelVida, BorderLayout.NORTH);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_W:
					controlador.getJugadorBueno().mover(0, -1);
					break;
				case KeyEvent.VK_A:
					controlador.getJugadorBueno().mover(-1, 0);
					break;
				case KeyEvent.VK_S:
					controlador.getJugadorBueno().mover(0, 1);
					break;
				case KeyEvent.VK_D:
					controlador.getJugadorBueno().mover(1, 0);
					break;
				case KeyEvent.VK_UP:
					controlador.getJugadorMalo().mover(0, -1);
					break;
				case KeyEvent.VK_LEFT:
					controlador.getJugadorMalo().mover(-1, 0);
					break;
				case KeyEvent.VK_DOWN:
					controlador.getJugadorMalo().mover(0, 1);
					break;
				case KeyEvent.VK_RIGHT:
					controlador.getJugadorMalo().mover(1, 0);
					break;
				case KeyEvent.VK_B:
					controlador.colocarBomba(controlador.getJugadorBueno());
					break;
				case KeyEvent.VK_M:
					controlador.colocarBomba(controlador.getJugadorMalo());
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0); // Salir del juego
					break;
				}
			}
		});
		
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (controlador != null) {
						controlador.actualizarBombas();
						// Verificar si alguno de los jugadores ha perdido todas sus vidas
	                    if (controlador.getJugadorBueno().getVidas() <= 0 || controlador.getJugadorMalo().getVidas() <= 0) {
	                        mostrarGameOverDialog();
	                        timer.stop();
	                    }
	                }
				} catch (NullPointerException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				panelJuego.repaint();
			}
		});

		setFocusable(true); // Asegurar que el JFrame sea enfocable para capturar eventos de teclado
		requestFocus(); // Solicitar el foco para recibir eventos de teclado

		timer.start(); // Iniciar el temporizador para actualizar la interfaz gráfica
		setVisible(true); // Mostrar la ventana
	}

	private void mostrarGameOverDialog() {
		gameOver = new GameOver(this);
		gameOver.setVisible(true);
	}
	void reiniciarJuego() {
	    if (timer != null && timer.isRunning()) {
	        timer.stop();
	    }

	    mapa.reiniciarMapa();
	    jugadorBueno.reiniciar();
	    jugadorMalo.reiniciar();
	    bombas.clear();
	    controlador = new Controlador(jugadorBueno, jugadorMalo, mapa);
	    panelJuego.repaint();

	    timer = new Timer(100, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (controlador != null) {
	                controlador.actualizarBombas();
	                // Verificar si el juego ha terminado
	                if (controlador.getJugadorBueno().getVidas() <= 0 && controlador.getJugadorMalo().getVidas() <= 0) {
	                    mostrarGameOverDialog();
	                    timer.stop();
	                }
	            }
	            panelJuego.repaint();
	        }
	    });

	    timer.start();

	    dispose(); // Cerrar la ventana de game over
	}


	private class GamePanel extends JPanel {
		private List<Bomba> bombas;
		private Malo jugadorMalo;
		private Bueno jugadorBueno;
		private Mapa mapa;

		private static final char PARED = '#';
		private static final char PARED_FIJA = '■';
		private static final char ESPACIO_VACIO = '-';

		public GamePanel(Mapa mapa, Bueno jugadorBueno, Malo jugadorMalo, List<Bomba> bombas) {
			this.mapa = mapa;
			this.jugadorBueno = jugadorBueno;
			this.jugadorMalo = jugadorMalo;
			this.bombas = bombas;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Actualizar etiquetas de vida
			vidaBuenoLabel.setText("Vida Bueno: " + jugadorBueno.getVidas());
			vidaMaloLabel.setText("Vida Malo: " + jugadorMalo.getVidas());
			// Dibujar el mapa
			for (int i = 0; i < mapa.getFilas(); i++) {
				for (int j = 0; j < mapa.getColumnas(); j++) {
					char elemento = mapa.getPosicion(i, j);
					switch (elemento) {
					case PARED:
						g.setColor(Color.GRAY); // Color para las paredes aleatorias
						break;
					case PARED_FIJA:
						g.setColor(Color.DARK_GRAY); // Color para las paredes fijas
						break;
					case ESPACIO_VACIO:
						g.setColor(Color.WHITE); // Color para las posiciones libres
						break;
					default:
						g.setColor(Color.WHITE);
						break;
					}
					g.fillRect(j * 55, i * 55, 55, 55); // Ajusta el tamaño del bloque según sea necesario
					g.setColor(Color.BLACK);
					g.drawRect(j * 55, i * 55, 55, 55); // Dibuja el borde del bloque
				}
			}

			// Dibujar el personaje bueno
			g.setColor(Color.BLUE);
			int[] rectanguloBueno = jugadorBueno.getRectanguloPersonaje();
			g.fillRect(rectanguloBueno[0] * 55, rectanguloBueno[1] * 55, rectanguloBueno[2], rectanguloBueno[3]);

			// Dibujar el personaje malo
			g.setColor(Color.RED);
			int[] rectanguloMalo = jugadorMalo.getRectanguloPersonaje();
			g.fillRect(rectanguloMalo[0] * 55, rectanguloMalo[1] * 55, rectanguloMalo[2], rectanguloMalo[3]);

			// Dibujar las bombas
			for (Bomba bomba : bombas) {
				if (!bomba.isExplotada()) {
					g.setColor(Color.BLACK);
					g.fillOval(bomba.getX() * 55, bomba.getY() * 55, 55, 55); // Usar tamaño fijo para la bomba
				}
			}
		}
	}


	public Bueno getJugadorBueno() {
		return jugadorBueno;
	}

	public Malo getJugadorMalo() {
		return jugadorMalo;
	}


}
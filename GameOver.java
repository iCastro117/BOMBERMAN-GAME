package co.edu.konradlorenz.view;

import javax.swing.*;

import co.edu.konradlorenz.model.Bueno;
import co.edu.konradlorenz.model.Malo;
import co.edu.konradlorenz.model.Mapa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JDialog {
	private JButton reiniciarButton;
	private JButton salirButton;
	private JuegoVentana juegoVentana;

	public GameOver(JuegoVentana parent) {
		super(parent, "Game Over", true);
		this.juegoVentana = parent;
		setSize(300, 150);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());

		JLabel gameOverLabel;

		Bueno jugadorBueno = juegoVentana.getJugadorBueno();
        Malo jugadorMalo = juegoVentana.getJugadorMalo();
        
        // ganador si la vida de alguno llega a cero
        if (jugadorBueno.getVidas() <= 0) {
            gameOverLabel = new JLabel("¡Game Over! ¡El Malo ha ganado!");
        } else if (jugadorMalo.getVidas() <= 0) {
            gameOverLabel = new JLabel("¡Game Over! ¡El Bueno ha ganado!");
        } else {
            gameOverLabel = new JLabel("¡Game Over!");
        }
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOverLabel, BorderLayout.CENTER);

		reiniciarButton = new JButton("Reiniciar");
		salirButton = new JButton("Salir");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(reiniciarButton);
		buttonPanel.add(salirButton);
		add(buttonPanel, BorderLayout.SOUTH);

		reiniciarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciarJuego();
				dispose();
			}
		});

		salirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void reiniciarJuego() {
		// Cerrar el menú
		juegoVentana.dispose();

				// Crear y mostrar la ventana del juego

				Mapa mapa = new Mapa(10, 10);
				Bueno jugadorBueno = new Bueno(mapa, 1, 1);
				Malo jugadorMalo = new Malo(mapa, 8, 8);

				SwingUtilities.invokeLater(()-> {
					 new JuegoVentana(mapa, jugadorBueno, jugadorMalo);
				});
			}
}
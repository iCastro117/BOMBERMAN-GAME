package co.edu.konradlorenz.view;

import javax.swing.*;

import co.edu.konradlorenz.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuVentana extends JFrame {
	private JButton startButton;
	private JButton instrucButton;

	public MenuVentana() {
		   // Configuración de la ventana
        setTitle("Bomberman - Menu");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        
        // JLabel para el fondo
        JLabel lblFondo = new JLabel();
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Manuel Cruz\\Downloads\\Fondo5.jpg"));
        lblFondo.setBounds(0, 0, 450, 300); // Establecer el tamaño y la posición

        // Crear el botón Start
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        startButton.setBounds(175, 150, 100, 30); // Establecer el tamaño y la posición
        
     // Crear el botón Instrucciones
        instrucButton = new JButton("Instructions");
        instrucButton.setPreferredSize(new Dimension(100, 30));
        instrucButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	irInstruccionesJuego();
            }
        });
        instrucButton.setBounds(175, 205, 100, 30); // Establecer el tamaño y la posición

      

        // Añadir los elementos a la ventana
        add(lblFondo);
        add(startButton);
        add(instrucButton);
        
        // Hacer visible la ventana
        setLayout(null); // Usamos un diseño nulo para posicionar los elementos manualmente
        setVisible(true);
	}

	private void iniciarJuego()  {
		// Cerrar el menú
		dispose();

		// Crear y mostrar la ventana del juego

		Mapa mapa = new Mapa(10, 10);
		Bueno jugadorBueno = new Bueno(mapa, 1, 1);
		Malo jugadorMalo = new Malo(mapa, 8, 8);

		SwingUtilities.invokeLater(()-> {
			 new JuegoVentana(mapa, jugadorBueno, jugadorMalo);
		});
	}
	
	private void irInstruccionesJuego()  {
		// Cerrar la ventana actual
	    dispose();

	    // Crear y mostrar la ventana de instrucciones
	    SwingUtilities.invokeLater(() -> {
	        new Instrucciones();
	    });
		
	}
}
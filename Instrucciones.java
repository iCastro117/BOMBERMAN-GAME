package co.edu.konradlorenz.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import co.edu.konradlorenz.model.Bueno;
import co.edu.konradlorenz.model.Malo;
import co.edu.konradlorenz.model.Mapa;

public class Instrucciones extends JFrame {
	private JButton backButton;

	public Instrucciones() {
		   // Configuración de la ventana
        setTitle("Bomberman - Menu");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        
        // JLabel para el fondo
        JLabel lblFondo = new JLabel();
        lblFondo.setIcon(new ImageIcon("C:\\Users\\Manuel Cruz\\Downloads\\instrucciones.jpg"));
        lblFondo.setBounds(0, 0, 450, 300); // Establecer el tamaño y la posición

        // Crear el botón back
        backButton = new JButton("back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenu();
            }
        });
        backButton.setBounds(175, 220, 100, 30); // Establecer el tamaño y la posición
        
        // Añadir los elementos a la ventana
        add(lblFondo);
        add(backButton);
        
        // Hacer visible la ventana
        setLayout(null); // Usamos un diseño nulo para posicionar los elementos manualmente
        setVisible(true);
	}

	private void volverMenu()  {
		  // Cerrar la ventana actual
	    dispose();

	    // Crear y mostrar la ventana del menú principal
	    SwingUtilities.invokeLater(() -> {
	        new MenuVentana();
	    });
	}
}

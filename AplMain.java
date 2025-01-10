package co.edu.konradlorenz.controller;

import co.edu.konradlorenz.view.*;

import javax.swing.SwingUtilities;

public class AplMain {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			new MenuVentana();
		});
	}

}
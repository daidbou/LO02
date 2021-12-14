package simplifiedProjet;

import java.awt.EventQueue;

import controleur.ControleurStart;
import vue.InterfaceStart;

public class Game {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceStart window = new InterfaceStart();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

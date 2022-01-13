package simplifiedProjet;

import java.awt.EventQueue;
import java.util.concurrent.CountDownLatch;

import controleur.ControleurSetup1;
import controleur.ControleurStart;
import vue.InterfaceStart;

public class Game {
	public final static CountDownLatch latch2 = new CountDownLatch(3);

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
		try {
			latch2.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(ControleurSetup1.getCountThreadAccomplished() == ControleurSetup1.getNumReal()) {//when all real players confirm then game begins	
			Engine.getEngine().play(ControleurSetup1.getNumAllPlayer(),ControleurSetup1.getNumBot());
				
		}	
	}

}

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import simplifiedProjet.Engine;
import vue.MonInterface;

public class ControleurEngine {
	private Engine engine;
	//private MonInterface inter;
	public void ControleurEngineS(Engine engine, JButton buttonStart) {
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Engine.getEngine().player();
			}
		});
		
		
	}
	public void ControleurEngineQ(Engine engine, JButton buttonQuit) {
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
	}
	
	
	
	

}

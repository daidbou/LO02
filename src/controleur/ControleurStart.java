package controleur;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import simplifiedProjet.Engine;
import vue.InterfacePreparation;
import vue.InterfaceStart;

public class ControleurStart {
	private Engine engine;
	//private MonInterface inter;
	
	public void ControleurEngineStart(Engine engine, JButton buttonStart) {
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfacePreparation().createInterfacePre();
				InterfaceStart.getFrame().dispose();// close the actuel frame first
				
			}
		});
		//Engine.getEngine().play();
		
	}
	public void ControleurEngineQuit(Engine engine, JButton buttonQuit) {
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceStart.getFrame().dispose();//close the frame
				
			}
		});
		
	}
	
	
	
	

}

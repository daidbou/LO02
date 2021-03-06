package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import simplifiedProjet.Engine;
import vue.InterfaceSetup1;
import vue.InterfaceStart;

public class ControleurStart {
	private Engine engine;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void ControleurEngineStart(Engine engine, JButton buttonStart) {
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InterfaceSetup1().createInterfaceSetup1();
				InterfaceStart.getFrame().dispose();// close the actuel frame first
				
			}
		});
		
		
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void ControleurEngineQuit(Engine engine, JButton buttonQuit) {
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceStart.getFrame().dispose();//close the frame
				
			}
		});
		
	}
	
	
	
	

}

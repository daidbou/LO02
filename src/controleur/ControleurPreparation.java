package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.InterfacePreparation;
import vue.InterfaceStart;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simplifiedProjet.Engine;

public class ControleurPreparation {

	private Engine engine;
	private int numAllPlayer;
	private int numBot;
	
	public int getNumAllPlayer() {
		return numAllPlayer;
	}
	public void setNumAllPlayer(int numAllPlayer) {
		this.numAllPlayer = numAllPlayer;
	}
	public int getNumBot() {
		return numBot;
	}
	public void setNumBot(int numBot) {
		this.numBot = numBot;
	}
	public void controleurPreContinue(JButton btnContinue,
									JComboBox cbxAllPlayer,JComboBox cbxBot) {
		btnContinue.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			numAllPlayer = Integer.parseInt((String)cbxAllPlayer.getSelectedItem());
			numBot = Integer.parseInt((String)cbxBot.getSelectedItem());
			System.out.println(numAllPlayer+""+numBot);
			Engine.getEngine().play(numAllPlayer,numBot);
			
		}
	});
	}
	public void controleurPreQuit(JButton btnQuit) {
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfacePreparation.getFrame().dispose();
			}
		});
	}
	
}

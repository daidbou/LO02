package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.InterfaceSetup1;
import vue.InterfaceSetup2;
import vue.InterfaceStart;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import simplifiedProjet.Engine;

public class ControleurSetup1 {

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
	public void controleurSetup1Continue(JButton btnContinue,
									JComboBox cbxAllPlayer,JComboBox cbxBot) {
		btnContinue.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			numAllPlayer = Integer.parseInt((String)cbxAllPlayer.getSelectedItem());
			numBot = Integer.parseInt((String)cbxBot.getSelectedItem());
			System.out.println(numAllPlayer+""+numBot);
			//Engine.getEngine().play(numAllPlayer,numBot);
			int numReal = numAllPlayer - numBot;
			createThread(numReal);//each player has his own thread
			
		}
	});
	}
	public void controleurSetup1Quit(JButton btnQuit) {
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceSetup1.getFrame().dispose();
			}
		});
	}
	
	public void createThread(int numreal) {
		String[] str = {"p1","p2","p3","p4","p5","p6"};
		for(int i = 0; i<numreal;i++) {
			new MyThread(str[i]).start();
		}
		
	}
	public class MyThread extends Thread{

		private String pName;
		public MyThread(String pName) {
			this.pName = pName;
		}

		public void run() {
			new InterfaceSetup2(this.pName).createInterfaceSetup2(this.pName);
			
		}
		
	}
	
}

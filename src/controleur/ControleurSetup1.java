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
	
	public static MyThread[] myThreadListC;
	
	private static int numAllPlayer;
	private static int numBot;
	private static int numReal;
	private static int countThreadAccomplished;
	
	public static int getCountThreadAccomplished() {
		return countThreadAccomplished;
	}
	public static void isCountThreadAccomplished() {
		countThreadAccomplished++;
	}
	
	public static int getNumReal() {
		return numReal;
	}
	
	public static int getNumAllPlayer() {
		return numAllPlayer;
	}
	
	public static int getNumBot() {
		return numBot;
	}
	
	/**
	 * set up the how many players in the whole game
	 * @param btnContinue
	 * @param cbxAllPlayer
	 * @param cbxBot
	 */
	public void controleurSetup1Continue(JButton btnContinue,
									JComboBox<String> cbxAllPlayer,JComboBox<String> cbxBot) {
		
		btnContinue.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			InterfaceSetup1.getFrame().dispose();
			numAllPlayer = Integer.parseInt((String)cbxAllPlayer.getSelectedItem());
			numBot = Integer.parseInt((String)cbxBot.getSelectedItem());	
			numReal = numAllPlayer - numBot;
			createThread(numReal);
			}
		});
		
	}
	/**
	 * when push the button, the first frame closed
	 * @param btnQuit
	 */
	public void controleurSetup1Quit(JButton btnQuit) {
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceSetup1.getFrame().dispose();
			}
		});
	}
	
	public void createThread(int numreal) {
		String[] str = {"p1","p2","p3","p4","p5","p6"};
		myThreadListC = new MyThread[numreal];
		for(int i = 0; i<numreal;i++) {
			myThreadListC[i] = new MyThread(str[i]);// TODO 可以把setup里的player初始化也改了
			myThreadListC[i].start();
		}
		
	}
	public class MyThread extends Thread{

		private String pName;
		private InterfaceSetup2 ist2;
		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}

		private int identity;
		public int getIdentity() {
			return identity;
		}

		public void setIdentity(int identity) {
			this.identity = identity;
		}

		
		public MyThread(String pName) {
			this.pName = pName;
		}

		public void run() {
			setIst2(new InterfaceSetup2(this.pName));
			getIst2().createInterfaceSetup2(this.pName);
		}

		
		public InterfaceSetup2 getIst2() {
			return ist2;
		}

		public void setIst2(InterfaceSetup2 ist2) {
			this.ist2 = ist2;
		}
		
	}
	
}

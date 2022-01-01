package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
=======
>>>>>>> parent of 7b9fc52 (meet trouble)

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;
import vue.InterfaceRound1;

public class ControleurRound1 {
	

	private JComboBox<String> cbxPlayerList;
	private static String player2;
	private static String strChoice;
	int i = 1;
	private String pName;
	private List<Player> playerList;
	public ControleurRound1(String pName,List<Player> playerList) {
		this.pName = pName;
		this.playerList = playerList;
	}
	public void controleurRound1AorH(JToggleButton tglbtnAOrH,JPanel panelCard) {
		
			tglbtnAOrH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(i%2 == 1) {
						tglbtnAOrH.setText("Accuse");
						ControleurRound1.strChoice = "Accuse";
						panelCard.setVisible(false);
						
					}else {
						tglbtnAOrH.setText("Hunt");
						ControleurRound1.strChoice = "Hunt";
						panelCard.setVisible(true);
						
					}
					i++;
				
				}
			});
	}
	public void controleurRound1Confirm(JButton btnConfirm,JComboBox<String> cbxPlayerList) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO accuse
				//use a while loop in engine, only by receiving CONFIRM can player ACCUSE
<<<<<<< HEAD
			
				//MyThreadRound mP1 = SetUp.playerToThread(SetUp.myThreadRoundList, pName);
				//MyThreadRound mP2 = SetUp.playerToThread(SetUp.myThreadRoundList, player2);
				Player p1 = Engine.nameToPlayer(playerList, pName);
				Player p2 = Engine.nameToPlayer(playerList, player2);
				
				p1.getIr1().setStrChoice(strChoice);
				p1.getIr1().setPlayer2(player2);			
				p1.getIr1().setRumourCardName(card);
				//System.out.println(card+"2");
				p1.setLock(false);
				//frame.setVisible(false);
				//System.out.println("mp2"+" "+mP2.getPlayer().getName());
				p2.getIr1().getFrame().setVisible(false);
				p2.getIr1().getFrame().update(p2.getIr1().getFrame().getGraphics());
				p2.setAccused(true);
				//mP2.run();
=======
				for(int k = 0;k<6;k++) {
					if(SetUp.myThreadRoundList[k].getPlayer().getName().equals(pName)) {
						SetUp.myThreadRoundList[k].getIr1().setStrChoice(strChoice);
						SetUp.myThreadRoundList[k].getIr1().setPlayer2(player2);
						SetUp.myThreadRoundList[k].setLock(false);
						break;
					}
				}
>>>>>>> parent of 7b9fc52 (meet trouble)
			}
		});
	}
	public void controleurRound1PlayerList(JComboBox<String> cbxPlayerList) {	
		cbxPlayerList.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				player2 = (String) cbxPlayerList.getSelectedItem();
			}
		});
		
	}
}

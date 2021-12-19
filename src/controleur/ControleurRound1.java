package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public ControleurRound1(String pName) {
		this.pName = pName;
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
				for(int k = 0;k<6;k++) {
					if(SetUp.myThreadRoundList[k].getPlayer().getName().equals(pName)) {
						SetUp.myThreadRoundList[k].getIr1().setStrChoice(strChoice);
						SetUp.myThreadRoundList[k].getIr1().setPlayer2(player2);
						SetUp.myThreadRoundList[k].setLock(false);
						break;
					}
				}
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

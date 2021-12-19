package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import simplifiedProjet.Engine;
import simplifiedProjet.Game;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;
import vue.InterfaceRound2;
import vue.InterfaceSetup1;
import vue.InterfaceSetup2;

public class ControleurSetup2 {
	
	private int i = 1;
	private String pName;
	public ControleurSetup2(String pName) {
		this.pName = pName;
	}
	public void controleurSetUp2IdChoice(JToggleButton tgBtnIdentityChoice) {
		tgBtnIdentityChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i%2 == 1) {
					tgBtnIdentityChoice.setText("witch");
				}else {
					tgBtnIdentityChoice.setText("villager");
				}
				i++;
			}
		});
	}
	public void controleurSetup2Confirm(JButton btnConfirm,JToggleButton tgBtnIdentityChoice,JFrame frame) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControleurSetup1.isCountThreadAccomplished();
				//if player confirms, countThreadAccomplished++, until count == number of real players
				// the game begins
				
				for(int i = 0; i<ControleurSetup1.myThreadListC.length;i++) {
					if(ControleurSetup1.myThreadListC[i].getpName().equals(pName)) {
						if(tgBtnIdentityChoice.getText().equals("witch")) {
							ControleurSetup1.myThreadListC[i].setIdentity(1);
			     		}else {
			     			ControleurSetup1.myThreadListC[i].setIdentity(0);
			     			
			     		}
					}
				}
				Game.latch2.countDown();
				frame.setVisible(false);
				
			}
		});
		
	}
}

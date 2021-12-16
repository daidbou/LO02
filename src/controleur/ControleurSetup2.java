package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class ControleurSetup2 {
	
	private int i = 1;
	private String name;
	public ControleurSetup2(String name) {
		this.name = name;
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
	public void controleurSetup2Confirm(JButton btnConfirm,JToggleButton tgBtnIdentityChoice) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControleurSetup1.isCountThreadAccomplished();
				
				for(int i = 0; i<ControleurSetup1.myThreadList.length;i++) {
					if(ControleurSetup1.myThreadList[i].getpName().equals(name)) {
						if(tgBtnIdentityChoice.getText().equals("witch")) {
							ControleurSetup1.myThreadList[i].setIdentity(1);
			     		}else {
			     			ControleurSetup1.myThreadList[i].setIdentity(0);
			     			
			     		}
					}
				}
				
				if(ControleurSetup1.getCountThreadAccomplished() == ControleurSetup1.getNumReal()) {//when all real players confirm then game begins
					
					Engine.getEngine().play(ControleurSetup1.getNumAllPlayer(),ControleurSetup1.getNumBot());
					
				}
				
			}
		});
		
	}
}

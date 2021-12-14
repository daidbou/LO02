package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class ControleurSetup2 {
	private int i = 1;

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
	public void controleurSetup2Confirm(JButton btnConfirm) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
}

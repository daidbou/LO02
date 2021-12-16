package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class ControleurRound1 {

	int i = 1;
	public void controleurRound1AorH(JToggleButton tglbtnAOrH) {
		tglbtnAOrH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i%2 == 1) {
					tglbtnAOrH.setText("witch");
				}else {
					tglbtnAOrH.setText("villager");
				}
				i++;
			}
		});
		
	}
	public void controleurRound1Confirm(JButton btnConfirm) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}

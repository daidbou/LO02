package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;

import controleur.ControleurSetup2;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class InterfaceSetup2 {

	private JFrame frame;
	private JLabel lblIdentity;
	private JToggleButton tgBtnIdentityChoice;
	
	public JToggleButton getTgBtnIdentityChoice() {
		return tgBtnIdentityChoice;
	}

	public void setTgBtnIdentityChoice(JToggleButton tgBtnIdentityChoice) {
		this.tgBtnIdentityChoice = tgBtnIdentityChoice;
	}

	private int i = 1;
	private JButton btnConfirm;
	private JLabel lblPlayerName;
	private String name;
	/**
	 * Launch the application.
	 */
	public void createInterfaceSetup2(String name) {
		this.name = name;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceSetup2 window = new InterfaceSetup2(name);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceSetup2(String name) {
		initialize(name);
		ControleurSetup2 cst2 = new ControleurSetup2(name);
		cst2.controleurSetUp2IdChoice(tgBtnIdentityChoice);
		cst2.controleurSetup2Confirm(btnConfirm,tgBtnIdentityChoice);	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnConfirm = new JButton("Confirm");
		
		btnConfirm.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 23));
		btnConfirm.setBounds(211, 304, 188, 78);
		frame.getContentPane().add(btnConfirm);
		
		lblPlayerName = new JLabel(name);
		lblPlayerName.setFont(new Font("Brush Script MT", Font.PLAIN, 33));
		lblPlayerName.setBounds(126, 91, 128, 39);
		frame.getContentPane().add(lblPlayerName);
		
		
		lblIdentity = new JLabel("Which identity ");
		lblIdentity.setFont(new Font("Bookman Old Style", Font.PLAIN, 23));
		lblIdentity.setBounds(322, 54, 279, 106);
		frame.getContentPane().add(lblIdentity);
		
		tgBtnIdentityChoice = new JToggleButton("villager or Witch?");
		
		tgBtnIdentityChoice.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
		tgBtnIdentityChoice.setBackground(Color.YELLOW);
		tgBtnIdentityChoice.setBounds(75, 170, 369, 52);
		frame.getContentPane().add(tgBtnIdentityChoice);
	}
}

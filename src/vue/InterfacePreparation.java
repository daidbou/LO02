package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controleur.ControleurPreparation;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfacePreparation {

	private static JFrame frame;
	private JButton btnContinue;
	private JButton btnQuit;
	private JComboBox cbxAllPlayer;
	private JComboBox cbxBot;

	public void createInterfacePre() {
		EventQueue.invokeLater(new Runnable() {// launch the interfacePre
			public void run() {
				try {
					InterfacePreparation window = new InterfacePreparation();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public InterfacePreparation() {
		initialize();
		new ControleurPreparation().controleurPreContinue(btnContinue, cbxAllPlayer, cbxBot);
		new ControleurPreparation().controleurPreQuit(btnQuit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 970, 701);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("How many players(including bots)");
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 34));
		lblNewLabel.setBounds(107, 96, 507, 42);
		getFrame().getContentPane().add(lblNewLabel);
		
		cbxAllPlayer = new JComboBox();
		cbxAllPlayer.setFont(new Font("Arial", Font.PLAIN, 23));
		cbxAllPlayer.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6"}));
		cbxAllPlayer.setBounds(644, 96, 132, 29);
		getFrame().getContentPane().add(cbxAllPlayer);
		
		JLabel lblHowManyBots = new JLabel("How many bots");
		lblHowManyBots.setFont(new Font("Calibri Light", Font.PLAIN, 34));
		lblHowManyBots.setBounds(107, 204, 507, 42);
		getFrame().getContentPane().add(lblHowManyBots);
		
		cbxBot = new JComboBox();
		cbxBot.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6"}));
		cbxBot.setFont(new Font("Arial", Font.PLAIN, 23));
		cbxBot.setBounds(644, 199, 132, 29);
		getFrame().getContentPane().add(cbxBot);
		
		btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Arial", Font.PLAIN, 23));
		
		btnContinue.setBounds(582, 529, 238, 56);
		getFrame().getContentPane().add(btnContinue);
		
		btnQuit = new JButton("Quit");
		
		btnQuit.setFont(new Font("Arial", Font.PLAIN, 23));
		btnQuit.setBounds(107, 529, 238, 56);
		getFrame().getContentPane().add(btnQuit);
	}

	public static JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

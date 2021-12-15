package vue;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InterfaceRound1 {

	private JFrame frame;
	private String name = "name";
	private JLabel lblPlayerName;
	private JLabel lblWhichPlayer;
	private JToggleButton tglbtnAOrH;

	/**
	 * Launch the application.
	 */
	public void createInterfaceRound1() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceRound1 window = new InterfaceRound1();
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
	public InterfaceRound1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPlayerName = new JLabel(name);
		lblPlayerName.setFont(new Font("Brush Script MT", Font.PLAIN, 33));
		lblPlayerName.setBounds(126, 91, 128, 39);
		frame.getContentPane().add(lblPlayerName, BorderLayout.WEST);
		
		JToggleButton tglbtnAOrH = new JToggleButton("accuse\r\n or hunt?");
		tglbtnAOrH.setFont(new Font("Harrington", Font.PLAIN, 37));
		tglbtnAOrH.setBounds(67, 200, 329, 66);
		frame.getContentPane().add(tglbtnAOrH);
		
		lblWhichPlayer = new JLabel("Which player?");
		lblWhichPlayer.setFont(new Font("Calibri", Font.PLAIN, 29));
		lblWhichPlayer.setBounds(91, 323, 225, 60);
		frame.getContentPane().add(lblWhichPlayer);
		
		JComboBox cbxPlayerList = new JComboBox();
		cbxPlayerList.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 29));
		cbxPlayerList.setModel(new DefaultComboBoxModel(new String[] {"for example", "p1", "p2", "b1"}));
		cbxPlayerList.setBounds(285, 326, 192, 48);
		frame.getContentPane().add(cbxPlayerList);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Chiller", Font.PLAIN, 48));
		btnConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirm.setBounds(110, 443, 156, 66);
		frame.getContentPane().add(btnConfirm);
		
	}
}

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

import simplifiedProjet.Player;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class InterfaceRound1 {

	private JFrame frame;
	private String name;
	private JLabel lblPlayerName;
	private JLabel lblWhichPlayer;
	private JToggleButton tglbtnAOrH;
	private JComboBox cbxPlayerList;
	private JButton btnConfirm;
	

	/**
	 * Launch the application.
	 */
	public void createInterfaceRound1(String name,List<Player> playerList) {
		this.name = name;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					
					InterfaceRound1 window = new InterfaceRound1(name,playerList);
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
	public InterfaceRound1(String name,List<Player> playerList) {
		initialize(name,playerList);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pname,List<Player> playerList) {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPlayerName = new JLabel(name);
		
		lblPlayerName.setText(pname);
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
		
		cbxPlayerList = new JComboBox();
		cbxPlayerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cbxPlayerList.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 29));
		String[] str = new String[playerList.size()];int i = 0;
		for(Player p: playerList) {
			if(p.getName() == name) {
				i++;
			}else if(p.ifIsOutOfTurn()) {
				i++;
			}
			str[i] = p.getName();
		}
		cbxPlayerList.setModel(new DefaultComboBoxModel(str));
		cbxPlayerList.setBounds(285, 326, 192, 48);//传一个数组回来
		frame.getContentPane().add(cbxPlayerList);
		
		btnConfirm = new JButton("Confirm");
		
		btnConfirm.setFont(new Font("Chiller", Font.PLAIN, 48));
		btnConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirm.setBounds(110, 443, 156, 66);
		frame.getContentPane().add(btnConfirm);
		
	}
}

package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import simplifiedProjet.Player;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class End {

	private JFrame frame;
	private List<Player> playerList;
	private StringBuffer sb;

	/**
	 * Launch the application.
	 */
	public void createEnd(List<Player> playerList,StringBuffer sb) {
		this.playerList = playerList;
		this.sb = sb;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					End window = new End(playerList,sb);
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
	public End(List<Player> playerList,StringBuffer sb) {
		
		this.playerList = playerList;
		this.sb = sb;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String str = sb.toString();
		JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setBounds(57, 39, 520, 381);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Next Round");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(Player p: playerList){
		        	if(p.isVirtual() == 0) {
		        		p.getIr1().getFrame().setVisible(false);
		        		p.getIr1().getFrame().dispose();
		        	}
				}
			}
		});
		btnNewButton.setFont(new Font("BIZ UDMincho Medium", Font.BOLD, 31));
		btnNewButton.setBounds(136, 430, 346, 34);
		frame.getContentPane().add(btnNewButton);
	}
}

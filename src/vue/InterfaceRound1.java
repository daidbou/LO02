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

import controleur.ControleurRound1;
import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.RumourCard.RumourCard;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class InterfaceRound1 {

	private JFrame frame;
	private String pName;
	private JLabel lblPlayerName;
	private JLabel lblWhichPlayer;
	private JToggleButton tglbtnAOrH;
	private JComboBox<String> cbxPlayerList;
	private JButton btnConfirm;
	private String player2;
	private String strChoice;
	private JPanel panelCard;
	private JComboBox<String> cbxCardList;
	private JLabel lblYourCards;
	/**
	 * Launch the application.
	 */
	public void createInterfaceRound1(String pName,List<Player> playerList) {
		this.pName = pName;
		EventQueue.invokeLater(new Runnable() {		
			public void run() {
				try {
					InterfaceRound1 window = new InterfaceRound1(pName,playerList);
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
	public InterfaceRound1(String pName,List<Player> playerList) {
	
		initialize(pName,playerList);
		ControleurRound1 c = new ControleurRound1(pName);
		c.controleurRound1AorH(tglbtnAOrH,panelCard);	
		c.controleurRound1Confirm(btnConfirm,cbxPlayerList);
		c.controleurRound1PlayerList(cbxPlayerList);
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pName,List<Player> playerList) {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelCard = new JPanel();
		panelCard.setBounds(445, 136, 229, 295);
		frame.getContentPane().add(panelCard);
		panelCard.setLayout(null);
		
		cbxCardList = new JComboBox();
		cbxCardList.setBounds(37, 120, 121, 37);
		Player p2 = Engine.nameToPlayer(playerList, pName);
		int k = 0;String[] strCardList = new String[p2.getPlayerRumourCardList().size()];
		for(RumourCard r:p2.getPlayerRumourCardList()) {
			strCardList[k++] = r.name();
		}
		cbxCardList.setModel(new DefaultComboBoxModel<String>(strCardList));
		
		panelCard.add(cbxCardList);
	
		
		lblYourCards = new JLabel("your cards");
		lblYourCards.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 27));
		lblYourCards.setBounds(23, 34, 155, 37);
		panelCard.add(lblYourCards);
		
		lblPlayerName = new JLabel(pName);	
		lblPlayerName.setText(pName);
		lblPlayerName.setFont(new Font("Brush Script MT", Font.PLAIN, 33));
		lblPlayerName.setBounds(126, 91, 128, 39);
		frame.getContentPane().add(lblPlayerName, BorderLayout.WEST);
		
		tglbtnAOrH = new JToggleButton("accuse or hunt?");		
		//tglbtnAOrH.setText(strChoice);
		tglbtnAOrH.setFont(new Font("Harrington", Font.PLAIN, 34));
		tglbtnAOrH.setBounds(67, 200, 286, 66);
		frame.getContentPane().add(tglbtnAOrH);
		
		lblWhichPlayer = new JLabel("Which player?");
		lblWhichPlayer.setFont(new Font("Calibri", Font.PLAIN, 29));
		lblWhichPlayer.setBounds(67, 323, 192, 57);
		frame.getContentPane().add(lblWhichPlayer);
		
		setCbxPlayerList(new JComboBox<String>());		
		getCbxPlayerList().setFont(new Font("Bradley Hand ITC", Font.PLAIN, 29));
		String[] strPlayerList = new String[playerList.size()];int i = 0;
		for(Player p: playerList) {
			
			/*if(p.getName() == name) {
				i++;
			}else if(p.ifIsOutOfTurn()) {
				i++;
			}*/
			strPlayerList[i++] = p.getName();
		}
		getCbxPlayerList().setModel(new DefaultComboBoxModel<String>(strPlayerList));
		getCbxPlayerList().setBounds(279, 324, 121, 51);//传一个数组回来
		frame.getContentPane().add(getCbxPlayerList());
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Chiller", Font.PLAIN, 48));
		btnConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirm.setBounds(110, 443, 156, 66);
		frame.getContentPane().add(btnConfirm);
		
	}

	public String getStrChoice() {
		return strChoice;
	}

	public JComboBox<String> getCbxPlayerList() {
		return cbxPlayerList;
	}

	public void setCbxPlayerList(JComboBox<String> cbxPlayerList) {
		this.cbxPlayerList = cbxPlayerList;
	}

	public JToggleButton getTglbtnAOrH() {
		return tglbtnAOrH;
	}

	public void setTglbtnAOrH(JToggleButton tglbtnAOrH) {
		this.tglbtnAOrH = tglbtnAOrH;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2= player2;
	}

	public void setStrChoice(String strChoice) {
		this.strChoice = strChoice;
	}
}

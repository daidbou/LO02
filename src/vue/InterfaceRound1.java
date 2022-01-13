package vue;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controleur.ControleurRound1;
import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp.MyThreadRound;
import simplifiedProjet.RumourCard.RumourCard;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfaceRound1 {

	private JFrame frame;


	private String i1;

	private String pName;
	public String getpName() {
		return pName;
	}

	private String rumourCardName;
	private JLabel lblYourTurn;
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
	private JPanel panelTurn;
	private JLabel lblNewLabel;
	private MyThreadRound mt;
	private boolean onTurn;
	private List<Player> playerList;
	private JToggleButton tglbtnWOrS;
	private JPanel panel_ah;
	private Player p;
	private JLabel lblImage;
	private JPanel panel2;
	/**
	 * Launch the application.
	 */
	public void createInterfaceRound1(String pName,List<Player> playerList) {
		this.pName = pName;
		this.playerList = playerList;
		this.p = Engine.nameToPlayer(playerList, pName);
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
	
		//this.mt = mt;
		//this.onTurn = mt.getPlayer().isOnTurn();
		//System.out.println(this.onTurn);
		initialize(pName,playerList);

		ControleurRound1 c = new ControleurRound1(pName,playerList);
		c.controleurRound1AorH(tglbtnAOrH,panelCard,panel2,lblImage);	
		c.controleurRound1WorS(tglbtnWOrS,panelCard,panel2,lblImage);	
		c.controleurRound1Confirm(frame,btnConfirm,cbxPlayerList,cbxCardList);
		c.controleurRound1PlayerList(cbxPlayerList);
		c.controleurRound1CbxcardList(cbxCardList,lblImage);
		


	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pName,List<Player> playerList) {
		Player p2 = Engine.nameToPlayer(playerList, pName);

		frame = new JFrame();
		frame.setBounds(100, 100, 1015, 873);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		tglbtnWOrS = new JToggleButton("Witch or showIdentity?");
		tglbtnWOrS.setFont(new Font("Harrington", Font.PLAIN, 34));
		tglbtnWOrS.setBounds(457, 200, 397, 66);
		frame.getContentPane().add(tglbtnWOrS);
		
		panelTurn = new JPanel();
		panelTurn.repaint();
		
		panelTurn.setBounds(84, 52, 567, 66);
		frame.getContentPane().add(panelTurn);
		
		String strId = "";
		if(p2.getIdentity() == 1) {
			strId = "witch";
		}else {
			strId = "villager";
		}
		lblYourTurn = new JLabel("you are a "+strId+" and have "+p2.getPoint()+" points");
		
		lblYourTurn.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 30));
		panelTurn.add(lblYourTurn);
		
		panelCard = new JPanel();
		panelCard.setBounds(300, 442, 167, 215);
		frame.getContentPane().add(panelCard);
		panelCard.setLayout(null);
		
		cbxCardList = new JComboBox();		
		cbxCardList.setBounds(23, 146, 121, 37);
		
		lblImage = new JLabel("your card is ...");
		lblImage.setFont(new Font("Bauhaus 93", Font.PLAIN, 28));
		
		lblImage.setBounds(553, 323, 397, 472);
		frame.getContentPane().add(lblImage);
		
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
		lblPlayerName.setBounds(110, 133, 128, 39);
		frame.getContentPane().add(lblPlayerName, BorderLayout.WEST);
		
		tglbtnAOrH = new JToggleButton("accuse or hunt?");		
		tglbtnAOrH.setFont(new Font("Harrington", Font.PLAIN, 34));
		tglbtnAOrH.setBounds(67, 200, 286, 66);
		frame.getContentPane().add(tglbtnAOrH);
		
		setCbxPlayerList(new JComboBox<String>());		
		getCbxPlayerList().setFont(new Font("Bradley Hand ITC", Font.PLAIN, 29));
		
		String[] strPlayerList = new String[playerList.size()];int i = 0;
		for(Player p: playerList) {
			strPlayerList[i++] = p.getName();
		}
		
		getCbxPlayerList().setModel(new DefaultComboBoxModel<String>(strPlayerList));
		getCbxPlayerList().setBounds(110, 402, 121, 51);
		
		lblNewLabel = new JLabel("click on it!!");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		lblNewLabel.setBounds(23, 99, 141, 37);
		panelCard.add(lblNewLabel);
		
		panel2 = new JPanel();
		panel2.setBounds(32, 306, 322, 96);
		
		lblWhichPlayer = new JLabel("Which player?");
		lblWhichPlayer.setFont(new Font("Calibri", Font.PLAIN, 29));
		lblWhichPlayer.setBounds(67, 323, 192, 57);
		panel2.add(lblWhichPlayer);
		panel2.add(cbxPlayerList);
		frame.getContentPane().add(panel2);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Chiller", Font.PLAIN, 48));
		btnConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirm.setBounds(84, 546, 156, 78);
		frame.getContentPane().add(btnConfirm);
		this.rumourCardName = cbxPlayerList.getItemAt(0);//in case the player didnt choose
		//but seems not working
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

	public JPanel getPanelTurn() {
		return panelTurn;
	}
	public String getRumourCardName() {
		return rumourCardName;
	}

	public void setRumourCardName(String rumourCardName) {
		this.rumourCardName = rumourCardName;
	}
	public String getI1() {
		return i1;
	}

	public void setI1(String i1) {
		this.i1 = i1;
	}
	public JFrame getFrame() {
		return frame;
	}


	public JLabel getLblYourTurn() {
		return lblYourTurn;
	}

	public void setLblYourTurn(JLabel lblYourTurn) {
		this.lblYourTurn = lblYourTurn;
	}
}

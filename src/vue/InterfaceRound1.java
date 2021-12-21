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
	/**
	 * Launch the application.
	 */
	public void createInterfaceRound1(String pName,List<Player> playerList) {
		this.pName = pName;

		EventQueue.invokeLater(new Runnable() {		
			public void run() {
				try {
					InterfaceRound1 window = new InterfaceRound1(mt,pName,playerList);
					
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
	public InterfaceRound1(MyThreadRound mt,String pName,List<Player> playerList) {
	
		this.mt = mt;
		this.onTurn = mt.getPlayer().isOnTurn();
		//System.out.println(this.onTurn);
		initialize(pName,playerList);
		if(this.onTurn == true) {
			ControleurRound1 c = new ControleurRound1(pName);
			c.controleurRound1AorH(tglbtnAOrH,panelCard);	
			c.controleurRound1Confirm(frame,btnConfirm,cbxPlayerList);
			c.controleurRound1PlayerList(cbxPlayerList);
			c.controleurRound1CbxcardList(cbxCardList);
			c.controleurRound1Turn(frame,mt,panelTurn);
		}else {
			//TODO new c but different way
			// 让它变成灰色的，不能按的
		}

		
		
	

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String pName,List<Player> playerList) {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 717, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		panelTurn = new JPanel();
		panelTurn.repaint();
		
		panelTurn.setBounds(84, 52, 567, 66);
		frame.getContentPane().add(panelTurn);
		
		if(this.mt.getPlayer().isOnTurn()) {
			i1 = "it's your turn!";
		}else {
			i1 = "it's not your turn, you cannot do anything";
		}
		lblYourTurn = new JLabel(i1);
		lblYourTurn.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		//lblYourTurn.setVisible(false);
		lblYourTurn.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 30));
		panelTurn.add(lblYourTurn);
		
		panelCard = new JPanel();
		panelCard.setBounds(445, 142, 229, 295);
		frame.getContentPane().add(panelCard);
		panelCard.setLayout(null);
		
		cbxCardList = new JComboBox();		
		cbxCardList.setBounds(23, 146, 121, 37);
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
		lblPlayerName.setBounds(110, 133, 128, 39);
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
			strPlayerList[i++] = p.getName();
		}
		getCbxPlayerList().setModel(new DefaultComboBoxModel<String>(strPlayerList));
		getCbxPlayerList().setBounds(279, 324, 121, 51);//传一个数组回来
		frame.getContentPane().add(getCbxPlayerList());
		
		lblNewLabel = new JLabel("click on it!!");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		lblNewLabel.setBounds(23, 99, 141, 37);
		panelCard.add(lblNewLabel);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Chiller", Font.PLAIN, 48));
		btnConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		btnConfirm.setBounds(110, 443, 156, 66);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnNewButton = new JButton("update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblYourTurn.setText("fuck you");
				System.out.println("in ir1 i1 = "+i1);
				lblYourTurn.setText(i1);
				System.out.println(lblYourTurn.getText());
				frame.revalidate();
				frame.repaint();
				frame.revalidate();
			}
		});
		btnNewButton.setBounds(336, 495, 175, 66);
		frame.getContentPane().add(btnNewButton);
		this.rumourCardName = cbxPlayerList.getItemAt(0);//in case the player didnt choose
		//but seems not working
	}
	//public void re

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

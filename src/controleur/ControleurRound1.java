package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;
import simplifiedProjet.RumourCard.RumourCard;
import simplifiedProjet.SetUp.MyThreadRound;
import vue.InterfaceRound1;

public class ControleurRound1 {
	

	private JComboBox<String> cbxPlayerList;
	private String player2;
	private String strChoice;
	private String card;
	int i = 1;
	private String pName;
	private List<Player> playerList;
	public ControleurRound1(String pName,List<Player> playerList) {
		this.pName = pName;
		this.playerList = playerList;
	}
	public void controleurRound1AorH(JToggleButton tglbtnAOrH,JPanel panelCard,JPanel panel2,JLabel lblImageImage) {
		
			tglbtnAOrH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(i%2 == 1) {
						tglbtnAOrH.setText("Accuse");
						strChoice = "Accuse";
						panelCard.setVisible(false);
						lblImageImage.setVisible(false);
						
					}else {
						tglbtnAOrH.setText("Hunt");
						strChoice = "Hunt";
						panelCard.setVisible(true);	
						panel2.setVisible(true);
						lblImageImage.setVisible(true);
					}
					i++;
				
				}
			});
	}
	public void controleurRound1WorS(JToggleButton tglbtnWOrS,JPanel panelCard,JPanel panel2,JLabel lblImageImage) {
		
		tglbtnWOrS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i%2 == 1) {
					tglbtnWOrS.setText("Witch");
					strChoice = "Witch";
					panelCard.setVisible(true);
					panel2.setVisible(true);
					lblImageImage.setVisible(true);
					
				}else {
					tglbtnWOrS.setText("ShowId");
					strChoice = "ShowId";
					panelCard.setVisible(false);	
					panel2.setVisible(false);
					lblImageImage.setVisible(false);
				}
				i++;
			
			}
		});
}
	public void controleurRound1Confirm(JFrame frame,JButton btnConfirm,JComboBox<String> cbxPlayerList,
								JComboBox<String> cbxCardList) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				//TODO accuse
				//use a while loop in engine, only by receiving CONFIRM can player ACCUSE
				
				Player p1 = Engine.nameToPlayer(playerList, pName);
				Player p2 = Engine.nameToPlayer(playerList, player2);	
				int k = 0;
				String[] strCardList = new String[p1.getPlayerRumourCardList().size()];
				for(RumourCard r:p1.getPlayerRumourCardList()) {
					if(r.name().equals(card)) {
						continue;
					}
					strCardList[k++] = r.name();
				}
				cbxCardList.setModel(new DefaultComboBoxModel<String>(strCardList));
				
				p1.getIr1().setStrChoice(strChoice);
				p1.getIr1().setPlayer2(player2);			
				p1.getIr1().setRumourCardName(card);
				
				p1.setLock(false);
				p2.setAccused(true);
				
	
			}
		});
	}
	public void controleurRound1PlayerList(JComboBox<String> cbxPlayerList) {	
		cbxPlayerList.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				player2 = (String) cbxPlayerList.getSelectedItem();
			}
		});	
	}
	public void controleurRound1CbxcardList(JComboBox<String> cbxCardList,JLabel lblImage) {
		cbxCardList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card = (String)cbxCardList.getSelectedItem();
				//System.out.println(card);
				String resource = "/image/"+(String)cbxCardList.getSelectedItem()+".png";
				lblImage.setIcon(new ImageIcon(InterfaceRound1.class.getResource(resource)));
			}
		});
	}
	public void controleurRound1Turn(JFrame frame,MyThreadRound mP1,JPanel panelTurn) {
		//mP1.getIr1().getPanelTurn().setVisible(!mP1.isLock());//why not working
		//System.out.println(mP1.isOnTurn());
		/*
		panelTurn.repaint();
		if(mP1.isOnTurn()) {
			frame.setVisible(false);
			frame.update(frame.getGraphics());
			frame.paintComponents(frame.getGraphics());
		}*/
	}
	
}

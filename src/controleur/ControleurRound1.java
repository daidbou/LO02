package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;
import simplifiedProjet.SetUp.MyThreadRound;
import vue.InterfaceRound1;

public class ControleurRound1 {
	

	private JComboBox<String> cbxPlayerList;
	private String player2;
	private String strChoice;
	private String card;
	int i = 1;
	private String pName;
	public ControleurRound1(String pName) {
		this.pName = pName;
	}
	public void controleurRound1AorH(JToggleButton tglbtnAOrH,JPanel panelCard) {
		
			tglbtnAOrH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(i%2 == 1) {
						tglbtnAOrH.setText("Accuse");
						strChoice = "Accuse";
						panelCard.setVisible(false);
						
					}else {
						tglbtnAOrH.setText("Hunt");
						strChoice = "Hunt";
						panelCard.setVisible(true);					
					}
					i++;
				
				}
			});
	}
	public void controleurRound1Confirm(JFrame frame,JButton btnConfirm,JComboBox<String> cbxPlayerList) {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				//TODO accuse
				//use a while loop in engine, only by receiving CONFIRM can player ACCUSE
			
				MyThreadRound mP1 = SetUp.playerToThread(SetUp.myThreadRoundList, pName);
				MyThreadRound mP2 = SetUp.playerToThread(SetUp.myThreadRoundList, player2);
				mP1.getIr1().setStrChoice(strChoice);
				mP1.getIr1().setPlayer2(player2);			
				mP1.getIr1().setRumourCardName(card);
				//System.out.println(card+"2");
				mP1.setLock(false);
				//frame.setVisible(false);
				//System.out.println("mp2"+" "+mP2.getPlayer().getName());
				mP2.getIr1().getFrame().setVisible(false);
				mP2.getIr1().getFrame().update(mP2.getIr1().getFrame().getGraphics());
				mP2.setAccused(true);
				//mP2.run();
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
	public void controleurRound1CbxcardList(JComboBox<String> cbxCardList) {
		cbxCardList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card = (String)cbxCardList.getSelectedItem();
				//System.out.println(card+"1");
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

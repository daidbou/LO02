package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import simplifiedProjet.Engine;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;

@SuppressWarnings("deprecation")
public class MonInterface implements Observer{

	private JFrame frame;
	private JButton buttonStart;
	private JButton buttonQuit;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonInterface window = new MonInterface();
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
	public MonInterface() {
		initialize();
		//new Controleur(engine,)
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 1163, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Witch & Hunt");
		lblNewLabel.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 42));
		lblNewLabel.setBounds(276, 58, 582, 149);
		frame.getContentPane().add(lblNewLabel);
		
		buttonStart = new JButton("Start Game");
		buttonStart.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 29));
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Engine.getEngine().player();
			}
		});
		buttonStart.setBounds(343, 359, 358, 103);
		frame.getContentPane().add(buttonStart);
		
		buttonQuit = new JButton("Quit");
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				return;
			}
		});
		buttonQuit.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 33));
		buttonQuit.setBounds(418, 514, 231, 85);
		frame.getContentPane().add(buttonQuit);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

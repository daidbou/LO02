package vue;


import javax.swing.JFrame;
import controleur.ControleurStart;
import simplifiedProjet.Engine;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class InterfaceStart{

	private static JFrame frame;
	private JButton buttonStart;
	private JButton buttonQuit;
	private Engine engine;


	/**
	 * Create the application.
	 */
	public InterfaceStart() {
		initialize();
		new ControleurStart().ControleurEngineStart(engine,buttonStart);
		new ControleurStart().ControleurEngineQuit(engine,buttonQuit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		frame.getContentPane().setLayout(null);
		getFrame().getContentPane().setBackground(Color.PINK);
		getFrame().setBounds(100, 100, 1163, 705);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Witch & Hunt");
		lblNewLabel.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 42));
		lblNewLabel.setBounds(276, 58, 582, 149);
		getFrame().getContentPane().add(lblNewLabel);
		
		buttonStart = new JButton("Start Game");
		buttonStart.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 29));
		
		buttonStart.setBounds(343, 359, 358, 103);
		getFrame().getContentPane().add(buttonStart);
		
		buttonQuit = new JButton("Quit");
		
		buttonQuit.setFont(new Font("BIZ UDPMincho Medium", Font.PLAIN, 33));
		buttonQuit.setBounds(418, 514, 231, 85);
		getFrame().getContentPane().add(buttonQuit);
	}

	

	public static JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		InterfaceStart.frame = frame;
	}
}

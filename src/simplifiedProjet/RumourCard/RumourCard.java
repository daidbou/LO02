package simplifiedProjet.RumourCard;
//import simplifiedProjet.SetUp;
import simplifiedProjet.Player;
import java.util.Scanner;

/**
 *interface for rumour cards
 */
public interface RumourCard {
	
	int status = 0;// maybe for discard card
	String name = "";
	Scanner in = new Scanner(System.in);
	
	/**
	 * use the Witch? ability
	 * @param name
	 * 		name of the player which he wants to affect
	 * @return
	 */
	public Player skillWitch(String name);
	
	/**
	 * Use the Hunt! ability
	 * @param name
	 * 		name of the player which he wants to affect
	 * @return
	 */
	public Player skillHunt(String name); 
	
	/**
	 * show the card ability
	 * @return sb.toString()
	 */
	public String ToString();
	
	/**
	 * TODO
	 * @return
	 */
	public String name();
}

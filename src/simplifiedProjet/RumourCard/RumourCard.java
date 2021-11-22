package simplifiedProjet.RumourCard;
//import simplifiedProjet.SetUp;
import simplifiedProjet.Player;
import simplifiedProjet.Engine;

import java.util.List;
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
	 * 		name of the player who use this card
	 * @return
	 */
	public Player skillWitch(String name,List<Player> playerList);
	/**
	 * use the Witch? ability for bot
	 * @param name
	 * 		name of the player who use this card
	 * @return
	 */
	public Player skillWitchBot(String name,List<Player> playerList);

	
	/**
	 * Use the Hunt! ability
	 * @param name
	 * 		name of the player who use this card
	 * @return
	 */
	public Player skillHunt(String name,List<Player> playerList); 
	public Player skillHuntBot(String name2, List<Player> playerList);
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

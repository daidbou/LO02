package simplifiedProjet.RumourCard;
//import simplifiedProjet.SetUp;
import simplifiedProjet.Player;
//import simplifiedProjet.Engine;

import java.util.List;
import java.util.Scanner;

/**
 *interface for rumour cards
 */
public interface RumourCard {
	
	int status = 0;// maybe for discard card
	String nameCard = "";
	Scanner in = new Scanner(System.in);
	
	/**
	 * use the Witch? ability
	 * @param accuser
	 * 		name here is the player who accuse
	 * @return
	 */
	public Player skillWitch(String accuser,String accused,List<Player> playerList);
	
	
	/**
	 * use the Witch? ability for bot
	 * @param accuser
	 * 		name here is the player who accuse
	 * @return
	 */
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList);

	
	/**
	 * Use the Hunt! ability
	 * @param hunter is the player who use is hunter
	 * 		
	 * @return next player to take the turn
	 */
	public Player skillHunt(String hunter,List<Player> playerList); 
	
	/**
	 * 
	 * @param hunter is the player who use is hunter
	 * @param playerList List of all the player
	 * @return
	 */
	public Player skillHuntBot(String hunter, List<Player> playerList);
	
	
	/**
	 * show the card ability
	 * @return sb.toString()
	 */
	public String toString();
	
	/**
	 * return this cards name
	 * @return
	 */
	public String name();

	
}

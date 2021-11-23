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
	 * @param name
	 * 		name here is theplayer being accused
	 * @return
	 */
	public Player skillWitch(String namePTurn2,List<Player> playerList);
	
	
	/**
	 * use the Witch? ability for bot
	 * @param name
	 * 		name here is the player being accused
	 * @return
	 */
	public Player skillWitchBot(String namePTurn2,List<Player> playerList);

	
	/**
	 * Use the Hunt! ability
	 * @param name is pTurn1's name, the player who use the huntSkill
	 * 		
	 * @return
	 */
	public Player skillHunt(String namePturn1,List<Player> playerList); 
	
	
	public Player skillHuntBot(String namePturn1, List<Player> playerList);
	
	
	/**
	 * show the card ability
	 * @return sb.toString()
	 */
	public String ToString();
	
	/**
	 * return this cards name
	 * @return
	 */
	public String name();
	
}

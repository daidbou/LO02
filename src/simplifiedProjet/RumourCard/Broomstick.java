package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.Preparation;

public class Broomstick implements RumourCard{
    
    String nameCard = "Broomstick";
    
    
    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");

        sb.append("Hunt :\n");
        sb.append("Choose next player\n");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}

    
   

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {

		System.out.println("Take next turn.");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.isBroomstick();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
	
		return takeNextTurn(playerList, accuser);
		
	}
	@Override
	/**
	 * take next turn
	 */
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {

		System.out.println("Take next turn.");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.isBroomstick();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
	
		return takeNextTurn(playerList, accuser);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.isBroomstick();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return  chooseNextplayerForReal(playerList, hunter);

	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {

		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.isBroomstick();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return chooseNextPlayerForBot(playerList, hunter);
	}




	public static Player takeNextTurn(List<Player> playerList, String pCurrent){
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, pCurrent));

	}

/**
 * player choose the next player of the next round
 * @param playerList list of player in the game
 * @param pUser name of the player that choose the next player
 * @return next player
 */
	public static Player chooseNextplayerForReal(List<Player> playerList, String pUser){
		String pNextTurn = "";
        System.out.println("choose next player(p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
        Scanner sc = new Scanner(System.in);
        do{
            pNextTurn = sc.nextLine();
        }while(!Preparation.isExistedForPlayer(pUser,pNextTurn, playerList));
        return Engine.nameToPlayer(playerList, pNextTurn);
	}

/**
 * player choose the next player of the next round
 * @param playerList list of player in the game
 * @param pUser name of the player that choose the next player
 * @return next player
 */
	public static Player chooseNextPlayerForBot(List<Player> playerList, String pUser){
		int nopRandom = 0;
        String pNextTurn = "";
        do{
            nopRandom = (int)(Math.random()*playerList.size()-1);
            pNextTurn = playerList.get(nopRandom).getName();
        }while(!Preparation.isExistedForPlayer(pUser,pNextTurn, playerList));

		return Engine.nameToPlayer(playerList, pNextTurn);
	}

}

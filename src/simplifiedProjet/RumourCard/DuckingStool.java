package simplifiedProjet.RumourCard;
import simplifiedProjet.Preparation;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class DuckingStool implements RumourCard {
    String name ="Ducking Stool";
    @Override
    public Player skillWitch(String name,List<Player> playerList) {

        System.out.println("choose next player");
        String pNextTurn = in.nextLine();
        Player pMe = Preparation.isExiste(name, playerList);//returns player pMe 
        return Preparation.isExiste(pNextTurn,pMe.getName(),playerList);// in order that he cannot choose himself as player next turn
        
    }
    @Override
    public Player skillWitchBot(String name,List<Player> playerList) {

        int nopRandom = (int)(Math.random()*playerList.size());
        String pNextTurn = playerList.get(nopRandom).getName();
        Player pMe = Engine.nameToPlayer(playerList, name);
        return Preparation.isExiste(pNextTurn,pMe.getName(),playerList);

    }
 

  

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Choose a player. They must revealed their identity or discard a card from their hand\n");
        sb.append("Witch: You gain 1pt. You take newt turn\n");
        sb.append("Villager: You lose 1pt.They take next turn\n");
        sb.append("If they discard: They take next turn");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return name;
	}
   
    
    @Override
    public Player skillHunt(String name, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }
    @Override
    public Player skillHuntBot(String name2, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }

    
    
}

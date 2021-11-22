package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Wart implements RumourCard{
    
    String nameCard = "Wart";
    
   

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Choose next player");

        return sb.toString();
    }
    
    @Override
	public String name() {
		
		return nameCard;
	}

   

    @Override
    public Player skillWitch(String name, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }

    @Override
    public Player skillWitchBot(String name, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }

    @Override
    public Player skillHunt(String name, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }

    @Override
    public Player skillHuntBot(String name, List<Player> playerList) {
        System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
    }

    
}

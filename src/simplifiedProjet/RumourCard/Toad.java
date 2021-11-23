package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Toad implements RumourCard{
    
    String nameCard ="Toad";
    
   

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Reveal your identity\n");
        sb.append("Witch: Player to your left takes next turn\n");
        sb.append("Villager: Choose next player");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}

    

	@Override
	public Player skillWitch(String namePTurn1, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn1));
	}

	@Override
	public Player skillWitchBot(String namePTurn1, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn1));
	}

	@Override
	public Player skillHunt(String namePturn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn2));
	}

	@Override
	public Player skillHuntBot(String namePturn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn2));
	}
    

    
}

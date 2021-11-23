package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class HookedNose implements RumourCard{
	String nameCard = "Hooked Nose";
	

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch : \n");
		sb.append("Take one card from the hand of the player who accused you\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Choose next player\n");
		sb.append("Before their turn, take a random card from their hand and add it to your hand");

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

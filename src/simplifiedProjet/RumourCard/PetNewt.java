package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class PetNewt implements RumourCard{
    
    String nameCard ="Pet Newt";

    

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
        sb.append("Take a revealed Rumour card from any other player into your hands\n");
        sb.append("Choose next player");

        return sb.toString();
    }

    @Override
	public String name() {
		
		return nameCard;
	}

   

	@Override
	public Player skillWitch(String namePTurn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn2));
	}

	@Override
	public Player skillWitchBot(String namePTurn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn2));
	}

	@Override
	public Player skillHunt(String namePturn1, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn1));
	}

	@Override
	public Player skillHuntBot(String namePturn1, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn1));
	}
 
    
}

package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class EvilEye implements RumourCard{
    String nameCard = "Evil Eye";
    
    

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Choose next player");
        sb.append("On their turn they must accuse a player other than you, if possible\n\n");

        sb.append("Hunt : \n");
        sb.append("Choose next player\n");
        sb.append("On their turn they must accuse a player other than you, if possible");

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

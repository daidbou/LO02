package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class BlackCat implements RumourCard{
    
    String name ="Black Cat";
    
    

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt :\n");
        sb.append("Add one discarded card to your hand, and then discard this card\n");
        sb.append("Take next turn");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return name;
	}

	@Override
	public Player skillWitch(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillWitchBot(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillHunt(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillHuntBot(String name2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}
    }

package simplifiedProjet.RumourCard;


import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
//import simplifiedProjet.Preparation;
//import simplifiedProjet.Test;
//import simplifiedProjet.SetUp;


public class AngryMob implements RumourCard {

	int flag = 1;
	String name = "Angry Mob";
	public AngryMob(int flag) {
		flag = 1;
	}

	public AngryMob() {
	}





    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Witch :\n"); 
		sb.append("Take next turn\n\n");

        sb.append("Hunt :\n");
		sb.append("(Only playable if you have been revealed as a Villager)\n");
		sb.append("Reveal another player's identity\n");
		sb.append("Witch: you gain 2pts.You take next turn\n");
		sb.append("Villager: you lose 2pts. They take next turn");

        return sb.toString();
    }

	@Override
	public String name() {
		return name;
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
	public Player skillHuntBot(String name2, List<Player> playerList) {
		System.out.println("Take next turn");
		Player pNextTurn = Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
		return pNextTurn;
	}

	
	

}

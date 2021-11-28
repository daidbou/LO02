package simplifiedProjet.RumourCard;


import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
//import simplifiedProjet.Preparation;
//import simplifiedProjet.Test;
//import simplifiedProjet.SetUp;


public class AngryMob implements RumourCard {

	int flag = 1;
	String nameCard = "Angry Mob";
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
		return nameCard;
	}

	

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {

		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		//TODO Add the cards name
		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player p1 = Engine.nameToPlayer(playerList, hunter);

		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		
		Player p1 = Engine.nameToPlayer(playerList, hunter);

		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}

	
	

}

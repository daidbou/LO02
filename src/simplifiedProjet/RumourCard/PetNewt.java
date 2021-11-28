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
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {//TODO implement

		Player p1= Engine.nameToPlayer(playerList, hunter);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {

		Player p1= Engine.nameToPlayer(playerList, hunter);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}
 
    
}

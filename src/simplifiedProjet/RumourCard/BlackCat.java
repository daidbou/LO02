package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class BlackCat implements RumourCard{
    
    String nameCard ="Black Cat";
    
    

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
	public Player skillHunt(String hunted, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunted);
		p1.showDiscardedCards();
		System.out.println("Add one discarded card to your hand\n");
		Scanner sc = new Scanner(System.in);
		int selectedCardNumber = sc.nextInt();
		p1.setCardInTheList(SetUp.discardedRumourCard.get(selectedCardNumber));
		SetUp.discardedRumourCard.remove()





		
		

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn1));
	}

	@Override
	public Player skillHuntBot(String namePturn1, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePturn1));
	}
    

    

    
}

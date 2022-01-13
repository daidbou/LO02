package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class BlackCat implements RumourCard{
    
    String nameCard ="BlackCat";
    
    

    @Override
    public String toString() {
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
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		if(SetUp.discardedRumourCard.size()==0){
			System.out.println("there is no discarded card, take next turn");
			return Engine.nextPlayer(playerList, p1);
		}else{
		
			p1.showDiscardedCards();
			System.out.println("Add one discarded card to your hand\n");
			System.out.println("enter 0 for the first card");
			Scanner sc = new Scanner(System.in);
			int selectedCardNumber = sc.nextInt();
			p1.addCardInTheList(SetUp.discardedRumourCard.get(selectedCardNumber));
			SetUp.discardedRumourCard.remove(SetUp.discardedRumourCard.get(selectedCardNumber));
			//remove black cat from the player's list of rumour card
			SetUp.discardedRumourCard.add(this);
			p1.getRumourCardListPlayer().remove(this);
			return Engine.nextPlayer(playerList, p1);
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		if(SetUp.discardedRumourCard.size()==0){
			System.out.println("there is no discarded card, take next turn");
			return Engine.nextPlayer(playerList, p1);
		}
		else{
			p1.showDiscardedCards();
			int selectedCardNumber =(int)(Math.random()*(p1.getRumourCardListPlayer().size()));	
			
			p1.addCardInTheList(SetUp.discardedRumourCard.get(selectedCardNumber));
			System.out.println("choosed card "+SetUp.discardedRumourCard.get(selectedCardNumber).name());
			SetUp.discardedRumourCard.remove(SetUp.discardedRumourCard.get(selectedCardNumber));
			//remove black cat from the player list of rumour card

			SetUp.discardedRumourCard.add(this);
			p1.getRumourCardListPlayer().remove(this);
			
			return Engine.nextPlayer(playerList, p1);
		}
	}
}

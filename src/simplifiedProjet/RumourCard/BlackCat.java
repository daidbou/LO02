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
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.showDiscardedCards();
		System.out.println("Add one discarded card to your hand\n");
		Scanner sc = new Scanner(System.in);
		int selectedCardNumber = sc.nextInt();
		p1.addCardInTheList(SetUp.discardedRumourCard.get(selectedCardNumber));
		SetUp.discardedRumourCard.remove(SetUp.discardedRumourCard.get(selectedCardNumber));
		//remove black cat from the player list of rumour card
		for(RumourCard r: p1.getRumourCardListPlayer()){
			if(r.name().equals("BlackCat")){
				p1.getRumourCardListPlayer().remove(r);
			}

		}
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.showDiscardedCards();
		int selectedCardNumber =(int)(Math.random()*(p1.getRumourCardListPlayer().size()-1));	
		
		p1.addCardInTheList(SetUp.discardedRumourCard.get(selectedCardNumber));
		SetUp.discardedRumourCard.remove(SetUp.discardedRumourCard.get(selectedCardNumber));
		//remove black cat from the player list of rumour card
		// for(RumourCard r: p1.getRumourCardListPlayer()){
		// 	if(r.name().equals("BlackCat")){
		// 		p1.getRumourCardListPlayer().remove(r);
		// 	}

		// }
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}
    

    

    
}

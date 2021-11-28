package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class TheInquisition implements RumourCard {

	int flag = 2;
	
	String nameCard = "The Inquisition";
	
	

    @Override
    public String ToString() {

        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch : \n");
		sb.append("Discard a card from your hand\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Only playable if you have been revealed as a Villager\n");
		sb.append("Choose next player\n");
		sb.append("Before their turn, secretly look at their identity");

        return sb.toString();
    }

	@Override
	public String name() {
		
		return nameCard;
	}

	
	

	/**
	 * discard a card from your hand
	 */
	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Discard a card from your hand");
		int index=0;
		Player pAccused = Engine.nameToPlayer(playerList, accused);

		for (int i = 0; i < pAccused.getRumourCardListPlayer().size(); i++) {
			if(pAccused.getRumourCardListPlayer().get(i).name()!= nameCard){
				System.out.println(pAccused.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i; // this.nameCard's index in the list
			}
		}
		Scanner sc = new Scanner(System.in);
		int selectedCardNumber = sc.nextInt();
		RumourCard rumourCard;

		if(selectedCardNumber<index){
			rumourCard = pAccused.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = pAccused.getRumourCardListPlayer().get(selectedCardNumber-1);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);

		pAccused.getRumourCardListPlayer().remove(rumourCard);//remove the card chosen

		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));// remove this.cardName
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		int index=0;
		int selectedCardNumber;
		Player pAccused = Engine.nameToPlayer(playerList, accused);
		RumourCard rumourCard;

		System.out.println(pAccused.getName()+" and cards with ");
		for (int i = 0; i < pAccused.getRumourCardListPlayer().size(); i++) {
			if(pAccused.getRumourCardListPlayer().get(i).name()!= nameCard){
				System.out.println(pAccused.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i;
			}
		}

		while(true){
			selectedCardNumber = (int) Math.random()*(pAccused.getRumourCardListPlayer().size());
			if(selectedCardNumber!=index){
				break;
			}
		}

		if(selectedCardNumber<index){
			rumourCard = pAccused.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = pAccused.getRumourCardListPlayer().get(selectedCardNumber-1);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);//TODO initialize discardList
		pAccused.getRumourCardListPlayer().remove(rumourCard);

		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, pAccused);
	}

	/**
	 * choose a player and secretly see his identity
	 */
	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player nextPlayer = Broomstick.chooseNextplayerForReal(playerList, hunter);
		System.out.print("(secretly looking at "+nextPlayer.getName()+" identity)\n");
		nextPlayer.showIdentity();
		Player pHunter = Engine.nameToPlayer(playerList, hunter);
		pHunter.revealCardAndRemoveFromRumourCardList(pHunter.stringToCard(nameCard));
		return nextPlayer;
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player nextPlayer = Broomstick.chooseNextPlayerForBot(playerList, hunter);
		System.out.print("(secretly looking at "+nextPlayer.getName()+"'s identity");
		Player pHunter = Engine.nameToPlayer(playerList, hunter);
		pHunter.revealCardAndRemoveFromRumourCardList(pHunter.stringToCard(nameCard));
		nextPlayer.showIdentity();
		return nextPlayer;
	}
	
	
}

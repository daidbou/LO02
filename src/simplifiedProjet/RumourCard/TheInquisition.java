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

	
	

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Discard a card from your hand");
		int index=0;
		Player p1 = Engine.nameToPlayer(playerList, accused);

		for (int i = 0; i < p1.getRumourCardListPlayer().size(); i++) {
			if(p1.getRumourCardListPlayer().get(i).name()!= nameCard){
				System.out.println(p1.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i;
			}
		}
		Scanner sc = new Scanner(System.in);
		int selectedCardNumber = sc.nextInt();
		RumourCard rumourCard;

		if(selectedCardNumber<index){
			rumourCard = p1.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = p1.getRumourCardListPlayer().get(selectedCardNumber-1);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);
		p1.getRumourCardListPlayer().remove(rumourCard);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		int index=0;
		int selectedCardNumber;
		Player p1 = Engine.nameToPlayer(playerList, accused);
		RumourCard rumourCard;

		System.out.println(p1.getName()+" and cards with ");
		for (int i = 0; i < p1.getRumourCardListPlayer().size(); i++) {
			if(p1.getRumourCardListPlayer().get(i).name()!= nameCard){
				System.out.println(p1.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i;
			}
		}

		while(true){
			selectedCardNumber = (int) Math.random()*(p1.getRumourCardListPlayer().size());
			if(selectedCardNumber!=index){
				break;
			}
		}

		if(selectedCardNumber<index){
			rumourCard = p1.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = p1.getRumourCardListPlayer().get(selectedCardNumber);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);
		p1.getRumourCardListPlayer().remove(rumourCard);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, p1);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player nextPlayer = Broomstick.chooseNextPlayerForReal(playerList, hunter);
		System.out.print("(secretly looking at "+nextPlayer.getName()+" cards)\n");
		nextPlayer.showCards();
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return nextPlayer;
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player nextPlayer = Broomstick.chooseNextPlayerForBot(playerList, hunter);
		System.out.print("(secretly looking at "+nextPlayer.getName()+" cards");
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		nextPlayer.showCards();
		return nextPlayer;
	}
	
	
}

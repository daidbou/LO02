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
		Player accusedPlayer = Engine.nameToPlayer(playerList, accused);

		System.out.println(accusedPlayer.getName()+" and cards with ");
		for (int i = 0; i < accusedPlayer.getRumourCardListPlayer().size()-1; i++) {
			if(accusedPlayer.getRumourCardListPlayer().get(i).name()!= accusedPlayer.getName()){
				System.out.println(accusedPlayer.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i;
			}
		}
		Scanner sc = new Scanner(System.in);
		int selectedCardNumber = sc.nextInt();
		RumourCard rumourCard;

		if(selectedCardNumber<index){
			rumourCard = accusedPlayer.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = accusedPlayer.getRumourCardListPlayer().get(selectedCardNumber-1);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);
		accusedPlayer.getRumourCardListPlayer().remove(rumourCard);
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		int index=0;
		int selectedCardNumber;
		Player accusedPlayer = Engine.nameToPlayer(playerList, accused);
		RumourCard rumourCard;

		System.out.println(accusedPlayer.getName()+" and cards with xxxxxxx");
		for (int i = 0; i < accusedPlayer.getRumourCardListPlayer().size()-1; i++) {
			if(accusedPlayer.getRumourCardListPlayer().get(i).name()!= accusedPlayer.getName()){
				System.out.println(accusedPlayer.getRumourCardListPlayer().get(i).name());
			}
			else{
				index=i;
			}
		}

		while(true){
			selectedCardNumber = (int) Math.random()*(accusedPlayer.getRumourCardListPlayer().size()-1);
			if(selectedCardNumber!=index){
				break;
			}
		}

		if(selectedCardNumber<index){
			rumourCard = accusedPlayer.getRumourCardListPlayer().get(selectedCardNumber);
		}
		else{
			rumourCard = accusedPlayer.getRumourCardListPlayer().get(selectedCardNumber-1);
		}
		
		SetUp.discardedRumourCard.add(rumourCard);
		accusedPlayer.getRumourCardListPlayer().remove(rumourCard);
		
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
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

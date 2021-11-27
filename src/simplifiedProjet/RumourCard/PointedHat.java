package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class PointedHat implements RumourCard{

	int flag = 1;
	String nameCard = "Pointed Hat";
	

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch :\n ");
		sb.append("Only playable if you have a revealed Rumour card\n");
		sb.append("Take one of your own revealed Rumour cards into your hand\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Only playable if you have a revealed Rumour card\n");
		sb.append("Take one of your own revealed Rumour cards into your hand\n");
		sb.append("Choose next player");

        return sb.toString();
    }

	@Override
	public String name() {

		return nameCard;
	}

	

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, accused);
		int index = 0;

		System.out.println("Take one of your own revealed Rumour cards into your hand\n");
		p1.showPlayerRevealedList();
		Scanner sc = new Scanner(System.in);
		index = sc.nextInt();
		p1.getRumourCardListPlayer().add(p1.getPlayerRevealedCardList().get(index));
		p1.getPlayerRevealedCardList().remove(p1.getPlayerRevealedCardList().get(index));
		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList,accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		Player p1 = Engine.nameToPlayer(playerList, accused);
		int index = 0;

		System.out.println("Take one of your own revealed Rumour cards into your hand\n");
		p1.showPlayerRevealedList();
		index = (int) Math.random()*(p1.getPlayerRevealedCardList().size()-1);
		p1.getRumourCardListPlayer().add(p1.getPlayerRevealedCardList().get(index));
		p1.getPlayerRevealedCardList().remove(p1.getPlayerRevealedCardList().get(index));
		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList,accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
	
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int index = 0;

		System.out.println("Take one of your own revealed Rumour cards into your hand\n");
		p1.showPlayerRevealedList();
		Scanner sc = new Scanner(System.in);
		index = sc.nextInt();
		p1.getRumourCardListPlayer().add(p1.getPlayerRevealedCardList().get(index));
		p1.getPlayerRevealedCardList().remove(p1.getPlayerRevealedCardList().get(index));
		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Broomstick.chooseNextplayerForReal(playerList, hunter);
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int index = 0;

		System.out.println("Take one of your own revealed Rumour cards into your hand\n");
		p1.showPlayerRevealedList();
		index = (int) Math.random()*(p1.getPlayerRevealedCardList().size()-1);
		p1.getRumourCardListPlayer().add(p1.getPlayerRevealedCardList().get(index));
		p1.getPlayerRevealedCardList().remove(p1.getPlayerRevealedCardList().get(index));
		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Broomstick.chooseNextPlayerForBot(playerList, hunter);
	}
	
	
}

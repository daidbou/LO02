package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class HookedNose implements RumourCard{
	String nameCard = "Hooked Nose";
	

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch : \n");
		sb.append("Take one card from the hand of the player who accused you\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Choose next player\n");
		sb.append("Before their turn, take a random card from their hand and add it to your hand");

        return sb.toString();
    }

	@Override
	public String name() {
		
		return nameCard;
	}

	

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		Player accuserPlayer = Engine.nameToPlayer(playerList, accuser);
		Player accusedPlayer = Engine.nameToPlayer(playerList, accused);
		int cardNum;

		cardNum = (int) Math.random()*(accuserPlayer.getRumourCardListPlayer().size());
		accusedPlayer.getRumourCardListPlayer().add(accuserPlayer.getRumourCardListPlayer().get(cardNum));
		accuserPlayer.getRumourCardListPlayer().remove(accuserPlayer.getRumourCardListPlayer().get(cardNum));

		accusedPlayer.revealCardAndRemoveFromRumourCardList(accusedPlayer.stringToCard(nameCard));

		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList,accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		Player accuserPlayer = Engine.nameToPlayer(playerList, accuser);
		Player accusedPlayer = Engine.nameToPlayer(playerList, accused);
		int cardNum;

		cardNum = (int) Math.random()*(accuserPlayer.getRumourCardListPlayer().size());
		accusedPlayer.getRumourCardListPlayer().add(accuserPlayer.getRumourCardListPlayer().get(cardNum));
		accuserPlayer.getRumourCardListPlayer().remove(accuserPlayer.getRumourCardListPlayer().get(cardNum));

		accusedPlayer.revealCardAndRemoveFromRumourCardList(accusedPlayer.stringToCard(nameCard));

		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player pNextTurn = Broomstick.chooseNextplayerForReal(playerList, hunter);
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int cardNum = (int) Math.random()*(pNextTurn.getRumourCardListPlayer().size());

		p1.getRumourCardListPlayer().add(pNextTurn.getRumourCardListPlayer().get(cardNum));
		pNextTurn.getRumourCardListPlayer().remove(pNextTurn.getRumourCardListPlayer().get(cardNum));

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return pNextTurn;

	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player pNextTurn = Broomstick.chooseNextPlayerForBot(playerList, hunter);
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int cardNum = (int) Math.random()*(pNextTurn.getRumourCardListPlayer().size());

		p1.getRumourCardListPlayer().add(pNextTurn.getRumourCardListPlayer().get(cardNum));
		pNextTurn.getRumourCardListPlayer().remove(pNextTurn.getRumourCardListPlayer().get(cardNum));

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return pNextTurn;
	}
}

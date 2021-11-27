package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Cauldron implements RumourCard{
    String nameCard ="Cauldron";
   

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("The player who accused you discards a random card from their hand\n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt :\n");
        sb.append("Reveal your identity\n");
        sb.append("Witch: Player to your left takes newt turn\n");
        sb.append("Villager: Choose next player");


        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}

   

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println(accuser+" have to discard a random card");
		int cardNum = (int)(Math.random()*(Engine.nameToPlayer(playerList, accuser).getRumourCardListPlayer().size()-1));
		Engine.nameToPlayer(playerList, accuser).disCardCard(cardNum);

		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println(accuser+" have to discard a random card");
		int cardNum = (int)(Math.random()*(Engine.nameToPlayer(playerList, accuser).getRumourCardListPlayer().size()-1));
		Engine.nameToPlayer(playerList, accuser).disCardCard(cardNum);

		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player hunterPlayer = Engine.nameToPlayer(playerList, hunter);
		Player nextPlayer;

		hunterPlayer.revealIdentity();
		hunterPlayer.showIdentity();

		if(hunterPlayer.getIdentity() == 1){
			nextPlayer = Engine.leftPlayer(playerList, hunterPlayer);
			return Engine.nextPlayer(playerList, nextPlayer);
		}
		else{
			nextPlayer = Broomstick.chooseNextplayerForReal(playerList, hunter);
			return Engine.nextPlayer(playerList, nextPlayer);
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player hunterPlayer = Engine.nameToPlayer(playerList, hunter);
		Player nextPlayer;

		hunterPlayer.revealIdentity();
		hunterPlayer.showIdentity();

		if(hunterPlayer.getIdentity() == 1){
			nextPlayer = Engine.leftPlayer(playerList, hunterPlayer);
			return Engine.nextPlayer(playerList, nextPlayer);
		}
		else{
			nextPlayer = Broomstick.chooseNextPlayerForBot(playerList, hunter );
			return Engine.nextPlayer(playerList, nextPlayer);
		}
	}
}

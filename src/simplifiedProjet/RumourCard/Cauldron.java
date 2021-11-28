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
		Player p1= Engine.nameToPlayer(playerList, accused);

		int cardNum = (int)(Math.random()*(p1.getRumourCardListPlayer().size()));
		p1.disCardCard(cardNum);

		System.out.println("Take next turn");
		
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, p1);
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		Player p1= Engine.nameToPlayer(playerList, accused);

		System.out.println(accuser+" have to discard a random card");
		int cardNum = (int)(Math.random()*(p1.getRumourCardListPlayer().size()));
		p1.disCardCard(cardNum);

		System.out.println("Take next turn");
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, p1);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);


		p1.revealIdentity();
		p1.showIdentity();

		if(p1.getIdentity() == 1){
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Engine.leftPlayer(playerList, p1);
		}
		else{
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Broomstick.chooseNextplayerForReal(playerList, hunter);

		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		Player nextPlayer;

		p1.revealIdentity();
		p1.showIdentity();

		if(p1.getIdentity() == 1){
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			nextPlayer = Engine.leftPlayer(playerList, p1);
			return p1;
		}
		else{
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			nextPlayer = Broomstick.chooseNextPlayerForBot(playerList, hunter );
			return p1;
		}
	}
}

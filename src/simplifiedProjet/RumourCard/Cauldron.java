package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Cauldron implements RumourCard{
    String nameCard ="Cauldron";
   

    @Override
    public String toString() {
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
		Player pAccuser= Engine.nameToPlayer(playerList, accuser);
		if(pAccuser.getRumourCardListPlayer().size() == 0){
			System.out.println(pAccuser.getName()+"has no more rumour cards so he cannot discard a card");
		}else{
			int cardNum = (int)(Math.random()*(pAccuser.getRumourCardListPlayer().size()));
			pAccuser.disCardCard(cardNum);
		}

		Player pAccused = Engine.nameToPlayer(playerList, accused);
		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, pAccuser);
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		
		Player pAccuser= Engine.nameToPlayer(playerList, accuser);

		System.out.println(accuser+" have to discard a random card");
		
		if(pAccuser.getRumourCardListPlayer().size() == 0){
			System.out.println(pAccuser.getName()+"has no more rumour cards so he cannot discard a card");
		}else{
			int cardNum = (int)(Math.random()*(pAccuser.getRumourCardListPlayer().size()));
			pAccuser.disCardCard(cardNum);
		}
		
		Player pAccused = Engine.nameToPlayer(playerList, accused);
		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));
		System.out.println("Take next turn");

		return Engine.nextPlayer(playerList, pAccuser);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);


		p1.revealIdentity();
		p1.showIdentity();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		if(p1.getIdentity() == 1){
			return Engine.leftPlayer(playerList, p1);
		}
		else{
			return Broomstick.chooseNextPlayerForReal(playerList, hunter);
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		Player nextPlayer;

		p1.revealIdentity();
		p1.showIdentity();
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		if(p1.getIdentity() == 1){
			nextPlayer = Engine.leftPlayer(playerList, p1);
			return nextPlayer;
		}
		else{
			nextPlayer = Broomstick.chooseNextPlayerForBot(playerList, hunter );
			return nextPlayer;
		}
	}
}

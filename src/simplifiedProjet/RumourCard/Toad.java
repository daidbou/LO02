package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Toad implements RumourCard{
    
    String nameCard ="Toad";
    
   

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Reveal your identity\n");
        sb.append("Witch: Player to your left takes next turn\n");
        sb.append("Villager: Choose next player");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}

    

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));


		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		
		Player p1= Engine.nameToPlayer(playerList, hunter);

		p1.showIdentity();
		p1.revealIdentity();
		if(p1.getIdentity()==0){
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return Broomstick.chooseNextplayerForReal(playerList, hunter);
		}
		else{
			p1.setIsOutOfTurn(true);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return Engine.leftPlayer(playerList, p1);
		}

	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.showIdentity();
		p1.revealIdentity();
		if(p1.getIdentity()==0){
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return Broomstick.chooseNextPlayerForBot(playerList, hunter);
		}
		else{
			p1.setIsOutOfTurn(true);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Engine.leftPlayer(playerList, p1);
		}
	}
	}
    

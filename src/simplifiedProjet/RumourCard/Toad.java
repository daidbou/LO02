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
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Engine.nameToPlayer(playerList, hunter).showIdentity();
		Engine.nameToPlayer(playerList, hunter).revealIdentity();
		if(Engine.nameToPlayer(playerList, hunter).getIdentity()==0){
			return Broomstick.chooseNextplayerForReal(playerList, hunter);
		}
		else{
			Engine.nameToPlayer(playerList, hunter).setIsOutOfTurn(true);
			return Engine.leftPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Engine.nameToPlayer(playerList, hunter).showIdentity();
		Engine.nameToPlayer(playerList, hunter).revealIdentity();
		if(Engine.nameToPlayer(playerList, hunter).getIdentity()==0){
			return Broomstick.chooseNextPlayerForBot(playerList, hunter);
		}
		else{
			Engine.nameToPlayer(playerList, hunter).setIsOutOfTurn(true);
			return Engine.leftPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
		}
	}
	}
    

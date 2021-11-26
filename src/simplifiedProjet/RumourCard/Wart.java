package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Wart implements RumourCard{
    
    String nameCard = "Wart";
    
   

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Choose next player");

        return sb.toString();
    }
    
    @Override
	public String name() {
		
		return nameCard;
	}

   

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		Engine.nameToPlayer(playerList, accuser).isWart();
		return Broomstick.takeNextTurn(playerList, accuser);
	}

	@Override
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
		Engine.nameToPlayer(playerList, accuser).isWart();
		return Broomstick.takeNextTurn(playerList, accuser);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Engine.nameToPlayer(playerList, hunter).isWart();
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList,hunter));
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Engine.nameToPlayer(playerList, hunter).isWart();
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}
    

    
}

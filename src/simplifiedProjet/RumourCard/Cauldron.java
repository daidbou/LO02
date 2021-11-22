package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Cauldron implements RumourCard{
    String name ="Cauldron";
   

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
		
		return name;
	}

   

	@Override
	public Player skillWitch(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillWitchBot(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillHunt(String name, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}

	@Override
	public Player skillHuntBot(String name2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, name));
	}}

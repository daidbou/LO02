package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;

public class Broomstick implements RumourCard{
    
    String nameCard = "Broomstick";
    
    
    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");

        sb.append("Hunt :\n");
        sb.append("Choose next player\n");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}

    
   

	@Override
	public Player skillWitch(String namePTurn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn2));
	}
	@Override
	public Player skillWitchBot(String namePTurn2, List<Player> playerList) {
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, namePTurn2));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		System.out.println("please select one player (p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
		
		Scanner sc = new Scanner(System.in);
		String hunted = sc.nextLine();
		
		for(Player p:playerList){
			if(p.getName().equals(hunted) && !p.ifIsOutOfTurn()){
				return Engine.nameToPlayer(playerList, hunted);
			}
		}
		sc.close();

		return null; 
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Random rand = new Random();
		Player hunted = new Player();
		while(true){
			hunted = playerList.get(rand.nextInt(playerList.size()-1));
			if(!hunted.ifIsOutOfTurn()){
				return hunted;
			}}
		}
	}

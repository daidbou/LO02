package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.Preparation;

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
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {

		Engine.nameToPlayer(playerList, accuser).isBroomstick();

		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}
	@Override
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {

		Engine.nameToPlayer(playerList,accuser).isBroomstick();
		
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Engine.nameToPlayer(playerList, hunter).isBroomstick();
		

		System.out.println("please select one player (p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
		String hunted = "";
		Scanner sc = new Scanner(System.in);
		// while(Preparation.isExistedForPlayer(hunted, playerList)){
			
		// 	hunted = sc.nextLine();

		// }
		do{
			hunted = sc.nextLine();
		}while(Preparation.isExistedForPlayer(hunted,null, playerList));
		
		
		for(Player p:playerList){
			if(p.getName().equals(hunted) && !p.ifIsOutOfTurn()){
				return Engine.nameToPlayer(playerList, hunted);
			}
		}
		

		return null; 
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {

		Engine.nameToPlayer(playerList, hunter).isBroomstick();

		Random rand = new Random();
		Player hunted = new Player();
		while(true){
			hunted = playerList.get(rand.nextInt(playerList.size()-1));
			if(!hunted.ifIsOutOfTurn()){
				return hunted;
			}}
		}
	}

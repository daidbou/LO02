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
	
		return takeNextTurn(playerList, accuser);
		
	}
	@Override
	public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {

		Engine.nameToPlayer(playerList,accuser).isBroomstick();
		
		return takeNextTurn(playerList, accuser);
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Engine.nameToPlayer(playerList, hunter).isBroomstick();

		return  chooseNextplayerForReal(playerList, hunter);

	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {

		Engine.nameToPlayer(playerList, hunter).isBroomstick();

		return chooseNextPlayerForBot(playerList, hunter);
	}



	
	public static Player takeNextTurn(List<Player> playerList, String pCurrent){
		System.out.println("Take next turn");
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, pCurrent));

	}

	public static Player chooseNextplayerForReal(List<Player> playerList, String pUser){
		String pNextTurn = "";
        System.out.println("choose next player(p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
        Scanner sc = new Scanner(System.in);
        do{
            pNextTurn = sc.nextLine();
        }while(!Preparation.isExistedForPlayer(pUser,pNextTurn, playerList));
        return Engine.nameToPlayer(playerList, pNextTurn);
	}

	public static Player chooseNextPlayerForBot(List<Player> playerList, String pUser){
		int nopRandom = 0;
        String pNextTurn = "";
        do{
            nopRandom = (int)(Math.random()*playerList.size());
            pNextTurn = playerList.get(nopRandom).getName();
        }while(!Preparation.isExistedForPlayer(pUser,pNextTurn, playerList));

		return Engine.nameToPlayer(playerList, pNextTurn);
	}

}

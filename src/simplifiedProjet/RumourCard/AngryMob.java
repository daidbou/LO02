package simplifiedProjet.RumourCard;


import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
//import simplifiedProjet.Preparation;
//import simplifiedProjet.Test;
//import simplifiedProjet.SetUp;
import simplifiedProjet.Preparation;


public class AngryMob implements RumourCard {

	int flag = 1;
	String nameCard = "Angry Mob";
	public AngryMob(int flag) {
		flag = 1;
	}

	public AngryMob() {
	}





    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Witch :\n"); 
		sb.append("Take next turn\n\n");

        sb.append("Hunt :\n");
		sb.append("(Only playable if you have been revealed as a Villager)\n");
		sb.append("Reveal another player's identity\n");
		sb.append("Witch: you gain 2pts.You take next turn\n");
		sb.append("Villager: you lose 2pts. They take next turn");

        return sb.toString();
    }

	@Override
	public String name() {
		return nameCard;
	}

	

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {

		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		//TODO Add the cards name
		System.out.println("Take next turn");
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player p1 = Engine.nameToPlayer(playerList, hunter);
		Scanner sc = new Scanner(System.in);
		String hunted;

		System.out.println("Reveal another player identity : \n");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
		while(true){
			System.out.println("Select a player (p1 or b1 for example : \n");
			hunted = sc.nextLine();
			if(Preparation.isExistedForPlayer(hunted, hunter, playerList) || Preparation.isExistedForBot(hunted, hunter, playerList)){
				if(Engine.nameToPlayer(playerList, hunted).ifIsOutOfTurn()==false && Engine.nameToPlayer(playerList, hunted).getIsBroomstick()==false){
					break;
				}
				else{
					System.out.println(hunted+"is out of turn or has a revealed Broomstick RumourCard, please select another player");
					continue;
				}
			}
			else{
				System.out.println("this player doesn't exist");
				continue;
			}
		}
		Player huntedPlayer = Engine.nameToPlayer(playerList, hunted);
		huntedPlayer.revealIdentity();
		if(huntedPlayer.getIdentity()==0){
			System.out.println(hunted+" is a villager, you lose 2 points. They take next turn.");
			p1.raisePoints(-2);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return huntedPlayer;
		}
		else{
			System.out.println(hunted+" is a witch, you win 2 points. You take next turn.");
			p1.raisePoints(2);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return p1;
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int huntedNumber;
		Player huntedPlayer;

		System.out.println("Reveal another player identity : \n");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
		while(true){
			huntedNumber = 	(int) Math.random()*(playerList.size());
			huntedPlayer = playerList.get(huntedNumber);
			if(huntedPlayer.ifIsOutOfTurn()==false && huntedPlayer.getIsBroomstick()==false){
				break;
			}
			else{
				continue;
			}
		}

		huntedPlayer.revealIdentity();
		if(huntedPlayer.getIdentity()==0){
			System.out.println(huntedPlayer.getName()+" is a villager, you lose 2 points. They take next turn.");
			p1.raisePoints(-2);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return huntedPlayer;
		}
		else{
			System.out.println(huntedPlayer.getName()+" is a witch, you win 2 points. You take next turn.");
			p1.raisePoints(2);
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
			return p1;
		}
	}
}

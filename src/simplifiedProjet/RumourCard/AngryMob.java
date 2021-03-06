package simplifiedProjet.RumourCard;


import java.util.ArrayList;
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
	String nameCard = "AngryMob";
	public AngryMob(int flag) {
		flag = 1;
	}

	public AngryMob() {
	}





    @Override
    public String toString() {
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

	
	/**
	 * take next turn
	 */
	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println(accused+" use skill Witch of Card "+ nameCard);

		Player pAccused = Engine.nameToPlayer(playerList, accused);
		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));

		return Broomstick.takeNextTurn(playerList, accuser);
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println(accused+" use skill Witch of Card "+ nameCard);
		
		Player pAccused = Engine.nameToPlayer(playerList, accused);
		pAccused.revealCardAndRemoveFromRumourCardList(pAccused.stringToCard(nameCard));

		return Broomstick.takeNextTurn(playerList, accuser);
	}

	/**
	 * reveal another player's identity and gain/lose points
	 */
	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player pHunter = Engine.nameToPlayer(playerList, hunter);
		Scanner sc = new Scanner(System.in);
		String hunted;

		System.out.println("Reveal another player identity : \n");
		for(Player p: playerList){
			if(!p.ifIdentityReavealed() && !p.ifIsOutOfTurn()){
				System.out.println(p.getName());
			}else if(p.ifIdentityReavealed()){
				System.out.println(p.getName()+" has already revealed his identity ");
			}else{
				System.out.println(p.getName()+" is already out of turn ");
			}
			
		}
		while(true){
			System.out.println("Select a player (p1 or b1 for example : \n");
			hunted = sc.nextLine();
			if(Preparation.isExistedForPlayer(hunted, hunter, playerList)){
				if(!Engine.nameToPlayer(playerList, hunted).ifIsOutOfTurn() && !Engine.nameToPlayer(playerList, hunted).getIsBroomstick()){
					break;
				}
				else{
					System.out.println(hunted+"is out of turn or has a revealed Broomstick RumourCard, please select another player");

				}
			}
			else{
				System.out.println("this player doesn't exist");

			}
		}

		Player huntedPlayer = Engine.nameToPlayer(playerList, hunted);
		huntedPlayer.revealIdentity();
		if(huntedPlayer.getIdentity()==0){
			System.out.println(hunted+" is a villager, you lose 2 points. They take next turn.");
			pHunter.raisePoints(-2);
			pHunter.revealCardAndRemoveFromRumourCardList(pHunter.stringToCard(nameCard));
			return huntedPlayer;
		}
		else{
			System.out.println(hunted+" is a witch, you win 2 points. You take next turn.");
			pHunter.raisePoints(2);
			pHunter.revealCardAndRemoveFromRumourCardList(pHunter.stringToCard(nameCard));
			return pHunter;
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		int huntedNumber;
		Player huntedPlayer;
		List<Player> cards = new ArrayList<>();

		System.out.println("Reveal another player identity : \n");
		for(Player p: playerList){
			if(!p.ifIdentityReavealed() && !p.getIsBroomstick()){
				System.out.println(p.getName());
				cards.add(p);
			}
			
		}

		huntedNumber = 	(int) Math.random()*(cards.size());
		huntedPlayer = cards.get(huntedNumber);


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

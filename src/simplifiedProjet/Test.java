package simplifiedProjet;
import java.util.List;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
import java.util.Scanner;


public class Test implements Preparation{
	
	// a little hypothese for the behave of robot
	// because of that all the choice we have done is based on the strings/chars that we input
	// like sk, id...
	// so the bahave/choice of the robot should also be an inpot (randomly)
	// i think that the random input could depend on the time
	// and then we code the time into strings
	// ex: 14:11 -> take the last digit of the minutes 1 -> if it's odd then it refers to use skill witch
	// or it's even then it refers to show identity

	public static void main(String[] args) {

		
		int i = 0;
		int didChangedPlayer = 0; // 0 = didn't change , 1 = changed
		Player pNextTurn  = new Player();
		Player pTurn1 = new Player();// in every round each players has the same status, so we
		Player pTurn2 = new Player();// just put then in pTurn1 and pTurn2

		TurnStart:while(ifTurnContinue(playerList)) {
			Scanner in = new Scanner(System.in);
			if(i == playerList.size()){
				i = i - playerList.size();
			}
			if(didChangedPlayer == 0){
				if(playerList.get(i).ifIsOutOfTurn() == false){
					pTurn1 = playerList.get(i);//the first round begins at player 1
				}else{
					pTurn1 = playerList.get(++i);// if he's out of game, goes to the next player
				}
				
			}else{
				pTurn1 = pNextTurn;  
			}
			
			
			System.out.println(pTurn1.getName() + " accuse or hunt? [a/h]");
			String choiceAH = in.nextLine();

			AccuseStart:if(choiceAH.equals("a")) {
				System.out.println("which player? ex: p1 ");
				String pName = in.nextLine();
				pTurn2 = Preparation.isExiste(pName,pTurn1, playerList);
				if(pTurn2 == null){
					continue TurnStart;
				}

			
				if (pTurn2.ifIdentityReavealed() == false){
					pTurn1.accuse(pTurn2);
				}else{
					System.out.println(pTurn2.getName() + " has already revealed his identity, try again");
					continue TurnStart;
				}
				

				System.out.println(pTurn2.getName() + " skill witch or show identity? [sk/id]");
				String choiceWS = in.nextLine();// scan the choise: skillWitch or show id

				if(choiceWS.equals("sk")){//choose to use skill witch
					System.out.println(" Which card do you want to use?");
					pTurn2.showCards();//shows what cards do you have
					System.out.println(" entre 0 for the first card");
					int cardNum = in.nextInt();//let player choose which card to use
					pNextTurn = pTurn2.witch(cardNum);// use witch skill
					if(pNextTurn == null){							
						//pNextTurn = playerList.get(++i);// null means take next turn	
						++i;
					}else{
						pNextTurn = Preparation.isExiste(pNextTurn.getName(), playerList);//turn to the chosen player 
						didChangedPlayer = 1;	
					}
					
				}else if(choiceWS.equals("id")){//choose to show identity
					pTurn2.showIdentity();
					pTurn2.revealIdentity();
					if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
						pTurn1.raisePoints(1);
						System.out.println(pTurn1.getName() + " gains 1 point");
						pTurn2.setIsOutOfTurn(true);
						//pTurn2 should left the game
						
					}else{
						pTurn1.raisePoints(0);//villager , gain no point;
						System.out.println(pTurn1.getName() + " gains 0 point");
					}
					//pNextTurn = playerList.get(++i);
					++i;
				}

				
			}else if(choiceAH.equals("h")) {
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = Preparation.isExiste(pName,pTurn1, playerList);
				if(pTurn2!=null){
				
					System.out.println(" Which card do you want to use?");
					pTurn1.showCards();//shows what cards do you have
					System.out.println(" Entre 0 for the first card");
					int cardNum = in.nextInt();//let player choose which card to use
					pNextTurn = pTurn1.hunt(cardNum);// use hunt skill
					if(pNextTurn == null){							
						//pNextTurn = playerList.get(++i);// null means take next turn	
						++i;
					}else{
						pNextTurn = Preparation.isExiste(pNextTurn.getName(), playerList);//turn to the chosen player 
						didChangedPlayer = 1;	
					}
				}else{
					System.out.println("no such player");
				}
			}else{
				System.out.println("noooooo");
				continue TurnStart;
			}
			//System.out.println(ifTurnContinue(playerList));
			if(ifTurnContinue(playerList)==false){ // if this round ends , we should score points
				System.out.println(" this turn is over ");
				for(Player p: playerList){
					if(p.ifIdentityReavealed() == false){
						if(p.getIdentity() == 1){ // he is a witch
							p.raisePoints(2); // get two points
						}else{// villager
							p.raisePoints(1);
						}	
					}
					System.out.println(p.getName() + " has " + p.getPoint());
				}
			}
			
		}
		//in.cloes();
	}




	public static boolean ifGameContinue(List<Player> pAll){
		for(Player p: pAll){
			if(p.getPoint()>=5){
				return false;
			}
		}
		return true;

    }
	
	public static boolean ifTurnContinue(List<Player> pAll){
		int i = pAll.size();// how many players are still playing
		//System.out.println("size"+pAll.size());
		for(Player p: pAll){
			if(p.ifIdentityReavealed() == true){

				i--; // count how many players didn't revealed their identity
			}
		}
		if (i == 1){
			return false; // if there is only one , the end game
		}else{
			return true;
		}
		
	}


	
}


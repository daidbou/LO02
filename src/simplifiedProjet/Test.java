package simplifiedProjet;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
import java.util.Scanner;


public class Test implements Preparation{

	public static void main(String[] args) {

		int turns = 1;
		int i = 0;
		while(turns<3) {
			Scanner in = new Scanner(System.in);
			
			Player pNextTurn = playerList.get(i);//the first round begins at player 1
			Player pTurn1 = pNextTurn;  // in every round each players has the same status, so we
			Player pTurn2;		 // just put then in pTurn1 and pTurn2
			
			System.out.println(pTurn1.getName() + " accuse or hunt? [a/h]");
			String choiceAH = in.nextLine();
			if(choiceAH.equals("a")) {
				System.out.println("which player? ex: p1 ");
				String pName = in.nextLine();
				pTurn2 = Preparation.isExiste(pName,pTurn1, playerList);
				pTurn1.accuse(pTurn2);

				System.out.println(pTurn2.getName() + " skill witch or show identity? [sk/id]");
				String choiceWS = in.nextLine();// scan the choise: skillWitch or show id

				if(choiceWS.equals("sk")){//choose to use skill witch
					System.out.println("Which card do you want to use?");
					pTurn2.showCards();//shows what cards do you have
					System.out.println("entre 0 for the first card");
					int cardNum = in.nextInt();//let player choose which card to use
					pNextTurn = pTurn2.witch(cardNum);// use witch skill
					if(pNextTurn == null){
		
						pNextTurn = playerList.get(++i);// null means take next turn
						//System.out.println("did take next turn");
						//System.out.println(pNextTurn.getName());
					}else{
						pNextTurn = Preparation.isExiste(pNextTurn.getName(), null, playerList);//turn to the player chosen
					}
					
				}else if(choiceWS.equals("id")){//choose to show identity
					pTurn2.showIdentity();
					if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
						pTurn1.raisePoints(1);
						playerList.remove(Preparation.isExiste(pTurn2.getName(), null, playerList));//pTurn2 should left the game
						
					}else{
						pTurn1.raisePoints(0);//villager , gain no point;
					}
					pNextTurn = playerList.get(++i);

				}

				
			}else if(choiceAH.equals("h")) {
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = Preparation.isExiste(pName,pTurn1, playerList);
				if(pTurn2!=null){
					pTurn1.hunt(pTurn2);
				}else{
					System.out.println("bug");
				}
			}else{
				System.out.println("noooooo");
			}
			
			//in.close();
		}
	}
	


	
}


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
		int didChangedPlayer = 0; // 0 = didn't change , 1 = changed
		Player pNextTurn  = new Player();
		Player pTurn1 = new Player();// in every round each players has the same status, so we
		Player pTurn2 = new Player();// just put then in pTurn1 and pTurn2
		while(turns<3) {
			Scanner in = new Scanner(System.in);
	
			if(didChangedPlayer == 0){
				pTurn1 = playerList.get(i);//the first round begins at player 1
			}else{
				pTurn1 = pNextTurn;  
			}
			
			
			
			
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
					//System.out.println("pNextTurn is " + pNextTurn.getName());
					if(pNextTurn == null){							
						pNextTurn = playerList.get(++i);// null means take next turn	
					}else{
						pNextTurn = Preparation.isExiste(pNextTurn.getName(), playerList);//turn to the chosen player 
						didChangedPlayer = 1;	
					}
					
				}else if(choiceWS.equals("id")){//choose to show identity
					pTurn2.showIdentity();
					if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
						pTurn1.raisePoints(1);
						System.out.println(pTurn2.getName() + "gains 1 point");
						playerList.remove(Preparation.isExiste(pTurn2.getName(), playerList));//pTurn2 should left the game
						
					}else{
						pTurn1.raisePoints(0);//villager , gain no point;
						System.out.println(pTurn2.getName() + "gains 0 point");
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


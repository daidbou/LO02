package simplifiedProjet;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
import java.util.Scanner;


public class Test extends Preparation{

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
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = isExiste(pName,pTurn1, playerList);
				pTurn1.accuse(pTurn2);

				System.out.println(pTurn2.getName()+" skill witch or show identity? [sk/id]");
				String choiceWS = in.nextLine();

				if(choiceWS.equals("sk")){//choose to use skill witch

					System.out.println("Which card do you want to use?");
					pTurn2.showCards();
					System.out.println("entre 0 for the first card");
					int cardNum = in.nextInt();//let player choose which card to use
					pNextTurn = pTurn2.witch(cardNum);
					if(pNextTurn == null){
						//if(i)
						pNextTurn = playerList.get(++i);// null means take next turn
						//System.out.println("did take next turn");
						//System.out.println(pNextTurn.getName());
					}else{
						pNextTurn = isExiste(pNextTurn.getName(), null, playerList);//turn to the player chosen
					}
					
				}else if(choiceWS.equals("id")){//choose to show identity

					pTurn2.showIdentity();
					if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 2 points
						pTurn1.raisePoints();
						pTurn1.raisePoints();
					}else{
						pTurn1.raisePoints();//villager raise 1 points;
					}

				}

				
			}else if(choiceAH.equals("h")) {
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = isExiste(pName,pTurn1, playerList);
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


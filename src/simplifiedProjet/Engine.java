package simplifiedProjet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine implements Preparation {
    public static void main(String[] args){
        

        List<Player> playerListInit = new ArrayList<Player>();

        GameStart:while(ifGameContinue(playerListInit)){
            List<Player> playerList = SetUp.setUpPlayer();
           
            
            Player pTurn1 = new Player();
            Player pTurn2 = new Player();
            Player pNextTurn  = playerList.get(0);

            TurnStart:while(ifTurnContinue(playerList)){

                pTurn1 = pNextTurn;
                if( ((pTurn1.isVirtual() == 1) && doChoiceAh_Bot(pTurn1))  || ((pTurn1.isVirtual() == 0) && doChoiceAH_Real(pTurn1)) ){
                    //accuse
                    pTurn2 = pTurn1.accuse(playerList);
                    if(((pTurn2.isVirtual() == 1) && doChoiceWI_Bot(pTurn2))  || ((pTurn2.isVirtual() == 0) && doChoiceWI_Real(pTurn2))){
                        //pTurn2.showIdentity();
                        //System.out.println(pTurn2.isVirtual());
                        System.out.println("bot choose WI"+doChoiceWI_Bot(pTurn2));
                        pNextTurn = pTurn2.witch(playerList);
                        System.out.println("pNextPlayer = "+pNextTurn.getName());
                        
                    }else{
                        pTurn2.showIdentity();
                        pTurn2.revealIdentity();
                        if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
                            pTurn1.raisePoints(1);
                            System.out.println(pTurn1.getName() + " gains 1 point");
                            pTurn2.setIsOutOfTurn(true); //pTurn2 should left the game
                           
                        
                        }else{
                            pTurn1.raisePoints(0);//villager , gain no point;
                            System.out.println(pTurn1.getName() + " gains 0 point");
                            
                        }
                        pNextTurn = nextPlayer(playerList,pTurn2);
                        System.out.println("pNextPlayer = "+pNextTurn.getName());
                    }
                }else{
                    pTurn2 = pTurn1.hunt(playerList);
                    pNextTurn = pTurn2;
                    System.out.println("pNextPlayer = "+pNextTurn.getName());
                }
                //System.out.println("pNextPlayer = "+pNextTurn);
                
                
            }
        }
    }

    public static boolean doChoiceWI_Real(Player pTurn1) {
        Scanner in = new Scanner(System.in);
        System.out.println(pTurn1.getName() + " SkillWitch or ShowIdentity? [sk/id]");
        String choiceAH_Real = in.nextLine();
        //in.close();
        if (choiceAH_Real.equals("sk")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean doChoiceAH_Real(Player pTurn1) {
        Scanner in = new Scanner(System.in);
        System.out.println(pTurn1.getName() + " accuse or hunt? [a/h]");
        String choiceAH_Real = in.nextLine();
        //in.close();
        if (choiceAH_Real.equals("a")) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * For a bot to choose randomly accuse or hunt
    * @param pTurn1
    * 		it's the bot who use this fonction
    * @return  a boolean
    *      the choice by random
    *      if the random int > 1 then returns accuse
    *      else returns Hunt 
    */
    public static boolean doChoiceAh_Bot(Player pTurn1) {
        double ChoiceAh_Bot = (Math.random() + 0.5);
        if (ChoiceAh_Bot > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean doChoiceWI_Bot(Player pTurn1) { //witch or show id
        double ChoiceWI_Bot = (Math.random() + 0.5);
        //System.out.println("bot choiceWI " +ChoiceWI_Bot );
        if (ChoiceWI_Bot > 1) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean ifGameContinue(List<Player> pAll) {
        for (Player p : pAll) {
            if (p.getPoint() >= 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean ifTurnContinue(List<Player> pAll) {
        int i = pAll.size();// how many players are still playing
        // System.out.println("size"+pAll.size());
        for (Player p : pAll) {
            if (p.ifIdentityReavealed() == true) {
                i--; // count how many players didn't revealed their identity
            }
        }
        if (i == 1) {
            return false; // if there is only one , the end game
        } else {
            return true;
        }
    }

    /**
	 * returns next player in the list of p, which is well examined
	 * @param playerList
	 * 		the list you want
     * @param p
     *      the current player
	 * @return
	 */
    public static Player nextPlayer(List<Player> playerList, Player p){
        int index = 0;
		for(int i = 0; i < playerList.size();i++){
			if(playerList.get(i).getName().equals(p.getName())){
				index = i;
				break;
			}
		}
        System.out.println("index = "+index);
        if(index == playerList.size()-1){
            index = 0;              //problem
        }
		Player ppNextTurn = playerList.get(++index);
		while(ppNextTurn.ifIsOutOfTurn()){
	
			ppNextTurn = Preparation.isExiste(ppNextTurn.getName(), p.getName(),playerList);
		}
		return ppNextTurn;
    }
    public static Player nameToPlayer(List<Player> playerList, String name){
        int index = 0;
		for(int i = 0; i <playerList.size();i++){
			if(playerList.get(i).getName().equals(name)){
				index = i;
				break;
			}
		}
		return playerList.get(index);

    }

}

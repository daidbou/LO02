package simplifiedProjet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine implements Preparation {
    public static void main(String[] args){
        

        List<Player> playerListInit = new ArrayList<Player>();

        GameStart:while(ifGameContinue(playerListInit)){
             System.out.println("================================ new turn ======================================= ");
            List<Player> playerList = SetUp.setUpPlayer();
           
            
            Player pTurn1 = new Player();
            Player pTurn2 = new Player();
            Player pNextTurn  = playerList.get(0);

            TurnStart:while(ifTurnContinue(playerList)){
               
                pTurn1 = pNextTurn;
                System.out.println("--------------------------------"+pTurn1.getName()+"'s round -----------------------");

                if( ((pTurn1.isVirtual() == 1) && doChoiceAh_Bot(pTurn1))  || ((pTurn1.isVirtual() == 0) && doChoiceAH_Real(pTurn1) )){
                    //accuse
                    pTurn2 = pTurn1.accuse(playerList);
                    if(((pTurn2.isVirtual() == 1) && doChoiceWI_Bot(pTurn2))  || ((pTurn2.isVirtual() == 0) && doChoiceWI_Real(pTurn2))){
                        pNextTurn = pTurn2.witch(pTurn1,playerList);
                        pTurn2.showCards();
                        System.out.println("pNextPlayer = "+pNextTurn.getName());
                        
                    }else{
                        pTurn2.showIdentity();
                        pTurn2.revealIdentity();
                        if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
                            pTurn1.raisePoints(1);
                            System.out.println(pTurn1.getName() + " gains 1 point");
                            pTurn2.setIsOutOfTurn(true); //pTurn2 should left the game
                            pNextTurn = nextPlayer(playerList, pTurn1);
                           
                        
                        }else{
                            pTurn1.raisePoints(0);//villager , gain no point;
                            System.out.println(pTurn1.getName() + " gains 0 point");
                            pNextTurn = pTurn2;// acussed player takes next turn
                        }
                        
                        //System.out.println("pNextPlayer = "+pNextTurn.getName());
                    }
                }else{
                    pTurn2 = pTurn1.hunt(playerList);
                    pTurn1.showCards();
                    pNextTurn = pTurn2;
                    System.out.println("pNextPlayer = "+pNextTurn.getName());
                }
                
                //showAllCards(playerList);

                if(!ifTurnContinue(playerList)){//this turn ends
                    showStatus(playerList);
                }
                
            }
        }
    }

    private static void showStatus(List<Player> playerList) {
        System.out.println("++++++++++++++++conclusion+++++++++++++++++++++");
        for(Player p: playerList){
            if(p.getIdentity() == 0){
                System.out.print("    + "+p.getName() + "is a villager" );
            }else{
                System.out.print("    + "+p.getName() + "is a witch   " );
            }
            System.out.println(" and got "+p.getPoint()+" points +  ");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
   



    public static boolean doChoiceWI_Real(Player pTurn2) {
        Scanner in = new Scanner(System.in);
        if(pTurn2.checkRumourCardList()){
           System.out.println("you don't have rumour cards, you have to show your identity");
           return false;

        }else{
            System.out.println(pTurn2.getName() + " SkillWitch or ShowIdentity? [sk/id]");
            String choiceAH_Real = in.nextLine();
            //in.close();
            if (choiceAH_Real.equals("sk")) {
                return true;
            } else {
                return false;
            }
        }
        
       
    }

    public static boolean doChoiceAH_Real(Player pTurn1) {
        Scanner in = new Scanner(System.in);
        if(pTurn1.checkRumourCardList()){
            System.out.println("you don't have rumour cards, you have to accuse someone");
            return true;
        }
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
        if (pTurn1.checkRumourCardList() || ChoiceAh_Bot > 1 ) {
            return true;// accuse
        } else {
            return false;
        }
    }

     /**
    * For a bot to choose randomly use witch or show id 
    * @param pTurn1
    * 		it's the bot who use this fonction
    * @return  a boolean
    *      the choice by random
    *      if true then means witch
    *      else means show id 
    */
    public static boolean doChoiceWI_Bot(Player pTurn1) { //witch or show id
        double ChoiceWI_Bot = (Math.random() + 0.5);
        
        if (ChoiceWI_Bot > 1 && !pTurn1.checkRumourCardList()) {
            return true;//use witch skill
        }else{
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
            return false; // if there is only one , the end this turn
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
        Player ppNextTurn = new Player();
        if(ifTurnContinue(playerList)){
            if(index == playerList.size()-1){
                index = 0;        
                ppNextTurn = playerList.get(index);
            }else{
                ppNextTurn = playerList.get(++index);
                while(ppNextTurn.ifIsOutOfTurn()){
                 ppNextTurn = playerList.get(++index);
                }
            }
            return ppNextTurn;
        }else{
            System.out.println("this turn ends");
            return null;// has a problem here
        }
        
        
		
    }

    /**
	 * change a string name to Player p
     * get the correct player in the list playerList with his player's name
	 * @param playerList
	 * 		the list of player of the game
     * @param name
     *      player's name 
	 * @return
     * the player of type Player selected
	 */
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

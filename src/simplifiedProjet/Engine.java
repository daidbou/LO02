package simplifiedProjet;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import controleur.ControleurSetup1;
import simplifiedProjet.SetUp.MyThreadRound;
import simplifiedProjet.RumourCard.RumourCard;
import vue.End;
import vue.InterfaceRound1;
import vue.InterfaceRound2;
import vue.InterfaceSetup1;

public class Engine implements Preparation {
	
	private static String choice;  
    private static Engine engine = new Engine();
    private Engine(){

    }

    public static Engine getEngine(){
        return engine;
    }
    private static int numReal;
    private static final CountDownLatch latchAH = new CountDownLatch(1);
	private List<Player> playerList;
	
    public void play(int numAllPlayer,int numBot){
    	numReal = numAllPlayer-numBot;
        List<Player> playerListInit = SetUp.initializeGame(numAllPlayer,numBot);
        
        GameStart:while(ifGameContinue(playerListInit)){
            
            System.out.println("================================ new turn ======================================= ");
            
            playerList = SetUp.setUpPlayerCards(playerListInit);
            
            for(Player p: playerList){
               p.showCards();
            }
           
            showDisCardCard();
           
            
            MyThreadRound myThreadRound = new MyThreadRound(playerList);
            myThreadRound.start();
            waitwait(2);
            
            Player pTurn1 = new Player();
            Player pTurn2 = new Player();
            Player pNextTurn  = playerList.get(0);
      

            TurnStart:while(ifTurnContinue(playerList)){
            	
                               
                pTurn1 = pNextTurn;              
                pTurn1.setOnTurn1(true);
                System.out.println("--------------------------------"+pTurn1.getName()+"'s round -----------------------");
                     
                waitChoice(pTurn1);
                
                if( ((pTurn1.isVirtual() == 1) && doChoiceAh_Bot(pTurn1))  || ((pTurn1.isVirtual() == 0) && doChoiceAH_Real(pTurn1) )){
                    //accuse
                    pTurn2 = pTurn1.accuse(playerList);    
                    if(pTurn2.equals(pTurn1)){
                        continue;
                    }
                    waitChoice(pTurn2);

                    if(((pTurn2.isVirtual() == 1) && doChoiceWI_Bot(pTurn2))  || ((pTurn2.isVirtual() == 0) && doChoiceWI_Real(pTurn2))){
                    
                        pNextTurn = pTurn2.witch(pTurn1,playerList); 
                        
                        if(pNextTurn.equals(pTurn2)){
                            pNextTurn = pTurn1;
                            continue TurnStart;
                        }
                        
                    }else{
                        pTurn2.showIdentity();
                        pTurn2.revealIdentity();
                        if(pTurn2.getIdentity() == 1){// witch ,pTurn1 gains 1 points
                            pTurn1.raisePoints(1);
                            System.out.println(pTurn1.getName() + " gains 1 point");
                            pTurn2.setIsOutOfTurn(true); //pTurn2 should left the game
                            pNextTurn = nextPlayer(playerList, pTurn1);
                            //used to be here
                        }else{
                            pTurn1.raisePoints(0);//villager , gain no point;
                            System.out.println(pTurn1.getName() + " gains 0 point");
                            pNextTurn = pTurn2;// accused player takes next turn
                        }
                        if(pNextTurn == null){
                            showStatusOfTurn(playerList);
                            break TurnStart;
                        }
                    }
                }else{
                    pTurn2 = pTurn1.hunt(playerList);
                    pTurn2.setOnTurn2(true);
                    pNextTurn = pTurn2;
                }
                pTurn1.setOnTurn1(false);
                pTurn2.setOnTurn2(false);
                pTurn1.setLock(true);
                pTurn2.setLock(true);
                   
                //System.out.println("there are still "+i+" players that didn't reveal their identity ");
                if(!ifTurnContinue(playerList)){//this turn ends
                    showStatusOfTurn(playerList);
                    playerListInit = playerList;
                }
            }

            if(!ifGameContinue(playerListInit)){
                showStatusOfGame(playerListInit);
            }
        }
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * shows the discard card of the round
     */
    private static void showDisCardCard() {
        System.out.println("=== discard card ===");
        for(RumourCard r: SetUp.discardedRumourCard){
            System.out.println(r.name());
        }
        System.out.println("====================");
    }


    /**
     * for each player in the playerList, shows it's status of the game,
     * including its points and the winner of the game
     * @param playerListInit
     */
    private static void showStatusOfGame(List<Player> playerListInit) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Conclusion of Game!!!!!!!!!!!!!!!!!!!!!!!!!");
        for(Player p: playerListInit){
            System.out.println(p.getName()+" got "+p.getPoint()+" points +  ");
        }
        
        int max = playerListInit.get(0).getPoint();
        String str = playerListInit.get(0).getName();
        for(Player p: playerListInit){
            if (p.getPoint()>max){
                max = p.getPoint();
                str = p.getName();
            }
        }  
        System.out.println(str+"win!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }


    /**
     * at the end of a turn,shows the status of a player, including
     * his name, his identity and his points
     * at the same time get the winner of this turn
     * we'll also create a window of the ending to show the status
     * @param playerList
     */
    private static void showStatusOfTurn(List<Player> playerList) {
        System.out.println("++++++++++++++++Conclusion of Turn+++++++++++++++++++++");

        for(Player p: playerList){
        	
            if(!p.ifIdentityReavealed() && !p.ifIsOutOfTurn()){
                p.setWinnerLastTurn(true);
                if(p.getIdentity() == 0){
                    p.raisePoints(1);
                }else{
                    p.raisePoints(2);
                }
                System.out.println(p.getName()+"   !!! is the winner of this turn !!!");
            }
        }
        for(Player p: playerList){
        	
            if(p.getIdentity() == 0){
                System.out.print("    + "+p.getName() + "is a villager" );
            }else{
                System.out.print("    + "+p.getName() + "is a witch   " );
            }
            System.out.println(" and got "+p.getPoint()+" points +  ");
        }
        
        //TODO end vue
        //End end = new End(playerList);
        //end.createEnd(playerList);
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
   


    /**
    * For a real player to choose to use witchskill or show identity
    * the choice is get from the controleurRound1 and the interfaceRound1
    * @param pTurn1
    * 		it's the real player who use this fonction
    * @return  
    *     true = witch skill , false = show identity
    */
    public static boolean doChoiceWI_Real(Player pTurn2) {
        
        if(pTurn2.checkRumourCardList()){
           System.out.println("you don't have rumour cards, you have to show your identity");
           return false;
        }
        System.out.println(pTurn2.getName() + " SkillWitch or ShowIdentity? [sk/id]");
        String choiceWS_Real = "ShowId";// par defaut
        choiceWS_Real = pTurn2.getIr1().getStrChoice();
        System.out.println("my choice is "+choiceWS_Real);
        if (choiceWS_Real.equals("ShowId")) {
      	        return false;
        }else{
               	return true; 
        }  
    }

    /**
    * For a real player to choose to accuse or hunt
    * @param pTurn1
    * 		it's the real player who use this function
    * @return  
    *     true = accuse , false = hunt
    */
    public static boolean doChoiceAH_Real(Player pTurn1) {
    	if(pTurn1.checkRumourCardList()){
           System.out.println("you don't have rumour cards, you have to accuse someone");
           return true;
        }
   		
    	String choiceAH_Real = "Accuse";// par defaut
    	choiceAH_Real = pTurn1.getIr1().getStrChoice();
    	System.out.println("my choice is "+choiceAH_Real);

    	if (choiceAH_Real.equals("Hunt")) {
    	        return false;
        }else{
            	return true;
        }
 
    }

    /**
    * For a bot to choose randomly to accuse or hunt
    * @param pTurn1
    * 		it's the bot who use this fonction
    * @return  
    *     true = accuse , false = hunt
    */
    public static boolean doChoiceAh_Bot(Player pTurn1) {
        double ChoiceAh_Bot = Math.random();
        if (pTurn1.checkRumourCardList() || ChoiceAh_Bot > 0.5 ) {
        	pTurn1.setLock(false);
            return true;// accuse
        } else {
        	pTurn1.setLock(false);
            return false;
        }
        
    }

     /**
    * For a bot to choose randomly to use witch or show id 
    * @param pTurn1
    * 		it's the bot who use this fonction
    * @return 
    *      true = witch , false = show id
    *      
    */
    public static boolean doChoiceWI_Bot(Player pTurn1) { //witch or show id
        double ChoiceWI_Bot = Math.random();
        
        if (ChoiceWI_Bot > 0.5 && !pTurn1.checkRumourCardList()) {
        	pTurn1.setLock(false);
            return true;//use witch skill
        }else{
        	pTurn1.setLock(false);
            return false;
        }
    }


    /**
     * to judge if this game is end or not
     * condition: until some players points more than 5
     * @param playerList
     * @return true if game ends
     */
    public static boolean ifGameContinue(List<Player> pAll) {
        for (Player p : pAll) {
            if (p.getPoint() >= 5) {
                return false;
            }
        }
        return true;
    }

    /**
     * to judge if this turn is end
     * condition: until one player didn't reveal his identity
     * @param playerList
     * @return true if turn ends
     */
    public static boolean ifTurnContinue(List<Player> playerList) {
        int i = playerList.size();// how many players are still playing

        for (Player p : playerList) {
            if (p.ifIdentityReavealed() ) {
                i--; // count how many players didn't revealed their identity
            }
        }
        //System.out.println("there are still "+i+" players that didn't reveal their identity ");
        if (i <= 1) {
            return false; // if there is only one , the end this turn
        } else {
            return true;
        }
    }

    /**
	 * returns next player in the list of p, which is well examined,
     * he should not out of turn, and will traverse again the list if reach the end of the list
     * 
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
        if(ifTurnContinue(playerList)){
            //check if there is a player after Player p that is not out of turn
            for(int i = index+1 ; i < playerList.size();i++){
                if(!playerList.get(i).ifIsOutOfTurn()){
                    return playerList.get(i);
                }
            }
            //start a new turn to find the next player
            for(int i = 0;i<index;i++){
                if(!playerList.get(i).ifIsOutOfTurn()){
                    return playerList.get(i);
                }
            }
            return null;
        }else{
            System.out.println("this turn ends111");
            return null;
        }
        
        
		
    }
    /**
     * 
     * @param playerList the list of player in the game
     * @param p the player that used Toad or Cauldron hunt! ability and need to pass the turn to the player at his left
     * @return the next player
     */
    public static Player leftPlayer(List<Player> playerList, Player p){
       
        int index = 0;
		for(int i = 0; i < playerList.size();i++){
			if(playerList.get(i).getName().equals(p.getName())){
				index = i;
				break;
			}
		}

        if(ifTurnContinue(playerList)){
            //check if there is a player before Player p that is not out of turn
            for(int i = index-1 ; i >= 0;i--){
                if(!playerList.get(i).ifIsOutOfTurn()){
                    return playerList.get(i);
                }
            }
            //start a new turn to find the next player
            for(int i = playerList.size()-1;i>index;i--){
                if(!playerList.get(i).ifIsOutOfTurn()){
                    return playerList.get(i);
                }
            }
            return null;
        }else{
            System.out.println("this turn ends222");
            return p;
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
    /**
     * return true or false randomly.
     * it's a random way to control what bot's going to do
     * @return
     */
    public static boolean choiceRandom(){
        double i = Math.random();
        if(i > 0.5){
            return true;
        }else{
            return false;
        }
    }
    /**
     * it's the lock of the player.
     * in the program, only when the player has done all the operations, like to choose to hunt, hunt witch
     * player, what cards to use,or other restrictions... can the program keep on executing.
     * so it needs a lock to make sure the input parameter is not null.
     * @param pTurn1 wait for the choice of pTurn1
     */
    public void waitChoice(Player pTurn1) {
    	if(pTurn1.isVirtual() == 0) {
    		while(pTurn1.isLock()) {
    			//we should wait pTurn1 had done all the operation
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    }
    /**
     * for the thread to wait some seconds in order that all the preparation is done
     * 
     * @param i wait i seconds
     */
    public static void waitwait(int i) {

        try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     }
    
    

   
    
    public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}


	public static CountDownLatch getLatchAH() {
		return latchAH;
	}


}


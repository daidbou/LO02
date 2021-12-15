package simplifiedProjet;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controleur.ControleurSetup1;
import simplifiedProjet.RumourCard.RumourCard;
import vue.InterfaceSetup1;

public class Engine implements Preparation {
    
    private static Engine engine = new Engine();
    private Engine(){

    }

    public static Engine getEngine(){
        return engine;
    }
    
    public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	private List<Player> playerList;
    public void play(int numAllPlayer,int numBot,controleur.ControleurSetup1.MyThread[] myThreadList){
        

        List<Player> playerListInit = SetUp.initializeGame(numAllPlayer,numBot);
        
        GameStart:while(ifGameContinue(playerListInit)){
            
            System.out.println("================================ new turn ======================================= ");
            
            playerList = SetUp.setUpPlayerCards(playerListInit);
           
            
            
            for(Player p: playerList){
                p.showCards();
            }
           
            showDisCardCard();
            Player pTurn1 = new Player();
            Player pTurn2 = new Player();
            Player pNextTurn  = playerList.get(0);
            for(int i = 0; i<3;i++) {
            	playerList.get(i).getIdentity();
            }

            TurnStart:while(ifTurnContinue(playerList)){
                               
                pTurn1 = pNextTurn;
                System.out.println("--------------------------------"+pTurn1.getName()+"'s round -----------------------");

                if( ((pTurn1.isVirtual() == 1) && doChoiceAh_Bot(pTurn1))  || ((pTurn1.isVirtual() == 0) && doChoiceAH_Real(pTurn1) )){
                    //accuse
                    pTurn2 = pTurn1.accuse(playerList);
                    if(pTurn2.equals(pTurn1)){
                        continue;
                    }
                    //while(isReturn){}
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
                            pNextTurn = pTurn2;// acussed player takes next turn
                        }
                        if(pNextTurn == null){
                            showStatusOfTurn(playerList);
                            break TurnStart;
                        }
                    }
                }else{
                    pTurn2 = pTurn1.hunt(playerList);
                    //pTurn1.showCards();
                    pNextTurn = pTurn2;

                }

                
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

    private static void showDisCardCard() {
        System.out.println("=== discard card ===");
        for(RumourCard r: SetUp.discardedRumourCard){
            System.out.println(r.name());
        }
        System.out.println("====================");
    }


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
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
   


    /**
    * For a real player to choose randomly to witchskill or show identity
    * @param pTurn1
    * 		it's the real player who use this fonction
    * @return  
    *     true = witch skill , false = show identity
    */
    public static boolean doChoiceWI_Real(Player pTurn2) {
        Scanner in = new Scanner(System.in);
        
        if(pTurn2.checkRumourCardList()){
           System.out.println("you don't have rumour cards, you have to show your identity");
           return false;

        }else{
            System.out.println(pTurn2.getName() + " SkillWitch or ShowIdentity? [sk/id]");
            String choiceAH_Real = in.nextLine();
            choice = choiceAH_Real;
            if (choiceAH_Real.equals("sk")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
    * For a real player to choose randomly to accuse or hunt
    * @param pTurn1
    * 		it's the real player who use this fonction
    * @return  
    *     true = accuse , false = hunt
    */
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
    * For a bot to choose randomly to accuse or hunt
    * @param pTurn1
    * 		it's the bot who use this fonction
    * @return  
    *     true = accuse , false = hunt
    */
    public static boolean doChoiceAh_Bot(Player pTurn1) {
        double ChoiceAh_Bot = Math.random();
        if (pTurn1.checkRumourCardList() || ChoiceAh_Bot > 0.5 ) {
            return true;// accuse
        } else {
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
            return true;//use witch skill
        }else{
            return false;
        }
    }


    /**
     * to judge if this gameis end
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
        //System.out.println("all "+i+" players");
        //System.out.println("size"+playerList.size());
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
       // System.out.println("index = "+index);

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
       // System.out.println("index = "+index);

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
     * return true or false randomly
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

    private static String choice;
   

}

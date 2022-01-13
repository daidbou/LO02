package simplifiedProjet;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import simplifiedProjet.RumourCard.RumourCard;
import simplifiedProjet.RumourCard.AngryMob;
import simplifiedProjet.RumourCard.BlackCat;
import simplifiedProjet.RumourCard.Broomstick;
import simplifiedProjet.RumourCard.Cauldron;
import simplifiedProjet.RumourCard.DuckingStool;
import simplifiedProjet.RumourCard.EvilEye;
import simplifiedProjet.RumourCard.HookedNose;
import simplifiedProjet.RumourCard.PetNewt;
import simplifiedProjet.RumourCard.PointedHat;
import simplifiedProjet.RumourCard.TheInquisition;
import simplifiedProjet.RumourCard.Toad;
import simplifiedProjet.RumourCard.Wart;

public interface Preparation {

    static AngryMob angryMob = new AngryMob();
    static BlackCat blackCat = new BlackCat();
    static Broomstick broomstick = new Broomstick();
    static Cauldron cauldron = new Cauldron();
    static DuckingStool duckingStool = new DuckingStool();
    static EvilEye evilEye = new EvilEye();
    static HookedNose hookedNose = new HookedNose();
    static PetNewt petNewt = new PetNewt();
    static PointedHat pointedHat = new PointedHat();
    static TheInquisition theInquisition = new TheInquisition();
    static Toad toad = new Toad();
    static Wart wart = new Wart();//create 12 rumour cards


    public static CopyOnWriteArrayList<RumourCard> rumourCardList = new CopyOnWriteArrayList<RumourCard>(){{
		//add(null);
        add(angryMob);
		add(blackCat);
		add(broomstick);
		add(cauldron);
        add(duckingStool);
        add(evilEye);
        add(hookedNose);
        add(petNewt);
        add(pointedHat);
        add(theInquisition);
        add(toad);
        add(wart);

	}};//add 12 rumour cards to a list
			


    /**
	 * verify if the player exists in the list
	 * @param pName target player
	 * @param pTurn1 player who use this method, in
     *               sure that pTurn1 cannot do something to himself	
     * @param playerList the list you want to choose
	 * @return the target player if exist, if not return null
	 */
    public static Player isExistedP(String pName, String pTurn1Name,List<Player> playerList) {
        // pName is the target player
        // pTurn1 is the player who use this method, to make sure that pTurn1 cannot do something to himself
        for(Player p:playerList) {
            if(pName.equals(p.getName())&& p.ifIsOutOfTurn() == false) {
                if(!pName.equals(pTurn1Name)){
                    return p;
                }else{
                    System.out.println("not yourself");
                    return null;
                }
            }	
        }
        System.out.println("no such player, try again");
        return null;
    }
    
    /**
	 * verify if the player exists in the list
	 * @param pName target player
	 * 	
     * @param playerList the list you want to choose
	 * @return the target player if exist, if not return null
	 */
    public static Player isExistedP(String pName,List<Player> playerList) {
    // sometimes we dont need to verify
        for(Player p:playerList) {
            if(pName.equals(p.getName()) && p.ifIsOutOfTurn() == false) {
                return p;
            }else if(p.ifIsOutOfTurn() == true){
                System.out.println("he is already out of game try again");
            }
        }
        System.out.println("no such player");
        return null;
    }
    /**
	 * verify if the player exists in the list
	 * @param accused target player
	 * @param pNextTurn player who use this method, in
     *               sure that pTurn1 cannot do something to himself	
     * @param playerList the list you want to choose
	 * @return true if existed
	 */
    public static boolean isExistedForPlayer(String accused,String pNextTurn,List<Player> playerList){
        for(Player p:playerList) {
            if(pNextTurn.equals(p.getName())) {
                if(!accused.equals(pNextTurn)&& p.ifIsOutOfTurn() == false){
                    return true;
                }else if(p.ifIsOutOfTurn()){
                    System.out.println("already out of turn");
                    return false;
                }else{
                    System.out.println("not yourself");
                    return false;
                }
            }	
        }
        System.out.println("no such player, try again");
        return false;
    }

    /**
     * verify if the bot exists in the list
     * @param accused target bot
     * @param pNextTurn player who use this method, int sure that pNextTurn cannot do something to himself
     * @param playerList
     * @return true if the bot accused exist
     */
    public static boolean isExistedForBot(String accused,String pNextTurn,List<Player> playerList){
        for(Player p:playerList) {
            if(pNextTurn.equals(p.getName())) {
                if(!accused.equals(pNextTurn) && p.ifIsOutOfTurn() == false){
                    return true;
                }else if(p.ifIsOutOfTurn()){
                    return false;
                }else{
                    return false;
                }
            }	
        }
        return false;
    }

    
    
   

}

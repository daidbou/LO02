package simplifiedProjet;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;

import simplifiedProjet.RumourCard.RumourCard;
import simplifiedProjet.RumourCard.RumourCard1;
import simplifiedProjet.RumourCard.RumourCard10;
import simplifiedProjet.RumourCard.RumourCard11;
import simplifiedProjet.RumourCard.RumourCard12;
import simplifiedProjet.RumourCard.RumourCard2;
import simplifiedProjet.RumourCard.RumourCard3;
import simplifiedProjet.RumourCard.RumourCard4;
import simplifiedProjet.RumourCard.RumourCard5;
import simplifiedProjet.RumourCard.RumourCard6;
import simplifiedProjet.RumourCard.RumourCard7;
import simplifiedProjet.RumourCard.RumourCard8;
import simplifiedProjet.RumourCard.RumourCard9;

public interface Preparation {

    static RumourCard1 rumourCard1 = new RumourCard1();
	static RumourCard2 rumourCard2 = new RumourCard2();
	static RumourCard3 rumourCard3 = new RumourCard3();		
    static RumourCard4 rumourCard4 = new RumourCard4();	
    static RumourCard5 rumourCard5 = new RumourCard5();
    static RumourCard6 rumourCard6 = new RumourCard6();
    static RumourCard7 rumourCard7 = new RumourCard7();
    static RumourCard8 rumourCard8 = new RumourCard8();
    static RumourCard9 rumourCard9 = new RumourCard9();
    static RumourCard10 rumourCard10 = new RumourCard10();
    static RumourCard11 rumourCard11 = new RumourCard11();
    static RumourCard12 rumourCard12 = new RumourCard12();//create 12 rumour cards


    public static List<RumourCard> rumourCardList = new ArrayList<RumourCard>(){{
		//add(null);
        add(rumourCard1);
		add(rumourCard2);
		add(rumourCard3);
		add(rumourCard4);
        add(rumourCard5);
        add(rumourCard6);
        add(rumourCard7);
        add(rumourCard8);
        add(rumourCard9);
        add(rumourCard10);
        add(rumourCard11);
        add(rumourCard12);

	}};//add 12 rumour cards to a list
			
	
	//static List<RumourCard> rumourCardListP1 = rumourCardList.subList(0, 2);//it's players own rumourcard list
	//static List<RumourCard> rumourCardListP2 = rumourCardList.subList(2, 4);// needs to be controlled, it's not random yet

    
    
		
    public static Player isExiste(String pName, Player pTurn1,List<Player> playerList) {
        // pName is the target player
        // pTurn1 is the player who use this method, to make sure that pTurn1 cannot do something to himself
        for(Player p:playerList) {
            if(pName.equals(p.getName())) {
                if(!pName.equals(pTurn1.getName())){
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
    
    public static Player isExiste(String pName,List<Player> playerList) {
    // sometimes we dont need to verify
        for(Player p:playerList) {
            if(pName.equals(p.getName()) && p.ifIsOutOfTurn() == false) {
                return p;
            }else if(p.ifIsOutOfTurn() == true){
                System.out.println("he is already out of gamem try again");
            }
        }
        System.out.println("no such player");
        return null;
    }
    
   

}

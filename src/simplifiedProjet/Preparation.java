package simplifiedProjet;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;

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


    public static List<RumourCard> rumourCardList = new ArrayList<RumourCard>(){{
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
			
	
	//static List<RumourCard> rumourCardListP1 = rumourCardList.subList(0, 2);//it's players own rumourcard list
	//static List<RumourCard> rumourCardListP2 = rumourCardList.subList(2, 4);// needs to be controlled, it's not random yet


    // /!\ 18/11/2021 don't forget to name the card with the correct name


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

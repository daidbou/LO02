package simplifiedProjet;
import java.util.ArrayList;
import java.util.List;

import simplifiedProjet.RumourCard.RumourCard;
import simplifiedProjet.RumourCard.RumourCard1;
import simplifiedProjet.RumourCard.RumourCard2;
import simplifiedProjet.RumourCard.RumourCard3;
import simplifiedProjet.RumourCard.RumourCard4;

public class Preparation {

    static RumourCard1 rumourCard1 = new RumourCard1();
	static RumourCard2 rumourCard2 = new RumourCard2();
	static RumourCard3 rumourCard3 = new RumourCard3();		
    static RumourCard4 rumourCard4 = new RumourCard4();	//create 12 rumour cards

    public static List<RumourCard> rumourCardList = new ArrayList<RumourCard>(){{
		//add(null);
        add(rumourCard1);
		add(rumourCard2);
		add(rumourCard3);
		add(rumourCard4);
	}};//add 12 rumour cards to a list
			
	
	//static List<RumourCard> rumourCardListP1 = rumourCardList.subList(0, 2);//it's players own rumourcard list
	//static List<RumourCard> rumourCardListP2 = rumourCardList.subList(2, 4);// needs to be controlled, it's not random yet

    public static List<RumourCard> rumourCardListP1 = new ArrayList<RumourCard>(){{
        //add(null);
        add(rumourCard1);
        add(rumourCard2);
    }};
    public static List<RumourCard> rumourCardListP2 = new ArrayList<RumourCard>(){{
        //add(null);
        add(rumourCard3);
        add(rumourCard4);
    }};

	public static Player p1 = new Player("p1",1,rumourCardListP1);
	public static Player p2 = new Player("p2",0,rumourCardListP2);

	public static List<Player> playerList = new ArrayList<Player>(){{//player list
		//add(null);
        add(p1);
		add(p2);
	}};
		
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
        System.out.println("no such player");
        return null;
            
            
        }

}

package simplifiedProjet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;



public class SetUp implements Preparation{
    // public static List<RumourCard> rumourCardListP1 = new ArrayList<RumourCard>(){{
    //     //add(null);
    //     add(rumourCard1);
    //     add(rumourCard2);
    //     add(rumourCard3);
    // }};
    // public static List<RumourCard> rumourCardListP2 = new ArrayList<RumourCard>(){{
    //     //add(null);
    //     add(rumourCard4);
    //     add(rumourCard5);
    //     add(rumourCard7);
    // }};
    // public static List<RumourCard> rumourCardListP3 = new ArrayList<RumourCard>(){{
    //     //add(null);
    //     add(rumourCard6);
    //     add(rumourCard8);
    //     add(rumourCard9);
    // }};
    // public static List<RumourCard> rumourCardListP4 = new ArrayList<RumourCard>(){{
    //     //add(null);
    //     add(rumourCard10);
    //     add(rumourCard11);
    //     add(rumourCard12);
    // }};

    public static Player p1 = new Player("p1");
    public static Player p2 = new Player("p2");
    public static Player p3 = new Player("p3");
    public static Player p4 = new Player("p4");
    public static Player p5 = new Player("p5");
    public static Player p6 = new Player("p6");


	public static List<Player> playerListAll = new ArrayList<Player>(){{//player list
        add(p1);
		add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
	}};
    public static List<Player> playerList = new ArrayList<Player>();

    public static List<RumourCard> rumourCardDupl = rumourCardList; 
    
    public static List<Player> setUpPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players? (3-6)");
        int numPlayer = sc.nextInt();
        int numCardsPerPlayer = (int)12/numPlayer;
        System.out.println("each player has "+numCardsPerPlayer+"cards");
        Collections.shuffle(rumourCardDupl);

        //Iterator<Player> it = playerListAll.iterator(); 
        for(int i = 0; i<numPlayer ;i++){
            List<RumourCard> rumourCardListPlayer = rumourCardDupl.subList(0, numCardsPerPlayer);
            playerListAll.get(i).setRumourCardListPlayer(rumourCardListPlayer);//the same time define theirs identity
            //playerListAll.get(i).showCards();
            for (int j = 0; j < numCardsPerPlayer; j++){
                rumourCardDupl.remove(0);
            }
        }
        //above succeed
        sc.close();
        for (int i = 0; i<numPlayer ;i++){
            playerList.add(playerListAll.get(i));
            System.out.println(playerList.get(i).getName());
            playerList.get(i).showCards();
        }
        return playerList;
            
        
    }
    
}

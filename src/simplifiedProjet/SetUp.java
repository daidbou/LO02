package simplifiedProjet;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
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
    public static Bot b1 = new Bot("b1");
    public static Bot b2 = new Bot("b2");
    public static Bot b3 = new Bot("b3");
    public static Bot b4 = new Bot("b4");
    public static Bot b5 = new Bot("b5");
    public static Bot b6 = new Bot("b6");
   


	public static List<Player> realPlayerListAll = new ArrayList<Player>(){{//player list
        add(p1);
		add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
	}};

    public static List<Bot> botListAll = new ArrayList<Bot>(){{//Bot List
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
    }};
    
    public static List<Player> playerList = new ArrayList<Player>();
    public static List<RumourCard> rumourCardDupl = rumourCardList; 
    
    public static List<Player> setUpPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players(including the bots)? (3-6)");
        int numPlayer = sc.nextInt();
        int numCardsPerPlayer = (int)12/numPlayer;
        System.out.println("each player has "+numCardsPerPlayer+"cards");
        //Collections.shuffle(rumourCardDupl);
        System.out.println("how many bots?");
        int numbot = sc.nextInt();
        while(numbot>numPlayer || numbot<0){
            System.out.println("pls enter again");
            numbot = sc.nextInt();
        }

        int numReal = numPlayer-numbot;
        System.out.println("this game includes "+numbot+" bots and "+numReal+" players");

        int i = 0;
        for(i = 0 ; i<numReal ;i++){ // set up real players
            List<RumourCard> rumourCardListReal = rumourCardDupl.subList((i)*numCardsPerPlayer, (i+1)*numCardsPerPlayer);

            realPlayerListAll.get(i).setRumourCardListPlayer(rumourCardListReal);//the same time define theirs identity
            
            playerList.add(realPlayerListAll.get(i));
            
            playerList.get(i).showCards();
        }
        
        
        for(int j = 0 ; j<numbot ;j++,i++){ // set up bots
            List<RumourCard> rumourCardListBot = rumourCardDupl.subList((i)*numCardsPerPlayer, (i+1)*numCardsPerPlayer);

            botListAll.get(j).setRumourCardListPlayer(rumourCardListBot);//the same time define theirs identity
            
            playerList.add(botListAll.get(j));
            
            playerList.get(i).showCards();
        }
        //Collections.shuffle(playerList);
        return playerList;      
    }
    
}

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

    /**
     * List of every vard that we discarded
     */
    public static List<RumourCard> discardedRumourCard = new ArrayList<RumourCard>();

	public static List<Player> irlPlayerList = new ArrayList<Player>(){{//player list
        add(p1);
		add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
	}};

    public static List<Bot> botPlayerList = new ArrayList<Bot>(){{//Bot List
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
    }};
    
    public static  List<Player> allPlayerList = new ArrayList<>();
    public static  List<RumourCard> rumourCardShuffled = rumourCardList; 
    
    public static List<Player> initializeGame(){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players(including the bots)? (3-6)");
        int numberOfPlayer = sc.nextInt();
        int numberOfCardsPerPlayer = (int)12/numberOfPlayer;
        System.out.println("each player has "+numberOfCardsPerPlayer+"cards");
        
        Collections.shuffle(rumourCardDupl);
        
        if(numberOfPlayer == 5){
            discardedRumourCard.add(rumourCardShuffled.get(10));
            discardedRumourCard.add(rumourCardShuffled.get(11));
        }
        System.out.println("how many bots?");
        int numberOfBot = sc.nextInt();
        while(numberOfBot>numberOfPlayer || numberOfBot<0){
            System.out.println("please enter again");
            numberOfBot = sc.nextInt();
        }

        int numReal = numberOfPlayer-numberOfBot;
        System.out.println("this game includes "+numberOfBot+" bots and "+numReal+" players");
        
        int i = 0;
        for(i = 0 ; i<numReal ;i++){ //  add real players
            allPlayerList.add(irlPlayerList.get(i));
        }
        for(int j = 0 ; j<numberOfBot ;j++,i++){ // add bots
            allPlayerList.add(botPlayerList.get(j));
        }
        
        return allPlayerList;
    }


    public static List<Player> setUpPlayer(List<Player> playerListInit){
        List<Player> playerList = new ArrayList<Player>();
       
        int numberOfPlayer = playerListInit.size();
        int numberOfBot = 0;
        int numberOfCardsPerPlayer = (int)12/numberOfPlayer;
        for(Player p:playerListInit){
            if(p.isVirtual() == 1){
                numberOfBot++;
            }
        } 
        
        int numIrlPlayer = numberOfPlayer - numberOfBot;
        for(Player p: playerListInit){
            p.initializePlayer();
        }
        int i = 0;
        for(i = 0 ; i<numIrlPlayer ;i++){ // set up real players
            List<RumourCard> rumourCardListReal = rumourCardShuffled.subList((i)*numberOfCardsPerPlayer, (i+1)*numberOfCardsPerPlayer);
            irlPlayerList.get(i).setRumourCardListPlayer(rumourCardListReal);//the same time define theirs identity
            playerList.add(irlPlayerList.get(i));
        }
        
        
        
        for(int j = 0 ; j<numberOfBot ;j++,i++){ // set up bots
            List<RumourCard> rumourCardListBot = rumourCardShuffled.subList((i)*numberOfCardsPerPlayer, (i+1)*numberOfCardsPerPlayer);
            botPlayerList.get(j).setRumourCardListPlayer(rumourCardListBot);//the same time define theirs identity
            playerList.add(botPlayerList.get(j));
        }
        //Collections.shuffle(playerList);
        return playerList;      
    }
}

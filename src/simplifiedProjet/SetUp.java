package simplifiedProjet;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import controleur.ControleurSetup2;

import controleur.ControleurSetup1;
import simplifiedProjet.RumourCard.RumourCard;



public class SetUp implements Preparation{

	private static int numReal;

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
     * List of every card that we discarded
     */
    public static CopyOnWriteArrayList<RumourCard> discardedRumourCard = new CopyOnWriteArrayList<RumourCard>();

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
    public static  CopyOnWriteArrayList<RumourCard> rumourCardShuffled = rumourCardList; 
    
    public static List<Player> initializeGame(int numberOfPlayer,int numberOfBot){
        Scanner sc = new Scanner(System.in);
        /*
        System.out.println("How many players(including the bots)? (3-6)");
        //int numberOfPlayer = sc.nextInt();
        int numberOfPlayer = cp.getNumAllPlayer();
        int numberOfCardsPerPlayer = (int)12/numberOfPlayer;
        System.out.println("each player has "+numberOfCardsPerPlayer+"cards");
        System.out.println("how many bots?");
        //int numberOfBot = sc.nextInt();
        int numberOfBot = cp.getNumBot();
        while(numberOfBot>numberOfPlayer || numberOfBot<0){
            System.out.println("please enter again");
            numberOfBot = sc.nextInt();
        }*/

        setNumReal(numberOfPlayer-numberOfBot);
       
        System.out.println("this game includes "+numberOfBot+" bots and "+getNumReal()+" players");
        
        int i = 0;
        for(i = 0 ; i<getNumReal() ;i++){ //  add real players
            allPlayerList.add(irlPlayerList.get(i));
        }
        for(int j = 0 ; j<numberOfBot ;j++){ // add bots
            allPlayerList.add(botPlayerList.get(j));
        }
     
        
        return allPlayerList;
    }


    public static List<Player> setUpPlayerCards(List<Player> playerListInit){
        Player pWinner = playerListInit.get(0);
      
        for(Player p: playerListInit){
            p.initializePlayer();
        }
        List<Player> playerList = new ArrayList<Player>();

        if(discardedRumourCard.size()!=0){ //initialze the discard card list
            discardedRumourCard.clear();
        }
        Collections.shuffle(rumourCardShuffled);
       
        int numberOfPlayer = playerListInit.size();
        int numberOfBot = 0;
        int numberOfCardsPerPlayer = (int)12/numberOfPlayer;

        for(Player p:playerListInit){
            if(p.isVirtual() == 1){
                numberOfBot++;
            }
        } 
        
        int numIrlPlayer = numberOfPlayer - numberOfBot;

        int i = 0;
        int j = 0;
        
        for(i = 0 ; i<numIrlPlayer ;i++){ // set up the cards of real players
            
            CopyOnWriteArrayList<RumourCard> rumourCardListReal = new  CopyOnWriteArrayList<RumourCard>();
            for (j = (i)*numberOfCardsPerPlayer;j<(i+1)*numberOfCardsPerPlayer;j++){
                rumourCardListReal.add(rumourCardShuffled.get(j));
            }
            irlPlayerList.get(i).setRumourCardListPlayer(rumourCardListReal); 
           
            //System.out.println(playerList.get(i).getIdentity());
            playerList.add(irlPlayerList.get(i));
        }
        
        
        for(int k = 0 ; k<numberOfBot ;k++,i++){
            CopyOnWriteArrayList<RumourCard> rumourCardListBot = new  CopyOnWriteArrayList<RumourCard>();
            for (int m = (i)*numberOfCardsPerPlayer;m<(i+1)*numberOfCardsPerPlayer;m++){
                rumourCardListBot.add(rumourCardShuffled.get(m));
            }
            botPlayerList.get(k).setRumourCardListPlayer(rumourCardListBot);//the same time define their identity
            playerList.add(botPlayerList.get(k));


        }
        if(numberOfPlayer == 5){
            discardedRumourCard.add(rumourCardShuffled.get(10));
            discardedRumourCard.add(rumourCardShuffled.get(11));
        }
       
        //Collections.shuffle(playerList);
        
        //the winner of last turn goes first
        for(int t = 0; t<playerList.size();t++){
            if(playerList.get(t).getName().equals(pWinner.getName())){
                System.out.println("winner of last turn is "+playerList.get(t).getName());
                Collections.swap(playerList,0,t);
            }
        }
        for(Player p: playerList){
            System.out.println(p.getName());
        }
        playerListInit = playerList;
        return playerList;      
    }
    public static List<Player> setUpPlayerIdentity(List<Player> playerList){
    	for(int i = 0; i<SetUp.getNumReal();i++) {
    		
    		if(ControleurSetup1.myThreadList[i].getIdentity() == 1) {
     			playerList.get(i).setIdentity(1);
     		}else {
     			playerList.get(i).setIdentity(0);
     		}
    		System.out.println(ControleurSetup1.myThreadList[i].getpName()+" "+ControleurSetup1.myThreadList[i].getIdentity()+"131313");
    		//System.out.println(playerList.get(i).getIdentity());
    		
    	}
    	return playerList;
    }
   


	public static int getNumReal() {
		return numReal;
	}


	public static void setNumReal(int numReal) {
		SetUp.numReal = numReal;
	}
}

package simplifiedProjet;
import simplifiedProjet.RumourCard.RumourCard;

import java.util.List;
//import java.util.Scanner;

public class Bot extends Player{

   // private String name;
	// private int identity;//1 = witch, 0 = villager
	// private boolean identityReavealed ;
	// private int point;
	// private List<RumourCard> rumourCardListPlayer;
	// public List<RumourCard> disCardCardListPlayer ;
	// private boolean isOutOfTurn;
    protected int virtual ; // 1 = virtual
	
	//Scanner sc = new Scanner(System.in);
    
    public Bot(String string) {
        name = string;
        this.virtual = 1;
       // super(name);
    }
    public Bot(String name, int identity, List<RumourCard> rumourCardListP) {
		
        this.name = name;
		this.identity = identity;
		this. playerRumourCardList= rumourCardListP;
		this.identityReavealed = false;
		this.isOutOfTurn = false;
        this.virtual = 1;
	}
    public void setRumourCardListPlayer(List<RumourCard> l){
		playerRumourCardList = l;
		identity = (int)(Math.random()+0.5);//random set identity
	}

    public Player accuse(List<Player> playerList){
		int noP = (int)(playerList.size()*Math.random()-1);// random num player 
		while(playerList.get(noP).getName().equals(name)){
			
			if(noP>playerList.size()){
				noP = 0;
			}else{
				noP++;
			}
		}

		Player pTurn2 = playerList.get(noP);
		while(pTurn2.ifIdentityReavealed()){
			if(noP == playerList.size()-1){
				noP = noP - playerList.size()-1;
				pTurn2 = playerList.get(noP);
			}else{
				pTurn2 = playerList.get(++noP);
			}
			
		}
			
		System.out.println(name+" accuses "+pTurn2.getName());
		return pTurn2;
		
	}

	/**
	 * when pTurn1 accuse you, then bot use skillWitch.
	 * the card bot use is randomly chosen bt Math.random()
	 * 
	 * @param pTurn1 is the player who accused you
	 * 		
	 * @return pNextTurn 
	 * 	the next player of pTurn1
	 */
	public Player witch(Player pTurn1,List<Player> playerList){
		int cardNumBot = (int)(Math.random()*(getRumourCardListPlayer().size()-1));	
		Player pNextTurn = playerRumourCardList.get(cardNumBot).skillWitchBot(pTurn1.getName(),playerList);
		System.out.println(name+"use witch skill to "+pTurn1.getName()+" using "+playerRumourCardList.get(cardNumBot).name());
		//rumourCardListPlayer.remove(cardNumBot);
		return pNextTurn;
	}
	/**
	 * bot use skill hunt
	 * 
	 * @param 
	 */
	public Player hunt(List<Player> playerList){
		int cardNumBot = (int)(Math.random()*(getRumourCardListPlayer().size()-1));
		
		Player pNextTurn = playerRumourCardList.get(cardNumBot).skillHuntBot(name,playerList);
		System.out.println(name+"use hunt skill to "+pNextTurn.getName()+" using "+playerRumourCardList.get(cardNumBot).name());
		//rumourCardListPlayer.remove(cardNumBot);
		return pNextTurn;

	}
    public int isVirtual(){
		return virtual;
	}
    
}

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
		this. rumourCardListPlayer= rumourCardListP;
		this.identityReavealed = false;
		this.isOutOfTurn = false;
        this.virtual = 1;
	}
    public void setRumourCardListPlayer(List<RumourCard> l){
		rumourCardListPlayer = l;
		identity = (int)Math.random();//random set identity
	}
    public Player accuse(List<Player> playerList){
		int noP = (int)(playerList.size()*Math.random());// random num player 

		Player pTurn2 = playerList.get(noP);
		while(pTurn2.ifIdentityReavealed()){
			if(noP == playerList.size()){
				noP = noP - playerList.size();
			}
			pTurn2 = playerList.get(++noP);
		}
			
		System.out.println(name+" accuses "+pTurn2.getName());
		return pTurn2;
		
	}

	public Player witch(List<Player> playerList){
		int cardNumBot = (int)(Math.random()*getRumourCardListPlayer().size());
		System.out.println(name+"use witch skill by "+rumourCardListPlayer.get(cardNumBot).name());
		Player pNextTurn = rumourCardListPlayer.get(cardNumBot).skillWitchBot(name,playerList);
		return pNextTurn;
	}

	public Player hunt(List<Player> playerList){
		int cardNumBot = (int)(Math.random()*getRumourCardListPlayer().size());
		System.out.println(name+"use hunt skill by  "+rumourCardListPlayer.get(cardNumBot).name());
		Player pNextTurn = rumourCardListPlayer.get(cardNumBot).skillHuntBot(name,playerList);
		return pNextTurn;

	}
    public int isVirtual(){
		return virtual;
	}
    
}

package simplifiedProjet;
import simplifiedProjet.RumourCard.RumourCard;

//import java.time.Year;
import java.util.List;
//import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bot extends Player{

    protected int virtual ; // 1 = virtual
	
    public Bot(String string) {
        name = string;
        this.virtual = 1;
    }
    public Bot(String name, int identity, CopyOnWriteArrayList<RumourCard> rumourCardListP) {
		
        this.name = name;
		this.identity = identity;
		this.setPlayerRumourCardList(rumourCardListP);
		this.identityReavealed = false;
		this.isOutOfTurn = false;
        this.virtual = 1;
	}
    public void setRumourCardListPlayer(CopyOnWriteArrayList<RumourCard> l){
		setPlayerRumourCardList(l);
		identity = (int)(Math.random()+0.5);//random set identity
		playerRevealedCardList = new CopyOnWriteArrayList<RumourCard>();
	}

	/**
	 * bot accuse another player
	 * @param playerList list of every player
	 */
    public Player accuse(List<Player> playerList){
		int noP;
		int index = 0;
		for(int i =0; i<playerList.size();i++){
			if(playerList.get(i).getName().equals(this.name)){
				index = i;	
			}
		}
		System.out.println("index = " + index);
		while(true){
			noP= (int) (Math.random()*(playerList.size()));// random num player 
			
			
			if(!Engine.nameToPlayer(playerList, playerList.get(noP).getName()).ifIsOutOfTurn()&&
				noP != index){
				System.out.println("noP = "+noP);
				break;
			}

		}
		System.out.println(name+" accuses "+playerList.get(noP).getName());
		return Engine.nameToPlayer(playerList, playerList.get(noP).getName());
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
		int cardNumBot = (int)(Math.random()*(getRumourCardListPlayer().size()));	
		System.out.println(name+" use witch skill to "+pTurn1.getName()+" using "+getPlayerRumourCardList().get(cardNumBot).name());
		return getPlayerRumourCardList().get(cardNumBot).skillWitchBot(pTurn1.getName(),this.name,playerList);
	}
	/**
	 * bot use skill hunt
	 * 
	 * @param 
	 */
	public Player hunt(List<Player> playerList){

		System.out.println(this.name+" choose hunt");
		int cardNumBot = (int)(Math.random()*(getRumourCardListPlayer().size()));

		System.out.println(this.name+" choose "+getRumourCardListPlayer().get(cardNumBot).name());
		
		return getPlayerRumourCardList().get(cardNumBot).skillHuntBot(name,playerList);

	}
    public int isVirtual(){
		return virtual;
	}
    
}

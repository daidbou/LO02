package simplifiedProjet;
//import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;

import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Scanner;

public class Player implements Preparation{
	protected String name;
	protected int identity;//1 = witch, 0 = villager
	protected boolean identityReavealed ;
	protected int point;
	protected List<RumourCard> rumourCardListPlayer;
	protected List<RumourCard> disCardCardListPlayer ;
	protected boolean isOutOfTurn;
	protected int virtual ; // 1 = virtual
	
	Scanner in = new Scanner(System.in);

	public Player(){};

	public Player(String name, int identity, List<RumourCard> rumourCardListP) {
		this.name = name;
		this.identity = identity;
		this.rumourCardListPlayer= rumourCardListP;
		this.identityReavealed = false;
		this.isOutOfTurn = false;
		this.virtual = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.virtual = 0;
	}

	/**
	 * for a real player to accuse someone, we cannot accuse someon's
	 * identity is already revealed, or someone is already out of turn
	 * 
	 * @param playerList
	 * @return
	 */
	public Player accuse(List<Player> playerList) { 
		System.out.println(name+" is a real");
		System.out.println("which player? ex: p1 b1");
		while(true){
		Scanner sc = new Scanner(System.in);
		String pName = sc.nextLine();
		Player pTurn2 = Preparation.isExiste(pName,name, playerList);
		if(pTurn2 == null){
			System.out.println("no such player, try again");
			continue;
		}else if(pTurn2.ifIdentityReavealed() == false){
			System.out.println(name+" accuse "+pTurn2.getName());
			return pTurn2;
		}else{
			System.out.println(pTurn2.getName() + " has already revealed his identity, try again");
			continue;
		}
		}
		
	}
	
	public Player hunt(List<Player> playerList) {
		
		showCards();
		System.out.println(" entre 0 for the first card");
		int cardNum = in.nextInt();
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillHunt(name,playerList);
		//rumourCardListPlayer.remove(cardNum);
		return pNextTurn ;
	}
	/**
	 * 
	 * @param pTurn1 is the player who accused you
	 * 		
	 * @return next player of pTurn1
	 */
	public Player witch(Player pTurn1,List<Player> playerList) {
		
		showCards();
		System.out.println(" entre 0 for the first card");
		int cardNum = in.nextInt();
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillWitch(pTurn1.getName(),playerList);
		//rumourCardListPlayer.remove(cardNum);
		return pNextTurn ;//在这里检查?
	}

	/**
	 * Show player's identity
	 * @param 
	 * 		
	 * @return
	 */
	public void showIdentity(){
		System.out.println(name+" choose to reveal identity");
		if(identity == 0){
			System.out.println(name + "is a villager" );
		}else{
			System.out.println(name + "is a witch" );
		}
		
		
	}

	
	/**
	 * Show the card of the player
	 */
	public void showCards() {
		System.out.println(name+" and cards with ");
		for (int i = 0; i < rumourCardListPlayer.size(); i++) {
			System.out.println(rumourCardListPlayer.get(i).name());
		}
		System.out.println();
	}
	
	/**
	 * Get the name of the player
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Get the identity (witch or villager) of the player
	 * @return a int, witch 1 refers to 
	 */
	public int getIdentity(){
		return identity;
	}

	/**
	 * Raise the score of the player
	 * @param num shows how many to raise
	 */
	public void raisePoints(int num){
		for(int i = 0; i < num; i++){
			point++;
		}
	}
	
	/**
	 * Get the total of points of a the player
	 * @return point
	 */
	public int getPoint(){
		return point;
	}

	/** player reveals it's identity
	 *
	 */
	public void revealIdentity(){
		this.identityReavealed = true;
	}

	/**
	 * getter of the identityReavealed
	 * @return bool
	 */
	public boolean ifIdentityReavealed(){
		return identityReavealed;
	}
	
	public void initializePlayer(){
		identityReavealed = false;
		isOutOfTurn = false;
			
	}
	/**
	 * 
	 * @param t if it's out of turn(identity revealed as a witch), input true
	 */
	public void setIsOutOfTurn(boolean t){
		isOutOfTurn = t;
	}
	/**
 	 *  getter of isOutOfTurn
 	 * @return isOutOfTurn
 	 */
	public boolean ifIsOutOfTurn(){
		return isOutOfTurn;
	}

	/**
	 * Get the number of points of the player
	 * @return point of the player
	 */
	public int showPoint(){
		return point;
	}
	
	/**
	 * Set the identity of real players, and the same time set the rumour card list of the player
	 * @param l
	 */
	public void setRumourCardListPlayer(List<RumourCard> l){
		
		rumourCardListPlayer = l;
		System.out.println(name+", what identity do you want to be? (1 for witch, 0 for villager)");
		String id = in.nextLine();
		
		while(true){
			
			if(id.equals("0")){
				identity = 0;
				break;
			}
			else if(id.equals("1")){
				identity = 1;
				break;
			}
			else{
				System.out.println("error, please select 0 or 1");
				System.out.println(name+", what identity do you want to be? (1 for witch, 0 for villager)");
				id = in.nextLine();
			}
		}
}

	public List<RumourCard> getRumourCardListPlayer(){	
		return rumourCardListPlayer;
	}

	public int isVirtual(){
		return virtual;
	}

	/**
	 * returns true when player don't have  any rumourcard
	 * @param 
	 * 		
	 * @return 
	 */
	public boolean checkRumourCardList(){
		if (rumourCardList.size() == 0){
			return true;
		}else{
			return false;
		}
	}

}

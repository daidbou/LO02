package simplifiedProjet;
//import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements Preparation{
	protected String name;
	protected int identity;//1 = witch, 0 = villager
	protected boolean identityReavealed ;
	protected int point;
	protected CopyOnWriteArrayList<RumourCard> playerRumourCardList;
	protected CopyOnWriteArrayList<RumourCard> playerRevealedCardList;
	protected boolean isOutOfTurn = false;
	protected int virtual ; // 1 = virtual
	protected boolean isBroomstick = false;
	protected boolean isWart = false;
	protected boolean isWinnerLastTurn;
	Scanner in = new Scanner(System.in);

	public Player(){};

	public Player(String name, int identity, CopyOnWriteArrayList<RumourCard> rumourCardListP) {
		this.name = name;
		this.identity = identity;
		this.playerRumourCardList= rumourCardListP;
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
		//System.out.println(name+" is a real");
		System.out.println("which player? ex: p1 b1");
		while(true){
		Scanner sc = new Scanner(System.in);
		String pName = sc.nextLine();
		Player pTurn2 = Preparation.isExistedP(pName,name, playerList);
		if(pTurn2 == null){
			//System.out.println("no such player, try again");
			continue;
		}else if(pTurn2.ifIdentityReavealed() == false){
			System.out.println(name+" accuse "+pTurn2.getName());
			//sc.close();
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
		Player pNextTurn;
		
		while(true){

			if(!(playerRumourCardList.get(cardNum).name() == "The Inquisition" || playerRumourCardList.get(cardNum).name() == "Angry Mob")){
				pNextTurn = playerRumourCardList.get(cardNum).skillHunt(name,playerList);
				break;
			}
			else{
				if(this.identity==0 && this.identityReavealed){
					pNextTurn = playerRumourCardList.get(cardNum).skillHunt(name,playerList);
					break;
				}
				else{
					System.out.println(playerRumourCardList.get(cardNum).name()+" is only playable if you have been revealed as a Villager ");
					showCards();
					System.out.print("select a card other than "+playerRumourCardList.get(cardNum).name());
					cardNum = in.nextInt();
				}

			}
		}
		disCardCard(cardNum);
		return pNextTurn;	
	}
	/**
	 * 
	 * @param pTurn1 is the player who accused you
	 * 		
	 * @return next player of pTurn1
	 */
	public Player witch(Player pTurn1,List<Player> playerList) {
		
		showCards();
		System.out.println(" enter 0 for the first card");
		int cardNum = in.nextInt();
		String cardName = playerRumourCardList.get(cardNum).name();
		Player pNextTurn = playerRumourCardList.get(cardNum).skillWitch(pTurn1.getName(),this.name,playerList);
		disCardCard(cardName);
		
		//use a method in setup
		return pNextTurn ;//在这里检查?
		
	}

	/**
	 * Show player's identity
	 */
	public void showIdentity(){
		System.out.println(name+" choose to reveal identity");
		if(identity == 0){
			System.out.println(name + " is a villager" );
		}else{
			System.out.println(name + " is a witch" );
		}
		
		
	}

	/**
	 * Show the card of the player
	 */
	public void showCards() {
		System.out.println(name+" and cards with ");
		for (int i = 0; i < playerRumourCardList.size(); i++) {
			System.out.println(playerRumourCardList.get(i).name());
		}
	}
	/**
	 * Show the discarded card
	 */
	public void showDiscardedCards() {
		System.out.println("Discarded cards are :");
		for (int i = 0; i < SetUp.discardedRumourCard.size(); i++) {
			System.out.println(SetUp.discardedRumourCard.get(i).name());
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
		if(num > 0){
			for(int i = 0; i < num; i++){
				point++;
			}
		}else{
			for(int i = 0; i > num; i--){
				point -= num;
			}
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
		if(this.playerRevealedCardList!=null){
			this.playerRevealedCardList.clear();
		}

		//TODO all card status need to be initialized
			
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
	public void setRumourCardListPlayer(CopyOnWriteArrayList<RumourCard> l){
		
		playerRumourCardList = l;
		playerRevealedCardList = new CopyOnWriteArrayList<RumourCard>();
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

	public CopyOnWriteArrayList<RumourCard> getRumourCardListPlayer(){	
		return playerRumourCardList;
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
		if (playerRumourCardList.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * add a card in the hand of the player
	 */
	public void addCardInTheList(RumourCard rumourCard){

		this.playerRumourCardList.add(rumourCard);
	}


	public void isBroomstick(){

		this.isBroomstick = true;
 
	}

	public void isWart(){
		this.isWart = true;
	}
	
	/**
	 * for a player to discard cards
	 * both to the commom discardlist and the private discardlist
	 * it will check if you still have a rumourcard
	 * @param cardNum which card to discard
	 * @return 0 if succeed,  -1 failed
	 */
	public int disCardCard(int cardNum){
		if(this.playerRumourCardList.size() == 0){
			System.out.println("you don't have any cards");
			return -1;
		}
		SetUp.discardedRumourCard.add(this.playerRumourCardList.get(cardNum));
		//this.playerRevealedCardList.add(this.playerRumourCardList.get(cardNum));
		playerRumourCardList.remove(cardNum);
		return 0;
	}

	/**
	 * for a player to discard cards
	 * both to the commom discardlist and the private discardlist
	 * it will check if you still have a rumourcard
	 * @param nameCard name of the card
	 * @return 0 if succeed,  -1 failed
	 */
	public int disCardCard(String nameCard){
		if(this.playerRumourCardList.size() == 0){
			System.out.println("you don't have any cards");
			return -1;
		}
		for(RumourCard r: this.playerRumourCardList){
			if(r.name().equals(nameCard)){
				SetUp.discardedRumourCard.add(r);
				//playerRevealedCardList.add(r);
				this.playerRumourCardList.remove(r);
				break;
			}
		}
		//System.out.println("no such card");
		return 0;
	}

	public CopyOnWriteArrayList<RumourCard> getPlayerDiscardList(){
		return this.playerRevealedCardList;
	}

	public void showPlayerDiscardList(){
		for(RumourCard r: this.playerRevealedCardList){
			System.out.println(r.name());
		}
	}

	/**
	 * 
	 * @return true if he is the winner of last turn
	 */
	public boolean ifIsWinnerLastTurn(){
		return isWinnerLastTurn;
	}

	public void setWinnerLastTurn(boolean b){
		this.isWinnerLastTurn = b;
	}

}

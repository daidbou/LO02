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
	protected boolean isEvilEye=false;
	protected boolean isWinnerLastTurn=false;
	Scanner in = new Scanner(System.in);

	public Player(){}

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
	
		}else if(!pTurn2.ifIdentityReavealed()){
			System.out.println(name+" accuse "+pTurn2.getName());
			//sc.close();
			return pTurn2;
		}else{
			System.out.println(pTurn2.getName() + " has already revealed his identity, try again");

		}
		}
		
	}
	/**
	 * Start the hunt process.
	 * Select a Rumour card in the player RumourCard list then use his Hunt! ability
	 * @param playerList list of player
	 * @return pNextTurn, the next player for the round
	 */
	public Player hunt(List<Player> playerList) {
		
		showCards();
		System.out.println(" enter 1 for the first card");
		int cardNum = in.nextInt() - 1 ;
		Player pNextTurn;
		
		while(true){

			if(playerRumourCardList.get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()>0){
				pNextTurn = playerRumourCardList.get(cardNum).skillHunt(name,playerList);
				break;
			}
			else if(playerRumourCardList.get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()==0){
				System.out.println("Sorry you don't have any revealed Rumour Card, you can't play Pointed Hat");
				showCards();
				System.out.println(" enter 0 for the first card");
				cardNum = in.nextInt();
			}
			else{

				if(!(playerRumourCardList.get(cardNum).name().equals("The Inquisition") || playerRumourCardList.get(cardNum).name().equals("Angry Mob"))){
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
		}
		
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
		Player pNextTurn;

		while(true){
			if(playerRumourCardList.get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()>0){
				pNextTurn = playerRumourCardList.get(cardNum).skillHunt(name,playerList);
				break;
			}
			else if(playerRumourCardList.get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()==0){
				System.out.println("Sorry you don't have any revealed Rumour Card, you can't play Pointed Hat");
				showCards();
				System.out.println(" enter 0 for the first card");
				cardNum = in.nextInt();

			}
			else{
				pNextTurn = playerRumourCardList.get(cardNum).skillWitch(pTurn1.getName(),this.name,playerList);
				break;

			}

		}		
		//use a method in setup
		return pNextTurn ;
		
	}

	/**
	 * Show player's identity
	 */
	public void showIdentity(){
		System.out.println(name+" reveals his identity");
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
		System.out.println("");
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
	 * @return  witch 1 refers to witch, 0 refers villager
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
			this.point += num;
		}else{
			if(this.point > num){
				this.point -= num;
			}else{
				this.point = 0;
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

	/** player reveals his identity
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
		isWinnerLastTurn = false;
		isBroomstick = false;
		isWart = false;
		isEvilEye =false;
			
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

	/**
	 * set true if the player reveal a broomstick rumourCard
	 */
	public void isBroomstick(){

		this.isBroomstick = true;
 
	}

	/**
	 * return true if the player has reveal a broomstick Card
	 */
	public boolean getIsBroomstick(){
		return this.isBroomstick;
	}

	/**
	 * set true if the player reveal a wart rumourCard
	 */
	public void isWart(){
		this.isWart = true;
	}

	/**
	 * return true if the player has revealed a wart rumourCard
	 * @return
	 */
	public boolean getIsWart(){
		return this.isWart;
	}
	/**
	 * set true if the player reveal Evil Eye
	 * @return
	 */
	public void isEvilEye(){
		 isEvilEye=true;
	}

	/**
	 * return true if the player reveal Evil Eye
	 * @return
	 */
	public boolean getIsEvilEye(){
		return isEvilEye;
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

	public CopyOnWriteArrayList<RumourCard> getPlayerRevealedCardList(){
		return this.playerRevealedCardList;
	}

	public void showPlayerRevealedList(){
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

	public void revealCardAndRemoveFromRumourCardList(RumourCard r){
		this.playerRevealedCardList.add(r);
		this.playerRumourCardList.remove(r);
	}

	public RumourCard stringToCard(String nameCard){

		RumourCard rumourCard; 
		
		for(RumourCard r: SetUp.rumourCardList){

			if(nameCard.equals(r.name())){
				rumourCard = r;
				return rumourCard;

			}
			else{}
		}
		return null;
	}
}

package simplifiedProjet;

import vue.InterfaceRound1;
import simplifiedProjet.RumourCard.RumourCard;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player implements Preparation{
	protected String name;
	protected int identity;//1 = witch, 0 = villager
	protected boolean identityReavealed ;
	protected int point;
	private CopyOnWriteArrayList<RumourCard> playerRumourCardList;
	protected CopyOnWriteArrayList<RumourCard> playerRevealedCardList;
	protected boolean isOutOfTurn = false;
	protected int virtual ; // 1 = virtual
	protected boolean isBroomstick = false;
	protected boolean isWart = false;
	protected boolean isEvilEye=false;
	protected boolean isWinnerLastTurn=false;
	private boolean onTurn1 = false;
	private boolean isAccused = false;
	private InterfaceRound1 ir1;
	private boolean lock = true;
	private boolean onTurn2 = false;


	

	Scanner in = new Scanner(System.in);

	public Player(){
		
	}

	public Player(String name, int identity, CopyOnWriteArrayList<RumourCard> rumourCardListP) {
		this.name = name;
		this.identity = identity;
		this.setPlayerRumourCardList(rumourCardListP);
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
	 * @param myThreadTurn1 
	 * @return
	 * @throws InterruptedException 
	 */
	public Player accuse(List<Player> playerList)  { 
		System.out.println("which player? ex: p1 b1");

		
		//Scanner sc = new Scanner(System.in);
		//String pName = sc.nextLine();

		String pName = this.getIr1().getPlayer2();
		Player pTurn2 = Preparation.isExistedP(pName,name, playerList);
		if(pTurn2 == null){
			//System.out.println("no such player, try again");

		}else if(!pTurn2.ifIdentityReavealed()){
			System.out.println(name+" accuse "+pTurn2.getName());
			return pTurn2;
		}else{
			System.out.println(pTurn2.getName() + " has already revealed his identity, try again");
		}
		return pTurn2;

	}
	/**
	 * Start the hunt process.
	 * Select a Rumour card in the player RumourCard list then use his Hunt! ability
	 * @param playerList list of player
	 * @return pNextTurn, the next player for the round
	 */
	public Player hunt(List<Player> playerList) {
		
		showCards();

		Player pNextTurn;
		String cardName = this.getIr1().getRumourCardName();
		RumourCard rumourCardChosen = null;
		for(RumourCard r :getPlayerRumourCardList()) {
			if(r.name().equals(cardName)) {
				rumourCardChosen = r;
				break;
			}
		}
		pNextTurn = rumourCardChosen.skillHunt(name,playerList);
		
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
		System.out.println(" enter 0 for the first card, // enter -1 to return");
		int cardNum = in.nextInt();
		Player pNextTurn;
		if(cardNum == -1){
			return this;
		}

		while(true){
			if(getPlayerRumourCardList().get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()>0){
				pNextTurn = getPlayerRumourCardList().get(cardNum).skillHunt(name,playerList);
				break;
			}
			else if(getPlayerRumourCardList().get(cardNum).name().equals("Pointed Hat") && playerRevealedCardList.size()==0){
				System.out.println("Sorry you don't have any revealed Rumour Card, you can't play Pointed Hat");
				showCards();
				System.out.println(" enter 0 for the first card");
				cardNum = in.nextInt();

			}
			else{
				pNextTurn = getPlayerRumourCardList().get(cardNum).skillWitch(pTurn1.getName(),this.name,playerList);
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
		for (int i = 0; i < getPlayerRumourCardList().size(); i++) {
			System.out.println(getPlayerRumourCardList().get(i).name());
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
		
		setPlayerRumourCardList(l);
		playerRevealedCardList = new CopyOnWriteArrayList<RumourCard>();	
}

	
	/** 
	 * @return CopyOnWriteArrayList<RumourCard>
	 */
	public CopyOnWriteArrayList<RumourCard> getRumourCardListPlayer(){	
		return getPlayerRumourCardList();
	}

	/**
	 * if player is a bot, return 1
	 * @return
	 */
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
		if (getPlayerRumourCardList().size() == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * add a card in the hand of the player
	 */
	public void addCardInTheList(RumourCard rumourCard){

		this.getPlayerRumourCardList().add(rumourCard);
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
		if(this.getPlayerRumourCardList().size() == 0){
			System.out.println("you don't have any cards");
			return -1;
		}
		SetUp.discardedRumourCard.add(this.getPlayerRumourCardList().get(cardNum));
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
		if(this.getPlayerRumourCardList().size() == 0){
			System.out.println("you don't have any cards");
			return -1;
		}
		for(RumourCard r: this.getPlayerRumourCardList()){
			if(r.name().equals(nameCard)){
				SetUp.discardedRumourCard.add(r);
				//playerRevealedCardList.add(r);
				this.getPlayerRumourCardList().remove(r);
				break;
			}
		}
		return 0;
	}

	
	/** 
	 * @return CopyOnWriteArrayList<RumourCard>
	 */
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

	
	/** 
	 * @param b
	 */
	public void setWinnerLastTurn(boolean b){
		this.isWinnerLastTurn = b;
	}

	
	/** 
	 * @param r
	 */
	public void revealCardAndRemoveFromRumourCardList(RumourCard r){
		this.playerRevealedCardList.add(r);
		this.getPlayerRumourCardList().remove(r);
	}

	
	/** 
	 * @param nameCard
	 * @return RumourCard
	 */
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

	
	/** 
	 * @param id
	 */
	public void setIdentity(int id) {
		this.identity = id;
	}
	
	
	/** 
	 * @return InterfaceRound1
	 */
	public InterfaceRound1 getIr1() {
		return ir1;
	}
	
	/** 
	 * @return boolean
	 */
	public boolean isLock() {
		return lock;
	}

	
	/** 
	 * @param lock
	 */
	public void setLock(boolean lock) {
		this.lock = lock;
	}

	
	/** 
	 * @param ir1
	 */
	public void setIr1(InterfaceRound1 ir1) {
		this.ir1 = ir1;
	}

	
	/** 
	 * @return CopyOnWriteArrayList<RumourCard>
	 */
	public CopyOnWriteArrayList<RumourCard> getPlayerRumourCardList() {
		return playerRumourCardList;
	}

	
	/** 
	 * @param playerRumourCardList
	 */
	public void setPlayerRumourCardList(CopyOnWriteArrayList<RumourCard> playerRumourCardList) {
		this.playerRumourCardList = playerRumourCardList;
	}
	
	/** 
	 * @return boolean
	 */
	public boolean isAccused() {
		return isAccused;
	}

	
	/** 
	 * @param isAccused
	 */
	public void setAccused(boolean isAccused) {
		this.isAccused = isAccused;
	}

	
	/** 
	 * return onTurn1
	 * @return boolean
	 */
	public boolean isOnTurn1() {
		return onTurn1;
	}

	
	/** 
	 * set onTurn
	 * @param onTurn boolean
	 */
	public void setOnTurn1(boolean onTurn) {
		this.onTurn1 = onTurn;
	}
	
	/** 
	 * return isOnTurn2 
	 * @return boolean
	 */
	public boolean isOnTurn2() {
		return onTurn2;
	}

	
	/** 
	 * set the variable onTurn2
	 * @param onTurn2 boolean
	 */
	public void setOnTurn2(boolean onTurn2) {
		this.onTurn2 = onTurn2;
	}

}

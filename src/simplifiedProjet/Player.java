package simplifiedProjet;
//import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;

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
		this. rumourCardListPlayer= rumourCardListP;
		this.identityReavealed = false;
		this.isOutOfTurn = false;
		this.virtual = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.virtual = 0;
	}

	public Player accuse(List<Player> playerList) { // 改好了
		System.out.println(name+" is a real");
		System.out.println("which player? ex: p1 b1");
		Scanner sc = new Scanner(System.in);
		String pName = sc.nextLine();
		Player pTurn2 = Preparation.isExiste(pName,name, playerList);
		if(pTurn2 == null){
			System.out.println("no such player, try again");
			return null;
		}else if(pTurn2.ifIdentityReavealed() == false){
			System.out.println(name+" accuse "+pTurn2.getName());
			return pTurn2;
		}else{
			System.out.println(pTurn2.getName() + " has already revealed his identity, try again");
			return null;
		}
	}
	
	public Player hunt(List<Player> playerList) {
		
		showCards();
		System.out.println(" entre 0 for the first card");
		int cardNum = in.nextInt();
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillHunt(name,playerList);// name here is this player's name
		return pNextTurn ;
	}
	
	public Player witch(List<Player> playerList) {
		
		showCards();
		System.out.println(" entre 0 for the first card");
		int cardNum = in.nextInt();
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillWitch(name,playerList);// name here is this player's name
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

	public void showCards() {//show it's rumourcards
		System.out.println(name+" and cards with ");
		for (int i = 0; i < rumourCardListPlayer.size(); i++) {
			System.out.println(rumourCardListPlayer.get(i).name());
		}
		System.out.println();
	}
	
	public String getName() {
		return name;
	}
	
	public int getIdentity(){
		return identity;
	}

	public void raisePoints(int num){
		for(int i = 0; i < num; i++){
			point++;
		}
	}
	
	public int getPoint(){
		return point;
	}

	public void revealIdentity(){
		this.identityReavealed = true;
	}

	public boolean ifIdentityReavealed(){ // getter of the identityReavealed
		return identityReavealed;
	}

	public void setIsOutOfTurn(boolean t){
		isOutOfTurn = t;
	}

	public boolean ifIsOutOfTurn(){
		return isOutOfTurn;
	}

	public int showPoint(){
		return point;
	}
	
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

}

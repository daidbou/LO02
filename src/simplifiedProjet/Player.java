package simplifiedProjet;
//import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;

import java.util.List;
import java.util.Scanner;

public class Player implements Preparation{
	private String name;
	private int identity;//1 = witch, 0 = villager
	private boolean identityReavealed ;
	private int point;
	private List<RumourCard> rumourCardListPlayer;
	public List<RumourCard> disCardCardListPlayer ;
	private boolean isOutOfTurn;
	Scanner sc = new Scanner(System.in);

	public Player(){};

	public Player(String name, int identity, List<RumourCard> rumourCardListP) {
		this.name = name;
		this.identity = identity;
		this. rumourCardListPlayer= rumourCardListP;
		this.identityReavealed = false;
		this.isOutOfTurn = false;

	}
	
	public Player(String name) {
		this.name = name;
	}

	public void accuse(Player player) {
		
		System.out.println(this.name+" accuse "+player.name);
	}
	
	public Player hunt(int cardNum) {
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillWitch(name);// name here is this player's name
		return pNextTurn;
	}
	
	public Player witch(int cardNum) {
		
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillWitch(name);// name here is this player's name
		return pNextTurn;
	}

	public void showIdentity(){
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
		if (this.identityReavealed == true){
			return true;
		}else{
			return false;
		}

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
		System.out.println("what identity do you want to be? (1 for witch, 0 for villager)");
		int id = sc.nextInt();
		identity = id;

	}
	public List<RumourCard> getRumourCardListPlayer(){
		
		return rumourCardListPlayer;
	}
}

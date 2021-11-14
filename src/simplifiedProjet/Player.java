package simplifiedProjet;
import java.util.Scanner;

import simplifiedProjet.RumourCard.RumourCard;

import java.util.List;

public class Player extends Preparation{
	private String name;
	private int identity;//1 = witch, 0 = villager
	private int point;
	public List<RumourCard> rumourCardListPlayer;

	public Player(){};

	public Player(String name, int identity, List<RumourCard> rumourCardListP) {
		this.name = name;
		this.identity = identity;
		this. rumourCardListPlayer= rumourCardListP;
	}
	
	public void accuse(Player player) {
		
		System.out.println(this.name+" accuse "+player.name);
	}
	
	public void hunt(Player player) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Which card do you want to use?");
			int i = in.nextInt();
			System.out.print(this.name);
			rumourCardListPlayer.get(i).skillHunt();
		}
		System.out.println(player.name);
	}
	
	public Player witch(int cardNum) {
		
		Player pNextTurn = rumourCardListPlayer.get(cardNum).skillWitch();	
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

	public void raisePoints(){
		point++;
	}
}

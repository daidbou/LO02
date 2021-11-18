package simplifiedProjet.RumourCard;


import simplifiedProjet.Player;
//import simplifiedProjet.Test;


public class AngryMob implements RumourCard {

	int flag = 1;
	String name = "Angry Mob";
	public RumourCard1(int flag) {
		flag = 1;
	}

	public RumourCard1() {
	}

	@Override
	public Player skillWitch(String name) {

		System.out.println("Take next turn");
		return null;
		
	}

	@Override
	public Player skillHunt(String name) {
		System.out.print(" Skill Hunt " + name);
		return null;
		
	}

	@Override
	public void ToString() {
		
		System.out.print(name + " ability");
	}

	@Override
	public String name() {
		return name;
	}

	
	

}

package simplifiedProjet.RumourCard;


import simplifiedProjet.Player;
import simplifiedProjet.Test;


public class RumourCard1 implements RumourCard {

	String name = "Angry Mob";

	@Override
	public Player skillWitch() {

		System.out.println("Take next turn");
		return null;
		
	}

	@Override
	public Player skillHunt() {
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

package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class RumourCard2 implements RumourCard {
	int flag = 2;
	String name = "The Inquisition";
	@Override
	public Player skillWitch(String name) {
		System.out.print(" Skill Witch " + name);	
		return null;
	}

	@Override
	public Player skillHunt(String name) {
		System.out.print(" Skill Hunt " + name);
		return null;
	}

	@Override
	public void ToString() {
		
		System.out.print(name + "ability");
	}

	@Override
	public String name() {
		
		return name;
	}
	
}

package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class RumourCard3 implements RumourCard{
	String name = "Pointed Hat";
	@Override
	public Player skillWitch() {
		System.out.print(" Skill Witch " + name);	
		return null;
	}

	@Override
	public Player skillHunt() {
		System.out.print(" Skill Hunt " + name);
		return null;
	}

	@Override
	public void ToString() {
		
		System.out.print("  ");
	}

	@Override
	public String name() {

		return name;
	}
	
}

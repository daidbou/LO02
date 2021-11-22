package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class HookedNose implements RumourCard{
	String name = "Hooked Nose";
	@Override
	public Player skillWitch(String name) {
		System.out.print(" Skill Witch " + name);	
		return null;
	}

	@Override
	public Player skillHunt(String name) {
		System.out.print(" Skill Hunt " + name);
		System.out.println("Take next turn");
		return null;
	}

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch : \n");
		sb.append("Take one card from the hand of the player who accused you\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Choose next player\n");
		sb.append("Before their turn, take a random card from their hand and add it to your hand");

        return sb.toString();
    }

	@Override
	public String name() {
		
		return name;
	}
	
}

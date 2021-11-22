package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class TheInquisition implements RumourCard {

	int flag = 2;
	
	String name = "The Inquisition";
	
	@Override
	public Player skillWitch(String name) {
		System.out.print(" Skill Witch " + name);
		System.out.println("Take next turn");	
		return null;
	}

	@Override
	public Player skillHunt(String name) {
		System.out.print(" Skill Hunt " + name);
		return null;
	}

    @Override
    public String ToString() {

        StringBuffer sb = new StringBuffer();
        
		sb.append("Witch : \n");
		sb.append("Discard a card from your hand\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Only playable if you have been revealed as a Villager\n");
		sb.append("Choose next player\n");
		sb.append("Before their turn, secretly look at their identity");

        return sb.toString();
    }

	@Override
	public String name() {
		
		return name;
	}
	
}

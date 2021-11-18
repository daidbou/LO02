package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class TheInquisition implements RumourCard {
	
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
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append("Witch : ");
        sb.append("Hunt : ");

        return sb.toString();
    }

	@Override
	public String name() {
		
		return name;
	}
	
}

package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class PointedHat implements RumourCard{

	int flag = 1;
	String name = "Pointed Hat";
	@Override
	public Player skillWitch(String name) {
		System.out.println("skill witch for " + name);
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
        
		sb.append("Witch :\n ");
		sb.append("Only playable if you have a revealed Rumour card\n");
		sb.append("Take one of your own revealed Rumour cards into your hand\n");
		sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
		sb.append("Only playable if you have a revealed Rumour card\n");
		sb.append("Take one of your own revealed Rumour cards into your hand\n");
		sb.append("Choose next player");

        return sb.toString();
    }

	@Override
	public String name() {

		return name;
	}
	
}

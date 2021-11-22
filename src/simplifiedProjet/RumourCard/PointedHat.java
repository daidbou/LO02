package simplifiedProjet.RumourCard;

import java.util.List;

import simplifiedProjet.Player;

public class PointedHat implements RumourCard{

	int flag = 1;
	String name = "Pointed Hat";
	

    @Override
    public String ToString() {
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

	

	@Override
	public Player skillWitch(String name, List<Player> playerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player skillWitchBot(String name, List<Player> playerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player skillHunt(String name, List<Player> playerList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player skillHuntBot(String name2, List<Player> playerList) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

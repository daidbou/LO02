package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class EvilEye implements RumourCard{
    String name = "Evil Eye";
    @Override
    public Player skillWitch(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player skillHunt(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch : \n");
        sb.append("Choose next player");
        sb.append("On their turn they must accuse a player other than you, if possible\n\n");

        sb.append("Hunt : \n");
        sb.append("Choose next player\n");
        sb.append("On their turn they must accuse a player other than you, if possible");

        return sb.toString();
    }

    @Override
	public String name() {
		
		return name;
	}
    
}

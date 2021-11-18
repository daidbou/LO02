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

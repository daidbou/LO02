package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class Toad implements RumourCard{
    
    String name ="Toad";
    
    @Override
    public Player skillWitch(String name) {
        // TODO Auto-generated method stub
        System.out.println("Take next turn");
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
        sb.append("Witch : Take next turn");
        sb.append("Hunt : ");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return name;
	}

    
}

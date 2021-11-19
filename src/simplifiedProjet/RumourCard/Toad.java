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
        
        sb.append("Witch : \n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Reveal your identity\n");
        sb.append("Witch: Player to your left takes next turn\n");
        sb.append("Villager: Choose next player");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return name;
	}

    
}

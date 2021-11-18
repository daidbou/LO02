package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;

public class PetNewt implements RumourCard{
    
    String name ="Pet Newt";

    @Override
    public Player skillWitch(String name) {
        // TODO Auto-generated method stub
        System.out.println("Take next turn");
        return null;
    }

    @Override
    public Player skillHunt(String name) {
        // TODO Auto-generated method stub
        System.out.println("");
        return null;
    }

    @Override
    public String ToString() {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append("Witch : Take newt turn");
        sb.append("Hunt : Take a revealed Rumour card from any other player into your hands");
        sb.append("Choose next player");

        return sb.toString();
    }

    @Override
	public String name() {
		
		return name;
	}
 
    
}

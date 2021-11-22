package simplifiedProjet.RumourCard;
import simplifiedProjet.Preparation;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class DuckingStool implements RumourCard {
    String name ="Ducking Stool";
    @Override
    public Player skillWitch(String name) {
        System.out.println("choose next player");
        String pNextTurn = in.nextLine();
        Player pMe = Preparation.isExiste(name, SetUp.playerList);//returns player pMe 
        return Preparation.isExiste(pNextTurn,pMe,SetUp.playerList);// in order that he cannot choose himself as player next turn
        
    }

    @Override
    public Player skillHunt(String name) {
        // TODO Auto-generated method stub
        System.out.println("Choose next player");
        return null;
    }

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Choose a player. They must revealed their identity or discard a card from their hand\n");
        sb.append("Witch: You gain 1pt. You take newt turn\n");
        sb.append("Villager: You lose 1pt.They take next turn\n");
        sb.append("If they discard: They take next turn");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return name;
	}
 
    
}

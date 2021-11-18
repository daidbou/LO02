package simplifiedProjet.RumourCard;
import simplifiedProjet.Preparation;
import simplifiedProjet.Player;

public class RumourCard7 implements RumourCard {
    String name ="Ducking Stool";
    @Override
    public Player skillWitch(String name) {
        System.out.println("choose next player");
        String pNextTurn = in.nextLine();
        Player pMe = Preparation.isExiste(name, Preparation.playerList);//returns player pMe 
        return Preparation.isExiste(pNextTurn,pMe,Preparation.playerList);// in order that he cannot choose himself as player next turn
        
    }

    @Override
    public Player skillHunt(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void ToString() {
        // TODO Auto-generated method stub
        
    }
    @Override
	public String name() {
		
		return name;
	}
 
    
}

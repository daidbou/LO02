package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.Preparation;

public class PetNewt implements RumourCard{
    
    String nameCard ="Pet Newt";

    

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Witch :\n");
        sb.append("Take next turn\n\n");

        sb.append("Hunt : \n");
        sb.append("Take a revealed Rumour card from any other player into your hands\n");
        sb.append("Choose next player");

        return sb.toString();
    }

    @Override
	public String name() {
		
		return nameCard;
	}

   

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		System.out.println("Take next turn");
		Player p1= Engine.nameToPlayer(playerList, accused);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, accuser));
	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {//TODO implement

		Player p1= Engine.nameToPlayer(playerList, hunter);
		String playerRobbed;

		Scanner sc = new Scanner(System.in);
		System.out.println("choose a player first");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
		do{
			playerRobbed =  sc.nextLine();
		}while(Preparation.isExistedP(playerRobbed, playerList)!=null);
		System.out.println("choose one of "+playerRobbed+" revealed cards");
		for()
		

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {

		Player p1= Engine.nameToPlayer(playerList, hunter);

		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
	}
 
    
}

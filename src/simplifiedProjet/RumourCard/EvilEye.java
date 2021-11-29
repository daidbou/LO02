package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.Preparation;
import simplifiedProjet.SetUp;

public class EvilEye implements RumourCard{
    String nameCard = "Evil Eye";
    
    

    @Override
    public String toString() {
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
		
		return nameCard;
	}

   

	@Override
	public Player skillWitch(String accuser,String accused, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.isEvilEye();
		
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return EvilEye.chooseNextPlayerForReal(playerList, accused);
	}

	@Override
	public Player skillWitchBot(String accuser,String accused, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.isEvilEye();
		
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		
		return EvilEye.chooseNextPlayerForBot(playerList, accused);

	}

	@Override
	public Player skillHunt(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
		return EvilEye.chooseNextPlayerForReal(playerList, hunter);
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1 = Engine.nameToPlayer(playerList, hunter);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

		return EvilEye.chooseNextPlayerForBot(playerList, hunter);
	}
    
	public static Player chooseNextPlayerForReal(List<Player> playerList, String pUser){
		String pNextTurn = "";
		int playerLeft=0;
		int nextPlayerIndex=0;

		for(int i=0; i< SetUp.allPlayerList.size(); i++){
			if(!SetUp.allPlayerList.get(i).ifIsOutOfTurn() && !SetUp.allPlayerList.get(i).getName().equals(pUser)){
				playerLeft++;
				nextPlayerIndex += i; 
			}
			else{

			}
		}
		if(playerLeft==1){
			return SetUp.allPlayerList.get(nextPlayerIndex);
		}

		else{
			System.out.println("choose next player other than" + pUser+ " (p1 or b1 for example :");
			for(Player p: playerList){
				System.out.println(p.getName());
			}
			Scanner sc = new Scanner(System.in);
			do{
				pNextTurn = sc.nextLine();
			}while(!Preparation.isExistedForPlayer(pUser,pNextTurn, playerList) && !Engine.nameToPlayer(playerList, pNextTurn).getIsEvilEye());
			return Engine.nameToPlayer(playerList, pNextTurn);
		}
	}

	public static Player chooseNextPlayerForBot(List<Player> playerList, String pUser){
		int nopRandom = 0;
        String pNextTurn = "";
		int playerLeft=0;
		int nextPlayerIndex=0;

		for(int i=0; i< SetUp.allPlayerList.size(); i++){
			if(!SetUp.allPlayerList.get(i).ifIsOutOfTurn() && !SetUp.allPlayerList.get(i).getName().equals(pUser)){
				playerLeft++;
				nextPlayerIndex += i; 
			}
			else{

			}
		}
		if(playerLeft == 1){
			return SetUp.allPlayerList.get(nextPlayerIndex);
		}
		else{
			do{
            nopRandom = (int)(Math.random()*playerList.size());
            pNextTurn = playerList.get(nopRandom).getName();
        	}while(!Preparation.isExistedForBot(pUser,pNextTurn, playerList) && !Engine.nameToPlayer(playerList, pNextTurn).getIsEvilEye());

		return Engine.nameToPlayer(playerList, pNextTurn);
		}
	}
}

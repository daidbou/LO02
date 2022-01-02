package simplifiedProjet.RumourCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.Preparation;


public class PetNewt implements RumourCard{
    
    String nameCard ="PetNewt";

    

    @Override
    public String toString() {
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
	public Player skillHunt(String hunter, List<Player> playerList) {

		Player p1= Engine.nameToPlayer(playerList, hunter);
		String playerRobbed;
		int cardNum;
		List<Player> playerWithRevealedCards = new ArrayList<>();

		Scanner sc = new Scanner(System.in);
		
		System.out.println("choose a player first");
		for(Player p: playerList){
			if(p.getPlayerRevealedCardList().size()>0){
				System.out.println(p.getName());
				playerWithRevealedCards.add(p);

			}
		}

		if(playerWithRevealedCards.size()==0){
			System.out.println("no player have reveal their rumour cards yet");
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Broomstick.chooseNextPlayerForReal(playerList, hunter);
		}
		else{
			do{
				playerRobbed =  sc.nextLine();
			}while(Preparation.isExistedP(playerRobbed, playerList)==null || Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().size()==0);
			
			while(true){
				System.out.println("choose one of "+playerRobbed+" revealed cards");
				Engine.nameToPlayer(playerList, playerRobbed).showPlayerRevealedList();
			
				cardNum = sc.nextInt();
				System.out.println("enter 0 for the card");
				if(0<=cardNum && cardNum<Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().size()){
					p1.getRumourCardListPlayer().add(Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().get(cardNum));
					Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().remove(Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().get(cardNum));
					break;
				}
				else{
					System.out.println("please select a number between 0 and "+ (Engine.nameToPlayer(playerList, playerRobbed).getPlayerRevealedCardList().size()-1));
				}
			}
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
		}
	}

	@Override
	public Player skillHuntBot(String hunter, List<Player> playerList) {
		Player p1= Engine.nameToPlayer(playerList, hunter);
		Player playerRobbed;
		int cardNum;
		List<Player> playerWithRevealedCards = new ArrayList<>();
	
		
		System.out.println("choose a player first");
		for(Player p: playerList){
			if(p.getPlayerRevealedCardList().size()>0){
				System.out.println(p.getName());
				playerWithRevealedCards.add(p);

			}
		}
		
		if(playerWithRevealedCards.size()==0){
			System.out.println("no player have reveal their rumour cards yet");
			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Broomstick.chooseNextPlayerForBot(playerList, hunter);
		}
		else{

			int playerNum = (int) Math.random()*playerWithRevealedCards.size();
			playerRobbed =  playerWithRevealedCards.get(playerNum);

			System.out.println("choose one of "+playerRobbed.getName()+" revealed cards");
			playerRobbed.showPlayerRevealedList();
		
			cardNum = (int) Math.random()*playerRobbed.getPlayerRevealedCardList().size();
			p1.getRumourCardListPlayer().add(playerRobbed.getPlayerRevealedCardList().get(cardNum));
			System.out.println("you choose "+playerRobbed.getName()+playerRobbed.getPlayerRevealedCardList().get(cardNum).name()+" cards");
			playerRobbed.getPlayerRevealedCardList().remove(playerRobbed.getPlayerRevealedCardList().get(cardNum));
		
			

			p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));

			return Engine.nextPlayer(playerList, Engine.nameToPlayer(playerList, hunter));
		}
	}
}

package simplifiedProjet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
public class Test {

	
	
	public static List<Player> playerList;
	public static void main(String[] args) {
		
		RumourCard1 rumourCard1 = new RumourCard1();
		RumourCard2 rumourCard2 = new RumourCard2();
		RumourCard3 rumourCard3 = new RumourCard3();		RumourCard4 rumourCard4 = new RumourCard4();
		List<RumourCard> rumourCardList = new ArrayList<RumourCard>(){{
			add(rumourCard1);
			add(rumourCard2);
			add(rumourCard3);
			add(rumourCard4);
		}};
			
	
		List<RumourCard> rumourCardListP1 = rumourCardList.subList(0, 2);
		List<RumourCard> rumourCardListP2 = rumourCardList.subList(2, 4);// needs to be controlled, it's not random yet
		Player p1 = new Player("p1",rumourCardListP1);
		Player p2 = new Player("p2",rumourCardListP2);
		List<Player> playerList = new ArrayList<Player>(){{
			add(p1);
			add(p2);
		}};
		
		
		//p1.ToString();
		//p2.ToString();
		Scanner in = new Scanner(System.in);
	
		
		while(true) {
			Player pTurn1 = p1;
			Player pTurn2;
			
			System.out.println(pTurn1.getName() + " accuse or hunt?");
			String choice = in.nextLine();
			if(choice.equals("accuse")) {
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = isExiste(pName,pTurn1, playerList);
				pTurn1.accuse(pTurn2);
				
			}else if(choice.equals("hunt")) {
				System.out.println("which player?");
				String pName = in.nextLine();
				pTurn2 = isExiste(pName,pTurn1, playerList);
				pTurn1.hunt(pTurn2);
			
				}	
			}
		}


	public static Player isExiste(String pName, Player pTurn1,List<Player> playerList) {
		for(Player p:playerList) {
			if(pName.equals(p.getName())) {
				if(!pName.equals(pTurn1.getName())){
					return p;
				}else{
					System.out.println("not yourself");
					return null;
				}
			}	
		}
		System.out.println("no player exit");
		return null;
		
		
	}
}


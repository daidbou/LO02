package simplifiedProjet.RumourCard;
import simplifiedProjet.Preparation;

import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class DuckingStool implements RumourCard {
    String nameCard ="Ducking Stool";
    /**
     *choose next player 
     *done
     */
    @Override
    public Player skillWitch(String accuser,String accused,List<Player> playerList) {
        String pNextTurn = "";
        System.out.println("choose next player(p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
        Scanner sc = new Scanner(System.in);
        do{
            pNextTurn = sc.nextLine();
        }while(!Preparation.isExistedForPlayer(accused,pNextTurn, playerList));
        return Engine.nameToPlayer(playerList, pNextTurn);
        
    }
    @Override
    /**
     *choose next player 
     *done
     */
    public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
        int nopRandom = 0;
        String pNextTurn = "";
        do{
            nopRandom = (int)(Math.random()*playerList.size());
            pNextTurn = playerList.get(nopRandom).getName();
        }while(!Preparation.isExistedForPlayer(accused,pNextTurn, playerList));
        return Engine.nameToPlayer(playerList, pNextTurn);
        
    }

  

    @Override
    public String ToString() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("Witch :\n");
        sb.append("Choose next player\n\n");
        
        sb.append("Hunt : \n");
        sb.append("Choose a player. They must revealed their identity or discard a card from their hand\n");
        sb.append("Witch: You gain 1pt. You take newt turn\n");
        sb.append("Villager: You lose 1pt.They take next turn\n");
        sb.append("If they discard: They take next turn");

        return sb.toString();
    }
    @Override
	public String name() {
		
		return nameCard;
	}
   
    
    @Override
    public Player skillHunt(String hunter, List<Player> playerList) {

        String hunted = "";
        System.out.println("choose next player(p1 or b1 for example :");
		for(Player p: playerList){
			System.out.println(p.getName());
		}
        Scanner sc = new Scanner(System.in);
        do{
            hunted = sc.nextLine();
        }while(!Preparation.isExistedForPlayer(hunter,hunted, playerList));

        Player pHunted = Engine.nameToPlayer(playerList, hunted);
        Player pHunter = Engine.nameToPlayer(playerList, hunter);
        System.out.println(pHunted.getName()+"you must revealed your identity or discard a card from their hand");
        System.out.println("Enter id for reveal identity, dc for discard a card");
        String choice = "";
        while(true){
            choice = sc.nextLine();
            if(choice.equals("id")){
                pHunted.showIdentity();
                pHunted.revealIdentity();
                if(pHunted.getIdentity() == 1){
                    System.out.println(pHunted.getName()+"is a Witch:"+pHunter.getName()+" gain 1pt. You take next turn");
                    pHunter.raisePoints(1);
                    pHunted.setIsOutOfTurn(true);
                    return pHunter;
                }else{
                    System.out.println(pHunted.getName()+"is a village:"+pHunter.getName()+" lose 1pt. "+pHunted.getName()+" take next turn");
                    pHunter.raisePoints(-1);
                    return pHunted;
                }
            }else if(choice.equals("dc")){
               
               System.out.println(pHunted.getName()+" you should discard one card");
               pHunted.showCards();
               int cardNum = sc.nextInt();
               pHunted.disCardCard(cardNum);
               return pHunted;
            }else{
                System.out.println("please re-enter");
                continue;
            }
        }

        //return Engine.nameToPlayer(playerList, pNextTurn);
        
    }
    @Override
    public Player skillHuntBot(String hunter, List<Player> playerList) {
        int nopRandom = 0;
        String hunted = "";
        do{
            nopRandom = (int)(Math.random()*playerList.size());
            hunted = playerList.get(nopRandom).getName();
        }while(!Preparation.isExistedForPlayer(hunter,hunted, playerList));

        Player pHunted = Engine.nameToPlayer(playerList, hunted);
        Player pHunter = Engine.nameToPlayer(playerList, hunter);
        Scanner sc = new Scanner(System.in);
        String choice = "";
        //int choiceBot = Math
        
        while(true){
            choice = sc.nextLine();
            if(choice.equals("id")){
                pHunted.showIdentity();
                pHunted.revealIdentity();
                if(pHunted.getIdentity() == 1){
                    System.out.println(pHunted.getName()+"is a Witch:"+pHunter.getName()+" gain 1pt. You take next turn");
                    pHunter.raisePoints(1);
                    pHunted.setIsOutOfTurn(true);
                    return pHunter;
                }else{
                    System.out.println(pHunted.getName()+"is a village:"+pHunter.getName()+" lose 1pt. "+pHunted.getName()+" take next turn");
                    pHunter.raisePoints(-1);
                    return pHunted;
                }
            }else if(choice.equals("dc")){
               
               System.out.println(pHunted.getName()+" you should discard one card");
               pHunted.showCards();
               int cardNum = sc.nextInt();
               pHunted.disCardCard(cardNum);
               return pHunted;
            }else{
                System.out.println("please re-enter");
                continue;
            }
        }

    }

    
    
}

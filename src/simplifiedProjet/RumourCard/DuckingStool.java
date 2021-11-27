package simplifiedProjet.RumourCard;
import simplifiedProjet.Preparation;

import java.text.BreakIterator;
import java.util.List;
import java.util.Scanner;

import simplifiedProjet.Engine;
import simplifiedProjet.Player;
import simplifiedProjet.SetUp;

public class DuckingStool implements RumourCard {//done
    String nameCard ="Ducking Stool";
    /**
     *choose next player 
     *done
     */
    @Override
    public Player skillWitch(String accuser,String accused,List<Player> playerList) {
    
        return Broomstick.chooseNextplayerForReal(playerList, accused);
        
    }
    @Override
    /**
     *choose next player 
     *done
     */
    public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
      
        return Broomstick.chooseNextPlayerForBot(playerList, accused);
        
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

        Player pHunted = Broomstick.chooseNextplayerForReal(playerList, hunter);
        Player pHunter = Engine.nameToPlayer(playerList, hunter);
        Player pNextTurn = huntDuckingStool(pHunter, pHunted);
        return pNextTurn;

       
    }

    @Override
    public Player skillHuntBot(String hunter, List<Player> playerList) {
       
        Player pHunted = Broomstick.chooseNextPlayerForBot(playerList, hunter);

        Player pHunter = Engine.nameToPlayer(playerList, hunter);
        Player pNextTurn = huntDuckingStool(pHunter, pHunted);
        return pNextTurn;


    }

    public Player huntDuckingStool(Player pHunter, Player pHunted){
        if(pHunted.isVirtual() == 0){//
            System.out.println(pHunted.getName()+"you must revealed your identity or discard a card from their hand");
            System.out.println("Enter id for reveal identity, dc for discard a card");
            
            String choice = "";
            while(true){
                Scanner sc = new Scanner(System.in);
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
                    System.out.println("enter 0 for the first card");
                    pHunted.showCards();
                    int cardNum = sc.nextInt();
                    pHunted.disCardCard(cardNum);
                    return pHunted;
                }else{
                    System.out.println("please re-enter");
                    continue;
                }
            }
        }else{// phunted is a bot
            boolean choice = Engine.choiceRandom();  
            if(choice){//bot shows identity
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
            }else{ //bot chooses to discard cards
                System.out.println(pHunted.getName()+" you should discard one card");
                pHunted.showCards();
                int cardNum = (int)(Math.random()*pHunted.getRumourCardListPlayer().size()-1);
                pHunted.disCardCard(cardNum);
                return pHunted;
            }         
        }


    }
 
}



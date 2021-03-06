package simplifiedProjet.RumourCard;

import java.util.List;
import java.util.Scanner;
import simplifiedProjet.Engine;
import simplifiedProjet.Player;


public class DuckingStool implements RumourCard {//done
    String nameCard ="DuckingStool";
    /**
     *choose next player 
     *done
     */
    @Override
    public Player skillWitch(String accuser,String accused,List<Player> playerList) {
    
        Player p1 = Engine.nameToPlayer(playerList, accused);
        p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
        return Broomstick.chooseNextPlayerForReal(playerList, accused);
        
    }
    @Override
    /**
     *choose next player 
     *done
     */
    public Player skillWitchBot(String accuser,String accused,List<Player> playerList) {
        
        Player p1 = Engine.nameToPlayer(playerList, accused);
		p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));
        return Broomstick.chooseNextPlayerForBot(playerList, accused);
        
    }

  

    @Override
    public String toString() {
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
       
        Player pHunted;
        Player p1 = Engine.nameToPlayer(playerList, hunter);

        
        while(true){
            pHunted = Broomstick.chooseNextPlayerForReal(playerList, hunter);
            if(!pHunted.getIsWart()){
                break;
            }
            else{
                System.out.println("select another player, "+pHunted.getName()+" has a Wart Rumour card Revealed");
            }
        }
        p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));


        return pHunted;   
    }

    @Override
    public Player skillHuntBot(String hunter, List<Player> playerList) {
       
        Player pHunted;
        Player p1 = Engine.nameToPlayer(playerList, hunter);

        
        while(true){
            pHunted = Broomstick.chooseNextPlayerForBot(playerList, hunter);
            if(!pHunted.getIsWart()){
                break;
            }
            else{

            }
        }
        p1.revealCardAndRemoveFromRumourCardList(p1.stringToCard(nameCard));


        return pHunted;   
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
                        System.out.println(pHunted.getName()+"is a Witch:"+pHunter.getName()+" gains 1pt. You take next turn");
                        pHunter.raisePoints(1);
                        pHunted.setIsOutOfTurn(true);
                        return pHunter;
                    }else{
                        System.out.println(pHunted.getName()+"is a villager:"+pHunter.getName()+" loses 1pt. "+pHunted.getName()+" take next turn");
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
                int cardNum = (int)(Math.random()*pHunted.getRumourCardListPlayer().size());
                pHunted.disCardCard(cardNum);
                return pHunted;
            }         
        }


    }
 
}



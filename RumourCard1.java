package simplifiedProjet;
import java.util.Scanner;


public class RumourCard1 implements RumourCard {

	Scanner in = new Scanner(System.in);
	
	@Override
	public Player SkillWitch() {
		System.out.print(" Skill Witch RumourCard1 ");	
		System.out.println("Choose next player");
		String pName = in.nextLine();
		return Test.isExiste(pName, Test.playerList);
		
		
	}

	@Override
	public Player SkillHunt() {
		System.out.print(" Skill Hunt RumourCard1 ");
		return null;
		
	}

	@Override
	public void ToString() {
		
		System.out.print(" RumourCard1 ");
	}

	@Override
	public String Name() {

		return null;
	}
	

}

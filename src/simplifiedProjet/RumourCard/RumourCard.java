package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;
import java.util.Scanner;

public interface RumourCard{
	
	int status = 0;
	String name = "";
	Scanner in = new Scanner(System.in);
	public Player skillWitch();
	public Player skillHunt(); 
	public void ToString();//shows its ability
	public String name();
}

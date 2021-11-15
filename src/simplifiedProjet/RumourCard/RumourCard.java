package simplifiedProjet.RumourCard;

import simplifiedProjet.Player;


import java.util.Scanner;

public interface RumourCard{
	
	int status = 0;// maybe for discard card
	String name = "";
	Scanner in = new Scanner(System.in);
	public Player skillWitch(String name);
	public Player skillHunt(String name); 
	public void ToString();//shows its ability
	public String name();
}

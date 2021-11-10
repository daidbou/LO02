package simplifiedProjet;
import java.util.Scanner;
import java.util.List;

public class Player {
	private String name;
	public List<RumourCard> rumourCardListPlayer;
	public Player(String name, List<RumourCard> rumourCardListP) {
		this.name = name;
		this. rumourCardListPlayer= rumourCardListP;
	}
	void accuse(Player player) {
		System.out.println(this.name+" accuse "+player.name);
	}
	
	void hunt(Player player) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Which card do you want to use?");
			int i = in.nextInt();
			System.out.print(this.name);
			rumourCardListPlayer.get(i).SkillHunt();
		}
		System.out.println(player.name);
	}
	void witch(Player player) {
		System.out.print(this.name);
		rumourCardListPlayer.get(1).SkillWitch();
		System.out.println(player.name);
		
	}

	public void ToString() {
		System.out.println(name+" and cards with ");
		for (int i = 0; i < rumourCardListPlayer.size(); i++) {
			rumourCardListPlayer.get(i).ToString();
		}
		System.out.println();
	}
	public String getName() {
		return name;
	}

}

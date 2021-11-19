package simplifiedProjet.RumourCard;


import simplifiedProjet.Player;
//import simplifiedProjet.Test;


public class AngryMob implements RumourCard {

	int flag = 1;
	String name = "Angry Mob";
	public AngryMob(int flag) {
		flag = 1;
	}

	public AngryMob() {
	}

	@Override
	public Player skillWitch(String name) {
		
		System.out.println("Take next turn");
		return null;
		
	}

	@Override
	public Player skillHunt(String name) {
		System.out.print(" Skill Hunt " + name);
		return null;
		
	}

    @Override
    public String ToString() {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append("Witch : Take next turn");
        sb.append("Hunt : ");

        return sb.toString();
    }

	@Override
	public String name() {
		return name;
	}

	
	

}

package simplifiedProjet;

public class State {
    private Player p;
    private String choice;

    public State(Player p, String choice){
        this.p = p;
        this.choice = choice;
    }
    public State(){

    }
    public State getState(){
        return this;
    }

    public void setState(Player p, String choice){
        this.p = p;
        this.choice = choice;
    }
  
}

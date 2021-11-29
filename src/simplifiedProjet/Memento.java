package simplifiedProjet;

public class Memento {
    private State state;
    public  Memento(State s){
        this.state = s;
    }
    public State getState(){
        return state;
    }
}

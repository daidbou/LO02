package simplifiedProjet;

public class Originator {
    private State state;
    public Memento creatMemento(){
        return (new Memento(state));
    }
    public void setMemento(Memento memento){
        state = memento.getState();
    }
    public void setState(State state){
        this.state = state;
    }
}

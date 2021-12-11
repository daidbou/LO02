package simplifiedProjet;

import controleur.Controleur;
import vue.MonInterface;

public class Game {
    public static void main(String[] args){
    	MonInterface inter = new MonInterface();
    	
         Engine engine = Engine.getEngine();
        Controleur controleur = new Controleur(engine, inter);
       
        //engine.player();
    }
}

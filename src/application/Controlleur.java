package application;

import javafx.scene.paint.Color;
import models.Personnage2D;

public class Controlleur {
	
	private Personnage2D perso;
	

	public Controlleur(){
	}
	
	public void init(int x, int y, int width, int height, Color couleur){
		
		perso = new Personnage2D(x, y, width, height, couleur);
		perso.ajoutFleches();
	}

	public Personnage2D getR0() {
		return perso;
	}

	public void setR0(Personnage2D r0) {
		this.perso = r0;
	}
}

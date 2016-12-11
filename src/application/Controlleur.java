package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.Personnage2D;

public class Controlleur {
	
	private Personnage2D perso;
	

	public Controlleur(){
	}

	public void init() {
		
		perso = new Personnage2D(0, 0, 100, 50, Color.AQUAMARINE, null);
	
	}

	public Personnage2D getR0() {
		return perso;
	}

	public void setR0(Personnage2D r0) {
		this.perso = r0;
	}
}

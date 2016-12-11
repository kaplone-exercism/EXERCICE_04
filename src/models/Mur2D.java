package models;

import java.util.List;

import enums.Orientation;
import enums.Sens;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

public class Mur2D extends Rectangle{
	
	private final Orientation orientation;
	private final int epaisseur;
	private final int position;
	private final int debut;
	private final int fin;
	private Rectangle2D rectangle2D;
	
	public Mur2D(Orientation orientation, int epaisseur, int position, int debut, int fin) {
		
		super(orientation == Orientation.HORIZONTAL ? debut : position,
		      orientation == Orientation.HORIZONTAL ? position : debut, 
		      orientation == Orientation.HORIZONTAL ? fin - debut : epaisseur,
		      orientation == Orientation.HORIZONTAL ? epaisseur : fin - debut);
		
		this.orientation = orientation;
		this.epaisseur = epaisseur;
		this.position = position;
		this.debut = debut;
		this.fin = fin;
		this.setFill(Settings.getCouleurMurs());
		
		if(horizontal()){
			this.rectangle2D = new Rectangle2D(debut, position, fin - debut, epaisseur);
		}
		else if(vertical()){
			this.rectangle2D = new Rectangle2D(position, debut, epaisseur, fin - debut);
		}	
	}
	
	public boolean estEnContact(Rectangle2D r){		
		return this.rectangle2D.contains(r);	
	}

    public Rectangle getRectangle(){
    	return this;
    }
    
    public Rectangle2D getRectangle2D(){
    	return this.rectangle2D;
    }

	public Orientation getOrientation() {
		return orientation;
	}

	public int getEpaisseur() {
		return epaisseur;
	}

	public int getPosition() {
		return position;
	}

	public int getDebut() {
		return debut;
	}

	public int getFin() {
		return fin;
	}

	public boolean horizontal(){
		return this.orientation == Orientation.HORIZONTAL;
	}
	
	public boolean vertical(){
		return this.orientation == Orientation.VERTICAL;
	}		
}

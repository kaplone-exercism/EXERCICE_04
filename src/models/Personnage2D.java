package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import enums.Sens;


public class Personnage2D extends Rectangle {

	private Rectangle2D rectangle2D;
	
	private final double surface;
	private ObservableList<Mur2D> murs;
	private Map<Sens, Fleche> fleches;
	
	public Personnage2D(double x, double y, double width, double height, Paint fill) {
		super(x, y, width, height);
		
		this.setFill(fill);
		this.rectangle2D = new Rectangle2D(x, y, width, height);
		
		this.surface = width * height;
		System.out.println(surface);
		this.murs = murs;
		
		fleches = new HashMap<Sens, Fleche>();
		
	}
	
	public void ajoutFleches(){
		fleches.put(Sens.BAS,
				    new Fleche(
				    		new Image("fleche_bas_20.png"),
                            this,
                            Sens.BAS));
		
		fleches.put(Sens.HAUT, new Fleche(new Image("fleche_haut_20.png"), this, Sens.HAUT));
		fleches.put(Sens.DROITE, new Fleche(new Image("fleche_droite_20.png"), this, Sens.DROITE));
		fleches.put(Sens.GAUCHE, new Fleche(new Image("fleche_gauche_20.png"), this, Sens.GAUCHE));
		
		for (Fleche f : fleches.values()){
			f.bindAttaches();
		}
	}
	
	public void deplacement(double x, double y){
		
		deplacement((int) x, (int) y);
	}
	
	public void deplacement(int x, int y){
		
		while (x != 0 || y != 0){
			
			if (x > 0){
				this.setX(this.getX() + 1);
				updateRectangle2D();
				if (enContact()){
					this.setX(this.getX() - 1);
					x = 0;
					updateRectangle2D();
				}
				else{
					x--;
				}
				
			}
			else if (x < 0){
				this.setX(this.getX() - 1);
				updateRectangle2D();
				if (enContact()){
					this.setX(this.getX() + 1);
					x = 0;
					updateRectangle2D();
				}
				else {
					x++;
				}
				
			}
			
			if (y > 0){
				this.setY(this.getY() + 1);
				updateRectangle2D();
				if (enContact()){
					this.setY(this.getY() - 1);
					y = 0;
					updateRectangle2D();
				}
				else {
					y--;
				}
				
			}
			else if (y < 0){
				this.setY(this.getY() - 1);
				updateRectangle2D();
				if (enContact()){
					this.setY(this.getY() + 1);
					y = 0;
					updateRectangle2D();
				}
				else {
					y++;
				}
			}
		}		
	}
	
	public boolean sansContact(){
		
		for (Mur2D m2D : murs){
			if (m2D.getRectangle2D().intersects(this.rectangle2D)){
				return false;
			}
		}
		return true;
		
	}
	
public boolean enContact(){
		
		return ! sansContact();
		
	}
	
    public void deformationGauche(double ratio){
    	
    	double widthSave = this.getWidth();
    	double heightSave = this.getHeight();
   	
    	this.setWidth(this.widthProperty().multiply(ratio).doubleValue());    	
    	this.setHeight(surface / this.getWidth());
    	
    	if (this.getWidth() < 20){
    		this.setWidth(20);	
    	}
    	else if(this.getHeight() < 20){
    		deformationGauche(1/ratio);
    	}
    	else {
    		
    		this.setX(this.getX() - (this.getWidth() - widthSave));
    		
    		updateRectangle2D();
    		
        	if (enContact()){
        		deformationGauche(1/ratio);	
        	}	
    	}	
    }
    
    public void deformationDroite(double ratio){
    	
    	double widthSave = this.getWidth();
    	double heightSave = this.getHeight();
   	
    	this.setWidth(this.widthProperty().multiply(ratio).doubleValue());    	
    	this.setHeight(surface / this.getWidth());
    	
    	if (this.getWidth() < 20){
    		this.setWidth(20);	
    	}
    	else if(this.getHeight() < 20){
    		deformationDroite(1/ratio);
    	}
    	else {
    		updateRectangle2D();
    		
        	if (enContact()){
        		deformationDroite(1/ratio);	
        	}	
    	}	
    }
    
    public void deformationHaut(double ratio){
    	
    	double widthSave = this.getWidth();
    	double heightSave = this.getHeight();

    	this.setHeight(this.heightProperty().multiply(ratio).doubleValue());
    	this.setWidth(surface / this.getHeight());
    	
    	if (this.getHeight() < 20){
    		this.setHeight(20);	
    	}
    	else if(this.getWidth() < 20){
    		deformationHaut(1/ratio);
    	}
    	else {
    		
    		this.setY(this.getY() - (this.getHeight() - heightSave));
    		
    		updateRectangle2D();

        	if (enContact()){
        		deformationHaut(1/ratio);
        	}	
    	}	
	}
    
    public void deformationBas(double ratio){
    	
    	double widthSave = this.getWidth();
    	double heightSave = this.getHeight();

    	this.setHeight(this.heightProperty().multiply(ratio).doubleValue());
    	this.setWidth(surface / this.getHeight());
    	
    	if (this.getHeight() < 20){
    		this.setHeight(20);	
    	}
    	else if(this.getWidth() < 20){
    		deformationBas(1/ratio);
    	}
    	else {
    		updateRectangle2D();

        	if (enContact()){
        		deformationBas(1/ratio);
        	}	
    	}	
	}
    
    public void montrerLesFleches(boolean in){
    	
    	if (in){
    		for (Fleche f : fleches.values()){
    			f.setOut(false);
        		f.setVisible(true);
        	}
    	}
    	else {
    		for (Fleche f : fleches.values()){
    			f.setOut(true);
        		f.setVisible(true);
        	}
    	}
    }
    
    public void cacherLesFleches(){
    	for (Fleche f : fleches.values()){
    		f.setVisible(false);
    	}
    }
	
	public void updateRectangle2D(){

		this.rectangle2D = new Rectangle2D(
                                   this.getX(),
                                   this.getY(),
                                   this.getWidth(),
                                   this.getHeight());
	}

	public Rectangle2D getRectangle2D() {
		return rectangle2D;
	}

	public List<Mur2D> getMurs() {
		return murs;
	}

	public void setMurs(ObservableList<Mur2D> murs) {
		this.murs = murs;
	}

	public Map<Sens, Fleche> getFleches() {
		return fleches;
	}
	
	
}

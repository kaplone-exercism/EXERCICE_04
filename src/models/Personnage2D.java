package models;

import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Personnage2D extends Rectangle {

	private Rectangle2D rectangle2D;
	
	private final double surface;
	private List<Mur2D> murs;
	
	public Personnage2D(double x, double y, double width, double height, Paint fill, List<Mur2D> murs) {
		super(x, y, width, height);
		
		this.setFill(fill);
		this.rectangle2D = new Rectangle2D(x, y, width, height);
		
		this.surface = width * height;
		this.murs = murs;
	}
	
	public void deplacement(int x, int y){
		
		while (sansContact()){
			
			this.setX(this.getX() + x > 0 ? 1 : 0);
			this.setY(this.getY() + y > 0 ? 1 : 0);
			
			x--;
			y--;
		}

		updateRectangle2D();
	}
	
	public boolean sansContact(){
		
		for (Mur2D m2D : murs){
			if (m2D.getRectangle2D().contains(this.rectangle2D)){
				return false;
			}
		}
		return true;
		
	}
	
    public void deformationHorizontal(double ratio){
    	
    	this.widthProperty().multiply(ratio);
    	this.setHeight(surface / this.getWidth());
		
		this.xProperty().add(this.getX() - this.rectangle2D.getMinX());
		this.yProperty().add(this.getY() - this.rectangle2D.getMinY());

		updateRectangle2D();
	}
    
    public void deformationVertical(double ratio){
    	
    	this.heightProperty().multiply(ratio);
    	this.setWidth(surface / this.getHeight());
		
		this.xProperty().add(this.getX() - this.rectangle2D.getMinX());
		this.yProperty().add(this.getY() - this.rectangle2D.getMinY());

		updateRectangle2D();
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

	public void setMurs(List<Mur2D> murs) {
		this.murs = murs;
	}
}

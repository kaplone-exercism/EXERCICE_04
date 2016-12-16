package models;

import javafx.scene.paint.Color;

public class Settings {
	
	static boolean retourContact = false;
	static boolean couleurContact = false;
	static Color couleurMurs = Color.BLACK;
	
	static boolean affPositionSouris = false;
	static boolean affInfosMurs = false;
	
	public static boolean isRetourContact() {
		return retourContact;
	}
	public static void setRetourContact(boolean retourContact) {
		Settings.retourContact = retourContact;
	}
	public static boolean isCouleurContact() {
		return couleurContact;
	}
	public static void setCouleurContact(boolean couleurContact) {
		Settings.couleurContact = couleurContact;
	}
	public static Color getCouleurMurs() {
		return couleurMurs;
	}
	public static void setCouleurMurs(Color couleurMurs) {
		Settings.couleurMurs = couleurMurs;
	}
	public static boolean isAffPositionSouris() {
		return affPositionSouris;
	}
	public static void setAffPositionSouris(boolean affPositionSouris) {
		Settings.affPositionSouris = affPositionSouris;
	}
	public static boolean isAffInfosMurs() {
		return affInfosMurs;
	}
	public static void setAffInfosMurs(boolean affInfosMurs) {
		Settings.affInfosMurs = affInfosMurs;
	}
	
	public static boolean isEdition(){
		return affInfosMurs || affPositionSouris;
	}
}

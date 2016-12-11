package models;

import javafx.scene.paint.Color;

public class Settings {
	
	static boolean retourContact = false;
	static boolean couleurContact = false;
	static Color couleurMurs = Color.BLACK;
	
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
	
	

}

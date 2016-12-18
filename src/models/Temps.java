package models;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Observable;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;


public class Temps {
	
	private int minutes;
	private int minutesTopJoueur;
	private int minutesTopMonde;
	private int secondes;
	private int secondesTopJoueur;
	private int secondesTopMonde;
	private int dixiemes;
	private int dixiemesTopJoueur;
	private int dixiemesTopMonde;
	
	Label h11c1; 
	Label h11c2;  
	Label h11c3; 
	Label h11c4;
	HBox h1;

	private Instant tempsDebut;
	
	public Temps(int minutesTopJoueur, int minutesTopMonde, int secondesTopJoueur,
			int secondesTopMonde, int dixiemesTopJoueur, int dixiemesTopMonde) {
		
		this.tempsDebut = Instant.now();
		
		this.minutes = 0;
		this.minutesTopJoueur = minutesTopJoueur;
		this.minutesTopMonde = minutesTopMonde;
		this.secondes = 0;
		this.secondesTopJoueur = secondesTopJoueur;
		this.secondesTopMonde = secondesTopMonde;
		this.dixiemes = 0;
		this.dixiemesTopJoueur = dixiemesTopJoueur;
		this.dixiemesTopMonde = dixiemesTopMonde;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getMinutesTopJoueur() {
		return minutesTopJoueur;
	}
	public void setMinutesTopJoueur(int minutesTopJoueur) {
		this.minutesTopJoueur = minutesTopJoueur;
	}
	public int getMinutesTopMonde() {
		return minutesTopMonde;
	}
	public void setMinutesTopMonde(int minutesTopMonde) {
		this.minutesTopMonde = minutesTopMonde;
	}
	public int getSecondes() {
		return secondes;
	}
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}
	public int getSecondesTopJoueur() {
		return secondesTopJoueur;
	}
	public void setSecondesTopJoueur(int secondesTopJoueur) {
		this.secondesTopJoueur = secondesTopJoueur;
	}
	public int getSecondesTopMonde() {
		return secondesTopMonde;
	}
	public void setSecondesTopMonde(int secondesTopMonde) {
		this.secondesTopMonde = secondesTopMonde;
	}
	public int getDixiemes() {
		return dixiemes;
	}
	public void setDixiemes(int dixiemes) {
		this.dixiemes = dixiemes;
	}
	public int getDixiemesTopJoueur() {
		return dixiemesTopJoueur;
	}
	public void setDixiemesTopJoueur(int dixiemesTopJoueur) {
		this.dixiemesTopJoueur = dixiemesTopJoueur;
	}
	public int getDixiemesTopMonde() {
		return dixiemesTopMonde;
	}
	public void setDixiemesTopMonde(int dixiemesTopMonde) {
		this.dixiemesTopMonde = dixiemesTopMonde;
	}
	
	public Temps getUpdate(){
		
		Instant instant = Instant.now();
		
		Duration duree = Duration.between(instant, tempsDebut);
			
		
		long minutes = duree.getSeconds() / 60;
		long secondes = duree.getSeconds() % 60;
		//String dixiemes = duree.getNano() % 100;
		
		this.h11c1.setText(minutes + "");
		this.h11c3.setText(secondes + "");
		
		h1.

		return this; 
	}
	
	public HBox getAffichage(){
		h1 = new HBox();
		VBox v1 = new VBox();
		VBox v2 = new VBox();
		Label l1 = new Label("Temps partie"); 
		Label l2 = new Label("Temps Maximal"); 
		HBox h11 = new HBox();
		h11c1 = new Label("00"); 
		Label h11pp = new Label(":"); 
		h11c3 = new Label("00"); 
		HBox h21 = new HBox();
		Label h21c1 = new Label("02"); 
		Label h21pp = new Label(":"); 
		Label h21c3 = new Label("00"); 
		
		h11.getChildren().addAll(h11c1, h11pp, h11c3);
		h21.getChildren().addAll(h21c1, h21pp, h21c3);
		
		v1.getChildren().addAll(l1, h11);
		v2.getChildren().addAll(l2, h21);
		
		h1.getChildren().addAll(v1, v2);

		return h1;	
	}
	

}

package application;

import java.util.Date;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;

import javafx.scene.control.*;

public class FenetreScores extends AnchorPane{
	
	private int score;
	private Date temps;
	private int touches;
	private int deplacements;
	
	public FenetreScores(int score, Date temps, int touches, int deplacements) {
		this.score = score;
		this.temps = temps;
		this.touches = touches;
		this.deplacements = deplacements;
	}
	
	public FenetreScores() {
		
		System.out.println("appel du constructeur de la fenetre des scores");
		Stage stageScores = new Stage();

		//stageScores.initStyle(StageStyle.UNDECORATED);
		Label bravo = new Label("BRAVO, Practice terminé !");
		Button fermer = new Button ("ok");
		this.getChildren().addAll(bravo, fermer);
		
		fermer.setOnAction(a -> stageScores.close());
		
		Scene SceneScores = new Scene(this, 200, 150);
		stageScores.sizeToScene();
		stageScores.show();
		
		//stageScores.setOnCloseRequest(a -> ); deplacer dans Main
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getTemps() {
		return temps;
	}

	public void setTemps(Date temps) {
		this.temps = temps;
	}

	public int getTouches() {
		return touches;
	}

	public void setTouches(int touches) {
		this.touches = touches;
	}

	public int getDeplacements() {
		return deplacements;
	}

	public void setDeplacements(int deplacements) {
		this.deplacements = deplacements;
	}
	
	
	
	

}

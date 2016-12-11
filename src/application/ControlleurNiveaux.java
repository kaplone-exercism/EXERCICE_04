package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import enums.Orientation;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Mur2D;
import models.Personnage2D;

public class ControlleurNiveaux implements Initializable{
	
	String home =  System.getProperty("user.home");
	File settings_file = new File(home, "shapesinthemazes_niveaux.txt");
	
	
	
	public AnchorPane init(AnchorPane root, Main_Exercice_04 main_Exercice_04) {

		final Scene scene = root.getScene();
		
        FileReader fr = null;
        root.getChildren().clear();
        ScrollPane sc = new ScrollPane();
        sc.setHbarPolicy(ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        sc.setPrefHeight(435);
        VBox vb = new VBox(); 
        vb.setPrefSize(580, 435);
        HBox hb = null;
        Label l = null;
        AnchorPane preview = null;
        AnchorPane fullGame = null;
        final Map<Node, AnchorPane> tableauDesNiveaux = new HashMap<>();
    	
		try {
			fr = new FileReader(settings_file);

	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String s = br.readLine();
			
	    	while(s != null){
	    		
	    		if (s.startsWith("#") || s.trim().equals("")){
	    		}
	    		
	    		else if(s.startsWith("[")){
	    			
	    			if (hb != null){
	    				
	    				hb.getChildren().add(preview);
	    				vb.getChildren().add(hb);
	    				tableauDesNiveaux.put(preview, fullGame);
	    				
	    				preview.setOnMouseClicked(a -> {
	    					
	    					Controlleur ct = new Controlleur();
	    					ct.init();
	    					final Personnage2D r0 = ct.getR0();

	    					AnchorPane root_ = tableauDesNiveaux.get(a.getSource());
	    					root_.getChildren().add(0, r0);

	    					scene.setRoot(root_);
		    				Stage stagePrincipal = (Stage) scene.getWindow();
		    				
		    				stagePrincipal.setWidth(1000);
		    				stagePrincipal.setHeight(600);
		    				
		    				root.setOnMouseClicked(e -> main_Exercice_04.gerer_clicks(r0, e));
		    				scene.setOnKeyPressed(e1 -> main_Exercice_04.gerer_keys(r0, e1, root_, stagePrincipal));
		    			});
	    				
	    			}
	    			
	    			hb = new HBox();
	    			hb.setPadding(new Insets(20));
	    			preview = new AnchorPane();
	    			preview.setPrefWidth(200);
	    			preview.setPrefHeight(120);
	    			preview.getChildren().add(new Rectangle(200,120, Color.WHITE));
	    			
	    			fullGame = new AnchorPane();
	    			fullGame.setPrefWidth(1000);
	    			fullGame.setPrefHeight(600);
	    			
	    			l = new Label(s.split("\\[")[1].split("\\]")[0] + " : ");
	    			hb.getChildren().add(l);
	    						
	    		}
	    		else {
	    			String ligne = s.split("=")[1];
	    			
	    			Orientation or = Orientation.valueOf(ligne.split(",")[0].trim());
	    			
	    			int epais = Integer.parseInt(ligne.split(",")[1].trim());
	    			int dist = Integer.parseInt(ligne.split(",")[2].trim());
	    			int debut = Integer.parseInt(ligne.split(",")[3].trim());
	    			int fin = Integer.parseInt(ligne.split(",")[4].trim());
	    			fullGame.getChildren().add(new Mur2D(or, epais, dist, debut, fin));
	    			
	    			epais = epais / 5;
	    			dist = dist / 5;
	    			debut = debut / 5;
	    			fin = fin / 5;
	    			
	    			preview.getChildren().add(new Mur2D(or, epais, dist, debut, fin));
	    		}
	    		s = br.readLine();
	    	}
	    	
	    	hb.getChildren().add(preview);
			vb.getChildren().add(hb);
			tableauDesNiveaux.put(preview, fullGame);
		
			preview.setOnMouseClicked(a -> {
				
				Controlleur ct = new Controlleur();
				ct.init();
				final Personnage2D r0 = ct.getR0();
				
				AnchorPane root_ = tableauDesNiveaux.get(a.getSource());
				
				root_.getChildren().add(0, r0);

				scene.setRoot(root_);
				Stage stagePrincipal = (Stage) scene.getWindow();
				
				stagePrincipal.setFullScreen(true);
				
				
				stagePrincipal.setWidth(1000);
				stagePrincipal.setHeight(600);
				
				root.setOnMouseClicked(e -> main_Exercice_04.gerer_clicks(r0, e));
				scene.setOnKeyPressed(e1 -> main_Exercice_04.gerer_keys(r0, e1, root_, stagePrincipal));
			});
			
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
		
		sc.setContent(vb);
		root.getChildren().add(sc);
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
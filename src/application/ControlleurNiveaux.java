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
import enums.Sens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Fleche;
import models.Mur2D;
import models.Personnage2D;

public class ControlleurNiveaux implements Initializable{
	
	String home =  System.getProperty("user.home");
	File settings_file = new File(home, "shapesinthemazes_niveaux.txt");
	
	Controlleur ct;
	
	private static Image im;
	private static ImageView imv;
	
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
        final Map<AnchorPane, AnchorPane> tableauDesNiveaux = new HashMap<>();
        final Map<AnchorPane, Controlleur> tableauDesPersos = new HashMap<>();
    	
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
	    				tableauDesPersos.put(preview, ct);
	    				
	    				ObservableList<Mur2D> listeMurs = listeDesMurs(fullGame.getChildrenUnmodifiable());
	    				
	    				preview.setOnMouseClicked(b -> {

	    					AnchorPane root_ = tableauDesNiveaux.get(b.getSource());
	    					
	    					Personnage2D r0 = tableauDesPersos.get(b.getSource()).getR0();
	    					
	    					Fleche fb = r0.getFleches().get(Sens.BAS);
	    					Fleche fh = r0.getFleches().get(Sens.HAUT);
	    					Fleche fd = r0.getFleches().get(Sens.DROITE);
	    					Fleche fg = r0.getFleches().get(Sens.GAUCHE);
	    					
	    					root_.getChildren().add(0, r0);
	    					root_.getChildren().addAll(fb, fh, fd, fg);
	    					
	    					r0.cacherLesFleches();

	    					scene.setRoot(root_);
		    				Stage stagePrincipal = (Stage) scene.getWindow();
		    				
		    				stagePrincipal.setWidth(1005);
		    				stagePrincipal.setHeight(635);

		    				root.setOnMouseClicked(e -> main_Exercice_04.gerer_clicks(r0, e));
		    				scene.setOnKeyPressed(e1 -> main_Exercice_04.gerer_keys(r0, e1, root_, stagePrincipal, listeMurs));
		    			});
	    				
	    			}
	    			
	    			
	    			ct = new Controlleur();

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
	    			String marqueur = s.split("=")[0].trim().toUpperCase();
	    			String ligne = s.split("=")[1];
	    			
	    			if ("PERSO".equals(marqueur)){
	    				Color couleur = Color.valueOf(ligne.split(",")[0].trim());
	    				int x = Integer.parseInt(ligne.split(",")[1].trim());
		    			int y = Integer.parseInt(ligne.split(",")[2].trim());
		    			int w = Integer.parseInt(ligne.split(",")[3].trim());
		    			int h = Integer.parseInt(ligne.split(",")[4].trim());

		    			ct.init(x, y, w, h, couleur);
	    			}
	    			else if ("MUR".equals(marqueur)){
	    				
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
	    			else if ("GOAL".equals(marqueur)){
	    				
	    				int x = Integer.parseInt(ligne.split(",")[0].trim());
		    			int y = Integer.parseInt(ligne.split(",")[1].trim());
		    			im = new Image("goal.png");
		    			imv = new ImageView(im);
		    			imv.setX(x);
		    			imv.setY(y);
		    			
	    				fullGame.getChildren().add(imv);
	    				Main_Exercice_04.setGoal2D(imv);
	    			}
	    			
                    else if ("INFOS".equals(marqueur)){
	    				
                    	String infos = ligne.split(";")[0].trim();
	    				int x = Integer.parseInt(ligne.split(";")[1].trim());
		    			int y = Integer.parseInt(ligne.split(";")[2].trim());
		    			int w = Integer.parseInt(ligne.split(";")[3].trim());
		    			
		    			Label text = new Label(infos);
		    			text.setWrapText(true);
		    			text.setLayoutX(x);
		    			text.setLayoutY(y);
		    			text.setPrefWidth(w);
		    			text.setOpacity(0.50);
		    			
	    				fullGame.getChildren().add(text);
	    			}
	    		}
	    		
	    		s = br.readLine();
	    	}
	    	
	    	hb.getChildren().add(preview);
			vb.getChildren().add(hb);
			tableauDesNiveaux.put(preview, fullGame);
			tableauDesPersos.put(preview, ct);
			
			ObservableList<Mur2D> listeMurs = listeDesMurs(fullGame.getChildrenUnmodifiable());
			
			preview.setOnMouseClicked(a -> {
		        
			    Controlleur ct = tableauDesPersos.get(a.getSource());
				final Personnage2D r0 = ct.getR0();
				
				Fleche fb = r0.getFleches().get(Sens.BAS);
				Fleche fh = r0.getFleches().get(Sens.HAUT);
				Fleche fd = r0.getFleches().get(Sens.DROITE);
				Fleche fg = r0.getFleches().get(Sens.GAUCHE);
				
				AnchorPane root_ = tableauDesNiveaux.get(a.getSource());
				
				root_.getChildren().add(0, r0);
				root_.getChildren().addAll(fb, fh, fd, fg);
				
				r0.cacherLesFleches();

				scene.setRoot(root_);
				Stage stagePrincipal = (Stage) scene.getWindow();
				
				//stagePrincipal.setFullScreen(true);

				stagePrincipal.setWidth(1005);
				stagePrincipal.setHeight(635);
				
				root.setOnMouseClicked(e -> main_Exercice_04.gerer_clicks(r0, e));
				scene.setOnKeyPressed(e1 -> main_Exercice_04.gerer_keys(r0, e1, root_, stagePrincipal, listeMurs));				
			});
			
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
		
		sc.setContent(vb);
		root.getChildren().add(sc);
		return root;
	}
	
	public ObservableList<Mur2D> listeDesMurs(ObservableList<Node> listeDesNoeuds){
		
		ObservableList<Mur2D> retour = FXCollections.observableArrayList();
		
		for (Node n : listeDesNoeuds){
			if (n instanceof Mur2D){
				retour.add((Mur2D)n);
			}
		}
		return retour;
	}

	public static void setImage(String im) {
		imv.setImage(new Image(im));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
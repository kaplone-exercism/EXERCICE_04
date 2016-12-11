package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import enums.Orientation;
import enums.Sens;
//import application.Contact;
//import application.Controlleur;
//import application.JfxUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Mur2D;
import models.Personnage2D;
import models.Settings;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Main_Exercice_04 extends Application implements Initializable{
		
	int bonus = 0;
	Rectangle  p1;

	@FXML
	ImageView maze;
	@FXML
	ImageView settings;
	@FXML
	ImageView launch;
	@FXML
	ImageView exit;
	
	@FXML
	AnchorPane root;

	Scene scene;
	
	Stage stagePrincipal;
	
	Thread t_launch;
	Thread tm_launch;
	Thread ts_launch;
	Thread te_launch;
	
	Double deltaChange = 0.5;
	
	@Override
	public void start(Stage primaryStage) {
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		
		try {
			
			scene = new Scene((Parent) JfxUtils.loadFxml("menu2.fxml"), 600, 400);

			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(435);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Rectangle gerer_keys(Personnage2D r, KeyEvent e, AnchorPane root, Stage importedstage){
		
		Double surface_r = r.computeAreaInScreen();
		double newSize;
		double ecart;
			
		KeyCode kc = e.getCode();
		
		//System.out.println(kc.getName());
		
		switch (kc) {
		case UP:   if(e.isShortcutDown()){
			          if (e.isShiftDown() &&
			        	  r.getWidth() > 20){
			        	  	r.deformationVertical(0.95);
			          }
			          else if (r.getHeight() > 20){
			        	  r.deformationHorizontal(0.95);
			          }
		            }
		            else {
		            	  r.deplacement(0, -5);
		            }
			break;
		case DOWN: if(e.isShortcutDown()){
	          		  if (e.isShiftDown() &&
	          			  r.getWidth() > 20){
	          			  	r.deformationVertical(0.95);
		              }
		              else if (r.getHeight() > 20){
		            	  r.deformationHorizontal(0.95);
		              }
	                }
	                else {
	            	  r.deplacement(0, 5);
	                }
		    break;
		case LEFT: if(e.isShortcutDown()){
	          if (e.isShiftDown() &&
		        	  r.getWidth() > 20){
		        	  r.deformationVertical(0.95);
		          }
		          else if (r.getHeight() > 20){
		        	  r.deformationHorizontal(0.95);
		          }
	            }
	            else {
	            	  r.deplacement(-5, 0);
	            }
		    break;
		case RIGHT:  if(e.isShortcutDown()){
	          if (e.isShiftDown() &&
		        	  r.getWidth() > 20){
		        	  r.deformationVertical(0.95);
		          }
		          else if (r.getHeight() > 20){
		        	  r.deformationHorizontal(0.95);
		          }
	            }
	            else {
	            	  r.deplacement(5, 0);
	            }
		    break;
		case Z: r.setY(r.getY() - Contact.rienNeBloque(r, Sens.HAUT, root));
		        r.setFill(Color.PINK);
		break;
		case S: r.setY(r.getY() + Contact.rienNeBloque(r, Sens.BAS, root));
		        r.setFill(Color.BLUE);
		break;
		case Q: r.setX(r.getX() - Contact.rienNeBloque(r, Sens.GAUCHE, root));
		        r.setFill(Color.ORANGE);
		break;
		case D: r.setX(r.getX() + Contact.rienNeBloque(r, Sens.DROITE, root));
		        r.setFill(Color.GREENYELLOW);
		break;
		case NUMPAD9 : r.setFill(Color.BLACK);
        break;
		case NUMPAD8 : r.setFill(Color.CHOCOLATE);
        break;
		case NUMPAD7 : r.setFill(Color.RED);
        break;
		case NUMPAD6 : r.setFill(Color.BLUE);
        break;
		case NUMPAD5 : r.setFill(Color.MAROON);
        break;
		case NUMPAD4 : r.setFill(Color.GREEN);
        break;
		case NUMPAD3 : r.setFill(Color.CORNFLOWERBLUE);
        break;
		case NUMPAD2 : r.setFill(Color.BLUEVIOLET);
        break;
		case NUMPAD1 : r.setFill(Color.YELLOW);
        break;
		case ADD: bonus += 5;
		break;
		case SUBTRACT: bonus -= 5;
		break;
		case ESCAPE: start(importedstage);
		break;
		}
		
		return r;		
	}
	
    public Rectangle gerer_clicks(Rectangle r, MouseEvent e){
		
		r.setX(e.getSceneX());
		r.setY(e.getSceneY());
		
		return r;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void retourMenu(){
		start(stagePrincipal);
	}
	
	public void nouvelleFenetre(){
		
		scene = root.getScene();
		stagePrincipal = (Stage) scene.getWindow();

		ControlleurNiveaux ctn = new ControlleurNiveaux();
		root = ctn.init(root, this);
	
	}
	
    public void nouvelleFenetreSettings(){
		
    	scene = root.getScene();
    	stagePrincipal = (Stage) scene.getWindow();
    	
		ControlleurSettings cts = new ControlleurSettings();
		scene = cts.init(root, this);
		
		stagePrincipal.setScene(scene);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		maze.setOnMouseEntered(a -> {
			tm_launch = new Thread(m_launch);
			tm_launch.start();
		});
		maze.setOnMouseExited(a -> {
			tm_launch.stop();
			maze.setImage(new Image("maze_v2_2_nb.png"));
		});
		
		launch.setOnMouseEntered(a -> {
			t_launch = new Thread(r_launch);
			t_launch.start();
		});
		launch.setOnMouseExited(a -> {
			t_launch.stop();
			launch.setImage(new Image("launch_nb.png"));
		});
		
		exit.setOnMouseEntered(a -> {
			te_launch = new Thread(e_launch);
			te_launch.start();
		});
		exit.setOnMouseExited(a -> {
			te_launch.stop();
			exit.setImage(new Image("exit.png"));
		});
		
		settings.setOnMouseEntered(a -> {
			ts_launch = new Thread(s_launch);
			ts_launch.start();
		});
		settings.setOnMouseExited(a -> {
			ts_launch.stop();
			settings.setImage(new Image("settings2_nb.png"));
		});
		
		settings.setOnMouseClicked(a -> nouvelleFenetreSettings());
		exit.setOnMouseClicked(a -> System.exit(0));
		launch.setOnMouseClicked(a -> nouvelleFenetre());	
	}
	
	Runnable r_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					launch.setImage(new Image("launch_2.png"));	
					Thread.sleep(100);
					launch.setImage(new Image("launch.png"));	
					Thread.sleep(50);
					
				} catch (InterruptedException e) {
					launch.setImage(new Image("launch_nb.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable m_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					maze.setImage(new Image("maze_v2_color1.png"));	
					Thread.sleep(80);
					maze.setImage(new Image("maze_v2_color2.png"));	
					Thread.sleep(50);
					maze.setImage(new Image("maze_v2_color3.png"));	
					Thread.sleep(70);
					
				} catch (InterruptedException e) {
					maze.setImage(new Image("maze_v2.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable s_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					settings.setImage(new Image("settings2_r2.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r3.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r4.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r5.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r6.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r7.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r8.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r9.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r10.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r11.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r12.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r13.png"));	
					Thread.sleep(80);
					
				} catch (InterruptedException e) {
					settings.setImage(new Image("settings2_nb.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable e_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					exit.setImage(new Image("exit_0_01.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_06.png"));	
					Thread.sleep(300);
					exit.setImage(new Image("exit_0_05_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01.png"));	
					Thread.sleep(220);
					
				} catch (InterruptedException e) {
					exit.setImage(new Image("exit.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
}

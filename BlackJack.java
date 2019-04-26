import java.util.HashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BlackJack extends Application{
	Dealer myDealer;
	Player myPlayer;

	public static void main(String[] args) {
		/*Deck myDeck = new Deck();
		myDeck.createDeck();
		myDeck.printCards();
		System.out.println("Shuffle...");
		myDeck.shuffle();
		myDeck.printCards();
		Dealer myDealer = new Dealer();
		Player myPlayer = new Player();
		myDealer.startGame();
		myPlayer.setPlayerHand(myDealer.dealCards());
		//System.out.println(myPlayer.valueCal());
		
		
		System.out.println("Player Deck: ");
		myPlayer.getPlayerHand().printCards();
		System.out.println(myPlayer.valueCal());
		
		//Card card = myDealer.dealACard();
		myPlayer.getPlayerHand().addCard(myDealer.dealACard(myPlayer.valueCal()));
		System.out.println(myPlayer.valueCal());*/
		launch(args);
	}

	//start scene
	Stage myStage;
	Scene scene;
	Button start,exit;
	HashMap<String, Scene> sceneMap;
	
	//network scene
	Scene networking;
	Button startGame;
	
	//game scene
	Scene game;
	Label waiting = new Label("Waiting for the other players...");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Welcome to Black Jack");
		sceneMap = new HashMap<String, Scene>();
		myStage = primaryStage;
		
	//start scene
		start = new Button("Start Game");
		exit = new Button("Exit Game");
		start.setLayoutX(20);
		start.setLayoutY(200);
		exit.setLayoutX(500);
		exit.setLayoutY(200);
		
		
		//buttons
		start.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				myStage.setScene(sceneMap.get("network"));
			}
		});
		
		//image
		Image pic = new Image("blackJack.jpeg");
		
		ImageView b = new ImageView(pic);
		b.setFitHeight(600);
		b.setFitWidth(600);
		b.setPreserveRatio(true);
		b.setImage(pic);
		
		//set group
		Group root = new Group();
		root.getChildren().addAll(b);
		root.getChildren().add(start);
		root.getChildren().add(exit);
		scene = new Scene(root,600,300);
		
	//network scene
		startGame = new Button("Start");
		startGame.setLayoutX(300);
		startGame.setLayoutY(550);
		
		startGame.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				myDealer = new Dealer();
				myDealer.startGame();
				myStage.setScene(sceneMap.get("game"));
			}
		});
		
		//set group
		Group network = new Group();
		networking = new Scene(network,400,600);
		network.getChildren().add(startGame);
		
		
		
		
		
	//game scene
		//image
		Image pic2 = new Image("game.jpg");

		ImageView b2 = new ImageView(pic2);
		b2.setFitHeight(600);
		b2.setFitWidth(600);
		b2.setPreserveRatio(true);
		b2.setImage(pic2);
		
		waiting.setTextFill(Color.WHITE);
		waiting.setFont(Font.font("Cambria", 20));
		waiting.setLayoutX(140);
		waiting.setLayoutY(180);
		
		//set group
		Group gamePlay = new Group();
		game = new Scene(gamePlay,600,400);
		gamePlay.getChildren().addAll(b2);
		gamePlay.getChildren().add(waiting);
		
		
		
		
		//map
		sceneMap.put("w", scene);
		sceneMap.put("network", networking);
		sceneMap.put("game", game);
		
		primaryStage.setScene(sceneMap.get("w"));
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}






















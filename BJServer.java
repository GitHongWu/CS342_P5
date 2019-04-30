import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BJServer extends Application{
	Dealer myDealer;
	Player myPlayer;
	private boolean isServer = true;
	
	private NetworkConnection  conn; 
	private TextArea messages = new TextArea();

	public static void main(String[] args) {
		
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
	Button btn;
	
	//game scene
	Scene game;
	Label waiting = new Label("Waiting for the other players...");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("BlackJack_Server");
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
		
		exit.setOnAction(e->{
			primaryStage.close();
			
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
		//startGame = new Button("Start");
		btn = new Button("connect");
		btn.setLayoutX(300);
		btn.setLayoutY(550);
		messages = new TextArea();
		Text textport = new Text("Port:");
		TextField text = new TextField();
		
		
		VBox portbox = new VBox(10, textport,text, btn,messages);
		
		
		btn.setOnAction(event->{
			//System.out.println(text.getText());
			int i = Integer.parseInt(text.getText());
			conn = createServer(i);
			try {
				conn.startConn();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("created:" + i);
			btn.setDisable(true);
			//myDealer = new Dealer();
			//myDealer.startGame();
			//myStage.setScene(sceneMap.get("game"));
			
			
			
		});
		
		
		exit = new Button("Exit");
		exit.setLayoutX(350);
		exit.setLayoutY(380);
		
		exit.setOnAction(e->{
			primaryStage.close();
			
		});
		
		
		
		/*
		startGame.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				myDealer = new Dealer();
				myDealer.startGame();
				myStage.setScene(sceneMap.get("game"));
			}
		});
		*/
		
		//set group
		BorderPane network = new BorderPane();
		//Group network = new Group();
		network.setCenter(portbox);
		networking = new Scene(network,500,500);
		//network.getChildren().add(portbox);
		
		
		
		
		
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
	
	
	
	
	private Server createServer(int i) {
		return new Server(i, data-> {
			Platform.runLater(()->{
				messages.appendText(data.toString() + "\n");
			});
		});
	}

}







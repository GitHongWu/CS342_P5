import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BJClient extends Application{
	Dealer myDealer;
	Player myPlayer;

	private boolean isServer = false;
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
		startGame = new Button("Start");
		startGame.setLayoutX(300);
		startGame.setLayoutY(550);
		
		
		messages.setPrefHeight(200);
		//TextField input = new TextField();
		
		Text textport = new Text("Port:");
		TextField portinput = new TextField();
		Text textip = new Text("IP: ");
		TextField ipinput = new TextField();
		Button btn = new Button("Connet");
		
		
		btn.setOnAction(event->{
			System.out.println("conneting..");
			System.out.println("port: "+ portinput.getText());
			System.out.println("ip: "+ ipinput.getText());
			int porti = Integer.parseInt(portinput.getText());
			
			String address = ipinput.getText();
			portinput.clear();
			ipinput.clear();
			conn = createClient(address,porti);
			messages.appendText("connected to " + porti + "\n");
			try {
				conn.startConn();
				//System.out.print("start");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//messages.appendText("connected to "+ porti);
			btn.setDisable(true);
				
		});
		
		
		exit = new Button("Exit");
		exit.setLayoutX(350);
		exit.setLayoutY(380);
		
		exit.setOnAction(e->{
			primaryStage.close();
			
		});
		
		
		VBox box = new VBox(20, messages,textport,portinput,textip,ipinput,btn);
		//set group
		Group network = new Group();
		networking = new Scene(network,400,500);
		network.getChildren().addAll(box,exit);
		
		
		
		
		
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
	
	
	private Client createClient(String ip, int i) {
		return new Client(ip, i, data -> {
			Platform.runLater(()->{
				messages.appendText(data.toString() + "\n");
			});
		});
	}

}






















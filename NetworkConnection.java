
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class NetworkConnection {
	
	private ConnThread connthread = new ConnThread();
	private Consumer<Serializable> callback;
	boolean clientOne, clientTwo;
	String dataOne, dataTwo;
	ArrayList<ClientThread> ct;
	//the list to store the players who wants to play
	ArrayList<ClientThread> playable_list;
	private int score1,score2;
	private Dealer mydealer = new Dealer();
	
	public NetworkConnection(Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
		ct = new ArrayList<ClientThread>();
		clientOne = false;
		clientTwo = false;
		
	}
	
	public void startConn() throws Exception{
		connthread.start();
		
	}
	
	public void send(Serializable data) throws Exception{
		if(isServer() && clientOne && clientTwo) {
			String message = compare();
			
			ct.forEach(t->{
				try {
					t.tout.writeObject(message);
					clientOne = false;
					clientTwo = false;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			});
		}
		else {
			connthread.out.writeObject(data);
		}
		
	}
	public String compare() {
		String message = "ghj";
		if(dataOne.equals("rock")) {
			if(dataTwo.equals("rock")) {
				message = "rock and rock, draw!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("paper")) {
				this.score2++;
				message = "paper beat rock, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("spock")) {
				this.score2++;
				message = "spock beat rock, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("scissors")) {
				this.score1++;
				message = "rock beat scissors, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("lizard")) {
				this.score1++;
				message = "rock beat lizard, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
		}
		if(dataOne.equals("paper")) {
			if(dataTwo.equals("paper")) {
				message = "paper and paper, draw!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("rock")) {
				this.score1++;
				message = "paper beat rock, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("scissors")) {
				this.score2++;
				message = "scissors beat paper, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("spock")) {
				this.score1++;
				message = "paper beat spock, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("lizard")) {
				this.score2++;
				message = "lizard beat paper, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			
		}
		if(dataOne.equals("scissors")) {
			if(dataTwo.equals("scissors")) {
				message = "scissors and scissors, draw!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("paper")) {
				this.score1++;
				message = "scissors beat paper, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("rock")) {
				this.score2++;
				message = "rock beat scissors, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("lizard")) {
				this.score1++;
				message = "scissors beat lizard, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("spock")) {
				this.score2++;
				message = "spock beat scissors, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			
		}
		if(dataOne.equals("spock")) {
			if(dataTwo.equals("spock")) {
				message = "spock and spock, draw!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("paper")) {
				this.score2++;
				message = "paper beat spock, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("rock")) {
				this.score1++;
				message = "spock beat rock, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("lizard")) {
				this.score2++;
				message = "lizard beat spock, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("scissors")) {
				this.score1++;
				message = "spock beat scissors, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			
		}
		if(dataOne.equals("lizard")) {
			if(dataTwo.equals("lizard")) {
				message = "lizard and lizard, draw!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("paper")) {
				this.score1++;
				message = "lizard beat paper, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("rock")) {
				this.score2++;
				message = "rock beat lizard, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("spock")) {
				this.score1++;
				message = "lizard beat spock, player1 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			if(dataTwo.equals("scissors")) {
				this.score2++;
				message = "scissors beat lizard, player2 win!" + "player1: " + this.score1 + " player2:"+this.score2;
			}
			
		}
		
		
		if(this.score1 == 3) {
			message += " player1 win the game!";
		}
		if(this.score2 == 3) {
			message += " player2 win the game!";
		}
		
		return message;
	}
	
	public void closeConn() throws Exception{
		connthread.socket.close();
	}
	public void closess() throws Exception{
		connthread.closess();
	}
	
	
	abstract protected boolean isServer();
	abstract protected String getIP();
	abstract protected int getPort();
	
	class ConnThread extends Thread{
		private Socket socket;
		private ObjectOutputStream out;
		private ServerSocket server;
		
		
		
		public void closess() {
			try {
				this.server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void run() {
			int number =1;
			if(isServer()) {
				try {
					server = new ServerSocket(getPort());
					while(true) {
						ClientThread t1 = new ClientThread(server.accept(),number);
						ct.add(t1);
						t1.start();
						callback.accept("player" + number + " join");
						number++;
						Thread.sleep(1000);
						if (ct.size() >= 4) {
							for(int i = 0; i < ct.size(); i++ ) {
								try {
									ct.get(i).tout.writeObject("There Are " + ct.size() + " Players Online, Do You Want To Start?\n");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						if (playable_list.size() >= 4) {
							gameStart();
						}
						
					}
				} 
				catch (Exception e) {
				}
				
			}
			else {
				try {
					Socket socket = new Socket(getIP(), getPort());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					
					this.socket = socket;
					this.out = out;
					socket.setTcpNoDelay(true);
					
					while(true) {
						Serializable data = (Serializable) in.readObject();
						System.out.print(data);
						callback.accept(data);
					}
				} 
				catch (Exception e) {
					
				}
			}
		}





		
	}
	
	class ClientThread extends Thread{
		Socket s;
		int number;
		ObjectOutputStream tout;
		ObjectInputStream tin;
		Player myplayer;
		
		ClientThread(Socket socket, int num){
			this.s = socket;
			this.number = num;
			myplayer = new Player();
		}
		public void run() {
			try(
				ObjectOutputStream out = new ObjectOutputStream(this.s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(this.s.getInputStream())){
				s.setTcpNoDelay(true);
				this.tout = out;
				this.tin = in;
				//System.out.print("here");
				tout.writeObject("welcome player: "+ number);
				/*
				if(number ==1) {
					tout.writeObject("waiting for another player come in");
				}
				else {
					tout.writeObject("game start, pick you choich");
				}
				
				*/
				while(true) {
					Serializable data = (Serializable) in.readObject();
					
					callback.accept(data);
				}
				
			}
			catch(Exception e) {
				callback.accept("connection Closed");
			}
		}
		
	}
	
	public void gameStart() {
		mydealer.startGame();
		String card;
		for(int i = 0; i < 4; i++ ) {
			try {
				card= mydealer.dealACard().toString();
				playable_list.get(i).tout.writeObject(card);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}	



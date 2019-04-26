
public class Card {
	private Suit suit;
	private Value value;
	
	//getter for value
	public Value getValue() {
		return this.value;
	}
	
	//getter for suit
	public Suit getSuit(){
		return this.suit;
	}
	//constructor
	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}
	
}

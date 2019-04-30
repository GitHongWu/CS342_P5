import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards;
	private int deckSize;
	
	//getter for cards
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	//getter for deckSize
	public int getDeckSize() {
		return this.deckSize;
	}
	
	//setters for deckSize
	public void setDeckSize(int size){
		this.deckSize = size;
	}
	
	//constructor
	public Deck(){
		this.cards = new ArrayList<Card>();
		this.deckSize = 0;
	}
	
	//create a deck of cards(not shuffled)
	public void createDeck() {
		//Generate cards
		for(Suit cardSuit : Suit.values())
		{
			for(Value cardValue : Value.values())
			{
				//add a new card
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
		this.deckSize = 52;
	}
	
	//print all the card in the deck
	public void printCards() {
		for(Card aCard : this.cards)
		{
			System.out.println( aCard.getSuit() + " " + aCard.getValue());
		}
		System.out.println("Deck size: " + this.deckSize);
	}
	
	//find a card in the deck by using the position
	public Card getNthCard(int n)
	{
		return this.cards.get(n);
	}
	
	//add a card in the deck
	public void addCard(Card newCard)
	{
		this.cards.add(newCard);
		this.deckSize ++;
	}
	
	//delete a card in the deck by using the position
	public Card deleteNthCard(int n)
	{
		Card c = this.cards.get(n);
		this.cards.remove(n);
		this.deckSize --;
		return c;
	}
	
	//shuffle deck
	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		int randomNum = 0;
		int counter = 0;
		int size = this.deckSize;
		int tmpSize = this.deckSize;
		for(counter = 0; counter < size; counter++)
		{
			//Generate a random number can be use as index 
			 randomNum = (int)(Math.random()* tmpSize);
			 //System.out.println(randomNum);
			 //add to tmpDeck
			 tmpDeck.add(this.cards.get(randomNum));
			 //delete from cards deck
			 this.cards.remove(randomNum);
			 tmpSize--;
		}
		this.cards = tmpDeck;
		size--;
	}
}


























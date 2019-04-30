
public class Dealer extends Deck{
	
	private Deck dealerDeck;
	
	//getter for dealerDeck
	public Deck getDealerDeck()
	{
		return dealerDeck;
	}
	//get a deck of cards and shuffle
	public void startGame() {
		dealerDeck = new Deck();
		dealerDeck.createDeck();
		dealerDeck.shuffle();
		dealerDeck.printCards();
	}
	
	public Deck dealCards() {
		System.out.println("Deal cards...");
		int counter = 0;
		Deck playerD = new Deck();
		while(counter < 2) {
			playerD.addCard(dealerDeck.getNthCard(0));
			dealerDeck.deleteNthCard(0);
			counter++;
		}
		System.out.println("Dealer Deck size: "+ dealerDeck.getDeckSize());
		System.out.println("Player Deck size: "+ playerD.getDeckSize());
		return playerD;
	}
	
	public Card dealACard() {
		/*
		if(playerHandValue < 21)
		{
			Card card = dealerDeck.getNthCard(0);
			dealerDeck.deleteNthCard(0);
			return card;
		}else
		{
			System.out.println("Your hand Value is equal or greatter than 21!!!");
			return null;
		}
		*/
		Card card = dealerDeck.getNthCard(0);
		dealerDeck.deleteNthCard(0);
		return card;
	}
	
	public void compare() {
		
	}

}

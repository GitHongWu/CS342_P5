
public class Player extends Dealer {
	private Deck playerHand; 
	private int handValue = 0;
	
	public void setPlayerHand(Deck hand) {
		playerHand = new Deck();
		playerHand = hand;
	}
	
	public Deck getPlayerHand() {
		return this.playerHand;
	}
	
	public void getHand() {
		setPlayerHand(dealCards());
	}
	public int valueCal() {
		int counter = playerHand.getDeckSize();
		System.out.println("size: " + counter);
		this.handValue = 0;
		while(counter != 0)
		{
			if(playerHand.getNthCard(counter-1) != null)
			{
				Value value = playerHand.getNthCard(counter-1).getValue();
				if(value == Value.TWO) {
					this.handValue = this.handValue + 2;
				}
				else if(value == Value.THREE){
					this.handValue = this.handValue + 3;
				}
				else if(value == Value.FOUR) {
					this.handValue = this.handValue + 4;
				}
				else if(value == Value.FIVE) {
					this.handValue = this.handValue + 5;
				}
				else if(value == Value.SIX) {
					this.handValue = this.handValue + 6;
				}
				else if(value == Value.SEVEN) {
					this.handValue = this.handValue + 7;
				}
				else if(value == Value.EIGHT) {
					this.handValue = this.handValue + 8;
				}
				else if(value == Value.NINE) {
					this.handValue = this.handValue + 9;
				}
				else if(value == Value.TEN) {
					this.handValue = this.handValue + 10;
				}
				else if(value == Value.JACK) {
					this.handValue = this.handValue + 10;
				}
				else if(value == Value.QUEEN) {
					this.handValue = this.handValue + 10;
				}
				else if(value == Value.KING) {
					this.handValue = this.handValue + 10;
				}
				else if(value == Value.ACE) {
					if(this.handValue <= 11) {
						this.handValue = this.handValue + 10;
					}else
					{
						this.handValue = this.handValue + 1;
					}
				}
			}
			counter--;
		}
		return handValue;
	}


}






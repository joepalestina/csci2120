public class Game {

	public static void main(String[] args){
		
		//initialize user interface
		TUI pokemonTUI = new TUI();
		
		//create pokemon
		Pokemon player1 = new Pokemon("Player 1");
		Pokemon player2 = new Pokemon("Player 2");
		
		//initiate rounds of battle until battle is complete
		Battle battle = new Battle();
		while(!battle.isComplete()) {
			battle.initiateNextRound(player1, player2, pokemonTUI);
		}

		//when battle is over, display the result
		pokemonTUI.displayBattleResults(battle.winner());
	}


}
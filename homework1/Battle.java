public class Battle {

/* Instance variables */
	private boolean isComplete;
	private String winner;
	private boolean playerOneIsFirst;

/* Query methods */
	public boolean isComplete() {
		return isComplete;
	}

	public String winner() {
		return winner;
	}

/* Constructor */
	public Battle() {
		isComplete = false;
		winner = "";
	}

/* Main logic of a battle */
	public void initiateNextRound(Pokemon player1, Pokemon player2, TUI pokemonTUI) {
		//display current tolerances (HP)
		pokemonTUI.displayBattleStats(player1, player2);
		
		//determine turn order
		this.determineTurnOrder(player1, player2);
		
		//prompt players for choices in turn order
		if(playerOneIsFirst) {
			pokemonTUI.displayPlayerMenu(player1);
			pokemonTUI.getPlayerAction(player1);
			pokemonTUI.displayPlayerMenu(player2);
			pokemonTUI.getPlayerAction(player2);			
		} else {
			pokemonTUI.displayPlayerMenu(player2);
			pokemonTUI.getPlayerAction(player2);
			pokemonTUI.displayPlayerMenu(player1);
			pokemonTUI.getPlayerAction(player1);
		}
		pokemonTUI.printDivider();
		
		//evaluate first action
		if(playerOneIsFirst) {
			resolvePlayerAction(player1, player2, pokemonTUI);
		} else {
			resolvePlayerAction(player2, player1, pokemonTUI);
		}

		//check whether a Pokemon has died and end battle if so
		this.checkForPokemonDeath(player1, player2);
		if(isComplete) {
			return;
		}

		//evaluate second action (opposite order of first)
		if(playerOneIsFirst) {
			resolvePlayerAction(player2, player1, pokemonTUI);
		} else {
			resolvePlayerAction(player1, player2, pokemonTUI);
		}
		
		//check whether a Pokemon has died
		this.checkForPokemonDeath(player1, player2);
	}

/* Logic for determining the order of play for a turn in battle */
	public void determineTurnOrder(Pokemon player1, Pokemon player2) {
		int percentage = Chance.randomPercent();
		if(player1.speed() > player2.speed()) {
		//if player 1 is faster than player 2, go first 80% of the time
			if(percentage<81) {
				playerOneIsFirst = true;
			} else {
				playerOneIsFirst = false;
			}
		} else {
		//if player 2 is faster than player 1, go first 80% of the time
			if(percentage<81) {
				playerOneIsFirst = false;
			} else {
				playerOneIsFirst = true;
			}
		}
	}

/* Logic to execute the chosen action of a player and display the result of the action */
	private void resolvePlayerAction(Pokemon activePlayer, Pokemon passivePlayer, TUI pokemonTUI) {
		String actionResult = "";
		if(activePlayer.isDefending()) {
				//execute defense and store result
				actionResult = activePlayer.defend();
				//display result
				pokemonTUI.displayActionResult(actionResult);
			} else {
				//execute attack from active player on passive player and store result
				actionResult = activePlayer.attack(passivePlayer);
				//display result
				pokemonTUI.displayActionResult(actionResult);
			}
	}

/* Logic to check for the death of either pokemon */
/* This should be run after each action in case both pokemon would die on the same turn */
	public void checkForPokemonDeath(Pokemon player1, Pokemon player2) {
		if(player1.tolerance() < 1) {
			isComplete = true;
			winner = player2.name();
		}
		if(player2.tolerance() < 1) {
			isComplete = true;
			winner = player1.name();
		}
	}

}
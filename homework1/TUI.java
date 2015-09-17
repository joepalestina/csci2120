/* TUI class (text user interface) */
import java.util.Scanner;
import java.io.*;

public class TUI {

/* Instance variables */
	private Scanner sc;

/* Constructor */
	public TUI() {
		sc = new Scanner(System.in);
	}

/* Basic print operations */
	private void printLine(String outputText) {
		System.out.println(outputText);
	}

	public static void printDivider() {
		System.out.println("------------------------------------------");
	}

	public static void printSpace() {
		System.out.println("");
	}

/* Displays */
	public void displayBattleResults(Battle lastRound, Pokemon pokemon1, Pokemon pokemon2) {
		printLine("");
	}

	public void displayBattleStats(Pokemon player1, Pokemon player2) {
	// Displays HP (tolerance) of each pokemon
		this.printDivider();
		printLine("Pokemon1 HP: " + player1.tolerance() + "   |   Pokemon2 HP: " + player2.tolerance());
		this.printDivider();
	}

	public void displayPlayerMenu(Pokemon currentPokemon) {
	//Displays menu of options for the current player
		printLine(currentPokemon.name() + ", please select your next move!");
		printLine("1. Attack");
		printLine("2. Defend");
	}

	public void displayActionResult(String result) {
	//Displays the result of a player's action
		printLine(result);
		printSpace();
	}

	public void displayBattleResults(String winner) {
	//Displays the final results of a battle
		printSpace();
		printLine(winner + " wins the battle. Congratulations!");
		printSpace();
	}

/* Inputs */
	public void getPlayerAction(Pokemon player) {
	//Takes the player's input and updates the pokemon's status to reflect the choice
		String input = "";
		boolean validInput = false;
		while(!validInput) {
			input = sc.next();
			if(input.equals("1")) {
				player.chooseAttack();
				validInput = true;
			} else if(input.equals("2")) {
				player.chooseDefend();
				validInput = true;
			} else {
				printLine("Incorrect input! Please try again.");
				printLine(player.name() + ", please press 1 or 2 and press enter.");
			}
		}
	}





}
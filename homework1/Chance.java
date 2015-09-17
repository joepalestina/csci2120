import java.util.Random;

// Used to generate numbers used for chance

public class Chance {
	public static int randomPercent() {
	//Random integer between 0 and 100 used for calculations based on percentage chance
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(101);
	}

	public static int randomTolerance() {
	//Random integer between 0 and 100 used for the initial tolerance of a pokemon
		return randomPercent();
	}
	
	public static int randomSpeed() {
	//Random speed: 1 <= int <= 100 used for the initial speed of a pokemon
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100) + 1;
	}

	public static int randomPower() {
	//Random power: 1 <= int <= 20 used for the initial power of a pokemon
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(20) + 1;
	}

}
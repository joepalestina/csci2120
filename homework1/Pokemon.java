public class Pokemon {

 /* Instance variables */
  private int tolerance; /* @invariant: tolerance >= 0 && tolerance <= 100 */
  private int speed;    /* @invariant: speed >= 1 && speed <= 100 */
  private int power;     /* @invariant: power >= 1 && power <= 20 */
  private String name;
  private boolean isDefending; // if true, Pokemon is defending, if false, Pokemon is attacking

 /* Debug constructor */
  public Pokemon(String name, int initialTolerance, int initialSpeed, int initialPower) {
    name = name;
    tolerance = initialTolerance;
    speed = initialSpeed;
    power = initialPower;
  }

/* Constructor with random parameter assignment */
  public Pokemon(String playerName) {
    name = playerName;
    tolerance = Chance.randomTolerance();
    speed = Chance.randomSpeed();
    power = Chance.randomPower();
  }
  
/* Query methods */
  public int tolerance() {
    return tolerance;
  }

  public int speed() {
    return speed;
  }

  public int power() {
    return power;
  }
  
  public String name() {
    return name;
  }

  public boolean isDefending() {
    return isDefending;
  }

  /* Input choices */
    public void chooseDefend() {
    isDefending = true;
  }

  public void chooseAttack() {
    isDefending = false;
  }


/* Commands */
  public String attack(Pokemon anotherAnimal) {
  //Resolves the attacking action
  //Returns a string with the result of the attack
    isDefending = false;
    String result = "";
    int reducedAttackPower = power / 4;

    if(anotherAnimal.isDefending) {
      //generate percentage integer between 0 and 100
      int percentage = Chance.randomPercent();
      if(percentage < 25) {
        //25% chance pokemon hits other pokemon
        anotherAnimal.getHit(reducedAttackPower);
        result = this.name + " barely hits " + anotherAnimal.name() + " for " + reducedAttackPower + " damage!";
      }
      else if(percentage < 50) {
        //25% chance pokemon hits itself
        this.getHit(reducedAttackPower);
        result = this.name + " misses and hits itself for " + reducedAttackPower + " damage!";

      } else {
        //50% chance nothing happens
        result = this.name + " attacks, but " + anotherAnimal.name() + " defends successfully!";
      }
    }
    else {
      //If the opponent is attacking,
      //attack has percent chance of hitting equal to its speed
      int percentage = Chance.randomPercent();
      if(percentage <= speed) {
        anotherAnimal.getHit(power);
        result = this.name + " hits " + anotherAnimal.name() + " for " + power + " damage!";
      } else {
        result = this.name + " attacks but just misses!";
      }
    }
    return result;
  }

  public String defend() {
    //Resolves the defending action (nothing)
    //Returns a string informing of defense
    String result = this.name + " is defending!";
    return result; 
  }

  private void getHit(int amountOfHit) {
    //Calculates the damage done by an attack
    tolerance = tolerance - amountOfHit;
  }



}

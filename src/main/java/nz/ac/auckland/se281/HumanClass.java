package nz.ac.auckland.se281;

public class HumanClass extends Players {

  private String name;

  // HumanClass constructor with argument name
  public HumanClass(String name) {
    this.name = name;
  }

  // Getters and setters for name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // If human wins, it will print the message with their name and rounds played
  public void won(String roundString) {
    MessageCli.END_GAME.printMessage(name, roundString);
  }

  // Will display the needed points for human to win
  public void neededPoints(int neededPoints, int score) {
    String scoreString = Integer.toString(score);
    String requiredPoints = Integer.toString(neededPoints - score);
    MessageCli.PRINT_PLAYER_WINS.printMessage(name, scoreString, requiredPoints);
  }
}

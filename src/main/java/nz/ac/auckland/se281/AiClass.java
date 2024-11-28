package nz.ac.auckland.se281;

import java.util.ArrayList;

public class AiClass extends Players {

  private String name;
  private ArrayList<Integer> fingerList = new ArrayList<>();
  private Strategy strategy;

  // AiClass constructor with arguments name, fingerList and strategy
  public AiClass(String name, ArrayList<Integer> fingerList, Strategy strategy) {
    this.name = name;
    this.fingerList = fingerList;
    this.strategy = strategy;
  }

  // Getter and setter for the private String name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // Getter and setter for the private ArrayList fingerList
  public ArrayList<Integer> getFingerList() {
    return fingerList;
  }

  public void setFingerList(ArrayList<Integer> fingerList) {
    this.fingerList = fingerList;
  }

  // Getter and setter for the private Strategy strategy
  public Strategy getStrategy() {
    return strategy;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  // Gets the sum of the fingers that the AI will play
  public int aiSum(int jarvisFinger, int roundCount) {
    // Will use random strategy for the first 3 rounds
    if (roundCount <= 3) {
      this.strategy = new RandomStrategy();
      return strategy.sum(jarvisFinger, roundCount);
    } else {
      // After three rounds, the strategy will be set to match the difficulty chosen by the player
      if (this.strategy instanceof RandomStrategy) {
        return strategy.sum(jarvisFinger, roundCount);
      } else if (this.strategy instanceof AverageStrategy) {
        return (strategy.sum(humanTotal(), roundCount)) + jarvisFinger;
      } else if (this.strategy instanceof TopStrategy) {
        return strategy.sum(jarvisFinger, mostCommon());
      }
    }
    return 0;
  }

  // Gets the most repeating value in the fingerList
  public int mostCommon() {
    int common = 0;
    int mostRepeated = 0;
    // Loops through fingerList arraylist and checks if values repeat multiple times
    for (int i = 0; i < this.fingerList.size(); i++) {
      int repeated = 0;
      for (int j = 0; j < this.fingerList.size(); j++) {
        if (this.fingerList.get(i) == this.fingerList.get(j)) {
          repeated++;
        }
      }
      // If this value repeats more than the previous one, it will replace it
      if (repeated > mostRepeated) {
        mostRepeated = repeated;
        common = this.fingerList.get(i);
        return common;
      }
    }
    // If nothing happens, it will return 0;
    return 0;
  }

  // Randomises the number of fingers the AI selects
  public int aiRandFinger() {
    return strategy.finger();
  }

  // Gets the total value of human fingers of past rounds
  public int humanTotal() {
    int total = 0;
    for (int i = 0; i < this.fingerList.size(); i++) {
      total = total + this.fingerList.get(i);
    }
    return total;
  }

  // Prints the AI won statement
  public void won(String roundString) {
    MessageCli.END_GAME.printMessage(name, roundString);
  }

  // Prints the points required to win by AI.
  public void neededPoints(int neededPoints, int score) {
    String scoreString = Integer.toString(score);
    String requiredPoints = Integer.toString(neededPoints - score);
    MessageCli.PRINT_PLAYER_WINS.printMessage(name, scoreString, requiredPoints);
  }
}

package nz.ac.auckland.se281;

public class DifficultyFactory {

  public static Strategy setStrategy(String difficultyType, int roundCount) {
    // Switches the difficulty type and returns the corresponding strategy
    switch (difficultyType) {
      case "easy":
        return new RandomStrategy();
      case "medium":
        return new AverageStrategy();
      case "hard":
        return new TopStrategy();
      case "master":
        //   Master case alternates between two strategies
        if (roundCount % 2 == 0) {
          return new AverageStrategy();
        } else {
          return new TopStrategy();
        }
        // The default strategy is randomStrategy
      default:
        return new RandomStrategy();
    }
  }
}

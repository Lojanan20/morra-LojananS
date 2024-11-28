package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {
  // AI finger is randomly generated
  @Override
  public int finger() {
    return Utils.getRandomNumber(1, 5);
  }

  // The random strategy gets a random number greater than the AI's finger
  @Override
  public int sum(int finger, int roundCount) {
    return Utils.getRandomNumber(finger + 1, finger + 5);
  }
}

package nz.ac.auckland.se281;

public class TopStrategy implements Strategy {
  // AI finger is randomly generated
  @Override
  public int finger() {
    return Utils.getRandomNumber(1, 5);
  }

  // The top strategy gets the most commonly played value by the human and adds it to the AI's
  // finger for the round
  @Override
  public int sum(int finger, int mostCommon) {
    int topSum = finger + mostCommon;
    return topSum;
  }
}

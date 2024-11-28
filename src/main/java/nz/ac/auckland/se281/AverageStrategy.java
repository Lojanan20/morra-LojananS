package nz.ac.auckland.se281;

public class AverageStrategy implements Strategy {
  // AI finger is randomly generated
  @Override
  public int finger() {
    return Utils.getRandomNumber(1, 5);
  }

  // The average strategy gets the average of humans fingers for previous rounds and divides it by
  // the rounds played
  @Override
  public int sum(int humanTotal, int roundCount) {
    double averageFinger = (double) (humanTotal) / (double) (roundCount - 1);
    int averageFingerInt = (int) Math.round(averageFinger);
    return averageFingerInt;
  }
}

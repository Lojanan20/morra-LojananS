package nz.ac.auckland.se281;

public abstract class Players {
  // Abstract class used to store the methods that will be used by both the Human and AI classes
  public abstract void won(String roundString);

  public abstract void neededPoints(int neededPoints, int score);
}

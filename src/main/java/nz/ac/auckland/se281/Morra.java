package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.Difficulty;

/* Morra.java lets users play the traditional game of Morra against an AI.
The AI has 4 difficulties; easy, medium, hard and master. User is able to select
the difficulty and also the number of points needed to win. AI implements different
strategies based on the selected difficulty to ensure the person has a fun time playing.
The game ends when either the AI or the player reaches the needed points to win.

Author: Lojanan Sivanantharuban  */

public class Morra {

  // Counts the number of rounds. Default is 0
  private int roundCount = 0;
  // Gets the player's name when new game is started
  private String humanName = "";
  // Difficulty string is initially empty. Will be set upon new game
  String setDiff = "";
  // Gets the strategy to use
  private Strategy strategy;
  // Boolean to check if game has started
  Boolean gameStarted = false;
  // Sets both player score and ai score to 0. Needed points is also set to 0
  int humanScore = 0, aiScore = 0, neededPoints = 0;
  // ArrayList to store the fingers that human has played for the game
  ArrayList<Integer> fingerList = new ArrayList<>();

  public Morra() {}

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    // Sets player name as the first option from user input then prints the welcome message.
    humanName = options[0];
    MessageCli.WELCOME_PLAYER.printMessage(humanName);
    // Converts the difficulty option to string and lowercase
    setDiff = difficulty.toString().toLowerCase();
    strategy = DifficultyFactory.setStrategy(setDiff, roundCount);
    // Sets the gameStarted boolean to true and gets the amount of points needed to win
    gameStarted = true;
    neededPoints = pointsToWin;
    // Sets roundCount to 0 as it is a new game as well as both the players scores to 0
    roundCount = 0;
    humanScore = 0;
    aiScore = 0;
  }

  public void play() {
    // If the gameStart option is zero, then error message will be printed and player will be
    // returned to main menu
    if (gameStarted == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Creates instance of human and AI class. Sets the strategy that's being played for the round
    HumanClass human = new HumanClass(humanName);
    AiClass jarvis = new AiClass("Jarvis", fingerList, strategy);
    strategy = DifficultyFactory.setStrategy(setDiff, roundCount);

    // Counts the number of rounds played by player, then prints the message of round start
    roundCount++;
    MessageCli.START_ROUND.printMessage(Integer.toString(roundCount));
    MessageCli.ASK_INPUT.printMessage();

    // Takes the user input and splits where there is a space
    String input = Utils.scanner.nextLine();
    String fingerString = input.split(" ")[0];
    String sumString = input.split(" ")[1];

    // As the following if statement is integer based, need to convert user input for finger and sum
    // into integer format
    int humanFinger = Integer.valueOf(fingerString);
    int humanSum = Integer.valueOf(sumString);

    // The random strategy for Jarvis. Using the getRandomNumber function, a random number for
    // finger and sum will be generated
    int jarvisFinger = jarvis.aiRandFinger();
    int jarvisSum = jarvis.aiSum(jarvisFinger, roundCount);

    // This if statement checks if the finger is 1-5 inclusive and sum is 1-10 inclusive
    if ((humanFinger < 1 || humanFinger > 5) || (humanSum < 1 || humanSum > 10)) {
      // Prints the invalid input message and uses recursion to call play function again
      MessageCli.INVALID_INPUT.printMessage();
      play();
    } else {
      // If the input is valid, then it will print the info of hand and store the value into the
      // array list for use in Average strategy
      MessageCli.PRINT_INFO_HAND.printMessage(human.getName(), fingerString, sumString);
      fingerList.add(humanFinger);
      MessageCli.PRINT_INFO_HAND.printMessage(
          jarvis.getName(), Integer.toString(jarvisFinger), Integer.toString(jarvisSum));
      // The correct sum of the player and ai fingers
      int actualSum = humanFinger + jarvisFinger;

      // If the player guessed the sum correct, human wins. If ai guessed sum correct, ai wins. Any
      // other possibility is a draw
      if ((humanSum == actualSum) && (jarvisSum == actualSum)) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
      } else if (humanSum == actualSum) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
        humanScore++;
      } else if (jarvisSum == actualSum) {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
        aiScore++;
      } else {
        MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
      }
    }

    // Converts the round count into string format
    String roundString = Integer.toString(roundCount);

    // If player or AI reaches neededPoints, the game will end and winner's name printed
    if (humanScore == neededPoints) {
      human.won(roundString);
      gameStarted = false;
    } else if (aiScore == neededPoints) {
      jarvis.won(roundString);
      gameStarted = false;
    }
  }

  public void showStats() {
    // Won't show stats if game hasn't started
    if (gameStarted == false) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    // Creates a new instance of Human and AI class
    AiClass jarvis = new AiClass("Jarvis", fingerList, strategy);
    HumanClass human = new HumanClass(humanName);
    // Displays the points needed for both human and AI to win
    human.neededPoints(neededPoints, humanScore);
    jarvis.neededPoints(neededPoints, aiScore);
  }
}

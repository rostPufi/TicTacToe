package tictactoe;

public class Model {
  private static int stepCount;
  private static int tableSize;
  private static String winnerXseries, winnerOseries; 

  public static int getTableSize() {
    return tableSize;
  }

  public static void setTableSize(int tableSize) {
    if (tableSize>=3 && tableSize<=5) {
      Model.tableSize = tableSize;
      setWinnerSeries(tableSize);
    }
    else 
      throw new IllegalArgumentException("Argument tableSize is out of bounds (3-5).");
  }

  public static int getStepCount() {
    return stepCount;
  }
  
  public static void nextStep() {
    stepCount++;
  }
  
  public static void resetGame() {
    stepCount = 0;
  }
  
  public static String getGamerName() {
    return Constants.GAMER[stepCount % 2];
  }
  
  private static void setWinnerSeries(int length) {
    winnerXseries="";
    winnerOseries="";
    for (int i=1; i<=getTableSize(); i++) {
      winnerXseries += "X";
      winnerOseries += "O";
    }    
  }
  
  public static String getWinnerXseries() {
    return winnerXseries;
  }
  
  public static String getWinnerOseries() {
    return winnerOseries;
  }
}

import java.util.Scanner;

public class AsciiShop {

  /**
   * reads an ASCII image line by line and prints its width and height,
   * if all lines are of equal length.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int lines = 1;
    int width = sc.nextLine().length();
    while (sc.hasNextLine()) {
      checkIfTrue(width == sc.nextLine().length());
      lines++;
    }
    System.out.println(width + " " + lines);
  }

  /**
   * prints INPUT MISMATCH and exits
   */
  static public void inputMismatch() {
    System.out.println("INPUT MISMATCH");
    System.exit(0);
  }

  /**
   * checks if condition is true, otherwise inputMismatch() is called
   */
  static public void checkIfTrue(boolean condition) {
    if (!condition) {
      inputMismatch();
    }
  }
}

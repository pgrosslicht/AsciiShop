import java.util.Scanner;
import java.util.Arrays;

public class AsciiShop {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] image = null;
    int width;
    int lines;
    String current_line;
    checkIfTrue("read".equals(sc.next()));
    lines = sc.nextInt();
    sc.nextLine();
    image = new String[lines];
    image[0] = sc.nextLine();
    width = image[0].length();
    for(int i = 1; i < lines; i++) {
      checkIfTrue(sc.hasNextLine());
      current_line = sc.nextLine();
      checkIfTrue(!current_line.startsWith("fill"));
      checkIfTrue(width == current_line.length());
      image[i] = current_line;
    }

    while(sc.hasNextLine()) {
      //check for fill commands
      checkIfTrue("fill".equals(sc.next()));

      //check if they follow syntax
      checkIfTrue(sc.hasNextInt());
      int x = sc.nextInt();
      checkIfTrue(sc.hasNextInt());
      int y = sc.nextInt();
      checkIfTrue(sc.hasNext());
      char c = sc.next().charAt(0);
      if(x >= 0 && x < width && y >= 0 && y < lines) {
        fill(image, x, y, c);
      } else {
        System.out.println("OPERATION FAILED");
        return;
      }
      sc.nextLine();
    }

    for (int i = 0; i < image.length; i++) {
      System.out.println(image[i]);
    }
    System.out.print(width + " " + lines);
  }

  /**
   * Floodfill algorithm
   * checks if neighbouring characters are the same as the replaced, if so, repeats the process for this one as well.
   * @param image Array of strings, represents an ASCII image
   * @param x x-coordinate
   * @param y y-coordinate
   * @param c the character that's going to replace the current character at the position (x|y)
   */
  public static void fill(String[] image, int x, int y, char c) {
    int lines = image.length;
    int width = image[0].length();
    char old_char = image[y].charAt(x);
    image[y] = image[y].substring(0,x) + c + image[y].substring(x+1);

    if ((y - 1) >= 0 && image[y-1].charAt(x) == old_char) {
      fill(image, x, y - 1, c);
    }
    if ((y + 1) <= lines && image[y+1].charAt(x) == old_char) {
      fill(image, x, y + 1, c);
    }
    if ((x + 1) < width  && image[y].charAt(x+1) == old_char) {
      fill(image, x + 1, y, c);
    }
    if ((x - 1) >= 0 && image[y].charAt(x-1) == old_char) {
      fill(image, x - 1, y , c);
    }
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


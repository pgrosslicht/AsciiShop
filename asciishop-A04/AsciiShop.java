import java.util.Scanner;
import java.util.Arrays;

public class AsciiShop {
    static boolean ausgabe = true;

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int lines;
      String current_line;
      checkIfTrue("read".equals(sc.next()));
      lines = sc.nextInt();
      sc.nextLine();
      AsciiImage image = new AsciiImage();
      image.addLine(sc.nextLine());
      for(int i = 1; i < lines; i++) {
        checkIfTrue(sc.hasNextLine());
        checkIfTrue(image.addLine(sc.nextLine()));
      }

      while(sc.hasNextLine()) {
        //save command for later use
        String command = sc.next();
        //look for commands and execute them
        if ("uniqueChars".equals(command)) {
          System.out.println(image.getUniqueChars());
        } else if ("symmetric-h".equals(command)) {
          System.out.println(image.isSymmetricH());
        } else if ("flip-v".equals(command)) {
          image.flipV();
        } else if ("transpose".equals(command)) {
          image.transpose();
        } else if ("fill".equals(command)) {
          //check if fill command follows syntax
          checkIfTrue(sc.hasNextInt());
          int x = sc.nextInt();
          checkIfTrue(sc.hasNextInt());
          int y = sc.nextInt();
          checkIfTrue(sc.hasNext());
          char c = sc.next().charAt(0);
          if(x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            image.fill(x, y, c);
          } else {
            System.out.println("OPERATION FAILED");
            return;
          }
        } else { //if command is not recognized, input mismatch
          inputMismatch();
        }
        //advance to next line
        sc.nextLine();
      }

      System.out.print(image);
      System.out.println(image.getWidth() + " " + image.getHeight());
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


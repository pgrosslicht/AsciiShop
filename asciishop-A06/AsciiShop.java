import java.util.Scanner;
import java.util.Arrays;

public class AsciiShop {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      AsciiStack stack = new AsciiStack(3);
      checkIfTrue("create".equals(sc.next()));
      checkIfTrue(sc.hasNextInt());
      int width = sc.nextInt();
      checkIfTrue(sc.hasNextInt());
      int height = sc.nextInt();
      checkIfTrue(width > 0);
      checkIfTrue(height > 0);
      AsciiImage image = new AsciiImage(width, height);

      while(sc.hasNextLine()) {
        //save command for later use
        String command = sc.next();
        //look for commands and execute them
        if ("load".equals(command)) {
          stack.push(new AsciiImage(image));
          String eof = sc.next();
          sc.nextLine();
          int lines = 0;
          while(sc.hasNextLine()) {
            String line = sc.next();
            if (line.equals(eof)) {
              break;
            }
            sc.nextLine();
            checkIfTrue(line.length() == image.getWidth());
            for (int i = 0; i < line.length(); i++) {
              image.setPixel(i, lines, line.charAt(i));
            }
            lines++;
          }
          checkIfTrue(lines == image.getHeight());
        } else if ("print".equals(command)) {
          System.out.println(image);
        } else if ("clear".equals(command)) {
          stack.push(new AsciiImage(image));
          image.clear();
        } else if ("centroid".equals(command)) {
          char c = sc.next().charAt(0);
          System.out.println(image.getCentroid(c));
        } else if ("grow".equals(command)) {
          stack.push(new AsciiImage(image));
          char c = sc.next().charAt(0);
          image.growRegion(c);
        } else if ("straighten".equals(command)) {
          stack.push(new AsciiImage(image));
          char c = sc.next().charAt(0);
          image.straightenRegion(c);
        } else if ("transpose".equals(command)) {
          stack.push(new AsciiImage(image));
          image.transpose();
        } else if ("replace".equals(command)) {
          stack.push(new AsciiImage(image));
          checkIfTrue(sc.hasNext());
          char oldc = sc.next().charAt(0);
          checkIfTrue(sc.hasNext());
          char newc = sc.next().charAt(0);
          image.replace(oldc, newc);
        } else if ("line".equals(command)) {
          stack.push(new AsciiImage(image));
          //check if line command follows syntax
          checkIfTrue(sc.hasNextInt());
          int x0 = sc.nextInt();
          checkIfTrue(sc.hasNextInt());
          int y0 = sc.nextInt();
          checkIfTrue(sc.hasNextInt());
          int x1 = sc.nextInt();
          checkIfTrue(sc.hasNextInt());
          int y1 = sc.nextInt();
          checkIfTrue(sc.hasNext());
          char c = sc.next().charAt(0);
          image.drawLine(x0, y0, x1, y1, c);
        } else if ("fill".equals(command)) {
          stack.push(new AsciiImage(image));
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
        } else if("undo".equals(command)) {
            if(stack.size() >= 1) {
              image = stack.pop();
              System.out.println("STACK USAGE " + stack.size() + "/" + stack.capacity());
            }
            else {
              System.out.println("STACK EMPTY");
            }
        } else { //if command is not recognized, input mismatch
          System.out.println("UNKNOWN COMMAND");
          return;
        }
        //advance to next line
        sc.nextLine();
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


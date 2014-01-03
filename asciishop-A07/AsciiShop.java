import java.util.Scanner;
import java.util.Arrays;

public class AsciiShop {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      AsciiStack stack = new AsciiStack();
      checkIfTrue("create".equals(sc.next()));
      checkIfTrue(sc.hasNextInt());
      int width = sc.nextInt();
      checkIfTrue(sc.hasNextInt());
      int height = sc.nextInt();
      checkIfTrue(sc.hasNext());
      String charset = sc.next();
      AsciiImage image = new AsciiImage(width, height, charset);

      while(sc.hasNextLine()) {
        try {
          //save command for later use
          String command = sc.next();
          //look for commands and execute them
          if ("load".equals(command)) {
            stack.push(new AsciiImage(image));
            String data = "";
            checkIfTrue(sc.hasNext());
            String eof = sc.next();
            boolean hasEof = false;
            sc.nextLine();
            while(sc.hasNextLine()) {
              String line = sc.next();
              if (line.equals(eof)) {
                hasEof = true;
                break;
              }
              data += line + "\n";
              sc.nextLine();
            }
            checkIfTrue(hasEof);
            image = (new LoadOperation(data)).execute(image);
          } else if ("print".equals(command)) {
            System.out.println(image);
          } else if ("filter".equals(command)) {
            stack.push(new AsciiImage(image));
            checkIfTrue("median".equals(sc.next()));
            image = (new MedianOperation()).execute(image);
          } else if ("clear".equals(command)) {
            stack.push(new AsciiImage(image));
            image = (new ClearOperation()).execute(image);
          } else if ("replace".equals(command)) {
            stack.push(new AsciiImage(image));
            checkIfTrue(sc.hasNext());
            char oldc = sc.next().charAt(0);
            checkIfTrue(sc.hasNext());
            char newc = sc.next().charAt(0);
            image = (new ReplaceOperation(oldc, newc)).execute(image);
          }  else if("undo".equals(command)) {
              if(stack.size() >= 1) {
                image = stack.pop();
              } else {
                System.out.println("STACK EMPTY");
              }
          } else {
            System.out.println("UNKNOWN COMMAND");
            return;
          }
          //advance to next line
          sc.nextLine();
        } catch (OperationException e) { operationFailed(); }
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
   * prints OPERATION FAILED and exits
   */
  static public void operationFailed() {
    System.out.println("OPERATION FAILED");
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


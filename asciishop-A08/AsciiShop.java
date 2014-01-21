import java.util.Scanner;
import java.util.HashMap;

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

      HashMap<String, Factory> commands = new HashMap<String, Factory>();
      commands.put("load", new LoadFactory());
      commands.put("filter", new FilterFactory());
      commands.put("replace", new ReplaceFactory());
      commands.put("clear", new ClearFactory());
      commands.put("binary", new BinaryFactory());

      while(sc.hasNextLine()) {
        String command = sc.next();
        if ("print".equals(command)) {
          System.out.println(image);
        } else if ("undo".equals(command)) {
            if(stack.size() >= 1) {
              image = stack.pop();
            } else {
              System.out.println("STACK EMPTY");
            }
        } else if ("histogram".equals(command)) {
            //Output histogram
        } else {
          try {
            stack.push(new AsciiImage(image));
            image = commands.get(command).create(sc).execute(image);
          } catch (OperationException e) { printAndExit("OPERATION FAILED"); }
            catch (NullPointerException e) { printAndExit("UNKNOWN COMMAND"); }
            catch (FactoryException e) { printAndExit("INPUT MISMATCH"); }
        }
        sc.nextLine();
      }

    }

  /**
   * prints and exits
   */
  static public void printAndExit(String message) {
    System.out.println(message);
    System.exit(0);
  }

  /**
   * checks if condition is true, otherwise print INPUT MISMATCH and exit
   */
  static public void checkIfTrue(boolean condition) {
    if (!condition) {
      printAndExit("INPUT MISMATCH");
    }
  }
}


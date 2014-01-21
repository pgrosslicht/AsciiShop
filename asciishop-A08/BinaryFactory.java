import java.util.Scanner;

public class BinaryFactory implements Factory {

  public BinaryFactory() {
  }

  public Operation create(Scanner scanner) throws FactoryException {

    char[] params = new char[1];

    for (int i = 0; i < params.length; i++) {
      if (!scanner.hasNext()) {
        throw new FactoryException("Insufficient parameter");
      }
      String s = scanner.next();
      if (s.length() > 1) {
        throw new FactoryException("Insufficient parameter");
      }
      params[i] = s.charAt(0);
    }

    return new BinaryOperation(params[0]);

  }

}

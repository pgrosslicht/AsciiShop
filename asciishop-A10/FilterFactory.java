import java.util.Scanner;

public class FilterFactory implements Factory {

  public FilterFactory() {
  }

  public Operation create(Scanner scanner) throws FactoryException {

    if (!scanner.hasNext()) {
      throw new FactoryException("Insufficient parameter");
    }
    String s = scanner.next();
    if (s.equals("median")) {
      return new MedianOperation();
    } else if (s.equals("average")) {
      return new AverageOperation();
    } else {
      throw new FactoryException("Wrong parameter");
    }
  }
}

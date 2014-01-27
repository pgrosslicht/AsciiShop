import java.util.Scanner;

public class CreateFactory implements Factory {

  /**
   * Default constructor creates a new CreateFactory.
   */
  public CreateFactory() {
  }

  public Operation create(Scanner scanner) throws FactoryException {
    if (!scanner.hasNextInt()) {
      throw new FactoryException("Insufficient parameter");
    }
    int width = scanner.nextInt();
    if (!scanner.hasNextInt()) {
      throw new FactoryException("Insufficient parameter");
    }
    int height = scanner.nextInt();
    if (!scanner.hasNext()) {
      throw new FactoryException("Insufficient parameter");
    }
    String charset = scanner.next();
    return new CreateOperation(width, height, charset);
  }
}

import java.util.Scanner;

public class LoadFactory implements Factory {

  public LoadFactory() {
  }

  public Operation create(Scanner scanner) throws FactoryException {

    String data = "";

    if (!scanner.hasNext()) {
      throw new FactoryException("No EOF is given");
    }
    String eof = scanner.next();
    boolean hasEof = false;
    scanner.nextLine();
    while(scanner.hasNextLine()) {
      String line = scanner.next();
      if (line.equals(eof)) {
        hasEof = true;
        break;
      }
      data += line + "\n";
      scanner.nextLine();
    }
    if (!hasEof) {
      throw new FactoryException("EOF is missing");
    }
    return new LoadOperation(data);

  }

}

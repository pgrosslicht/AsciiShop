import java.util.Scanner;

public class SaveFactory implements Factory {
  private MetricSet<AsciiImage> saved;

  /**
   * Default constructor creates a new SaveFactory.
   */
  public SaveFactory(MetricSet<AsciiImage> saved) {
    this.saved = saved;
  }

  public Operation create(Scanner scanner) throws FactoryException {
    return new SaveOperation(saved);
  }
}


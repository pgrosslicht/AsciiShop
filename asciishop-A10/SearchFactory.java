import java.util.Scanner;

public class SearchFactory implements Factory {
  private MetricSet<AsciiImage> saved;

  /**
   * Default constructor creates a new SearchFactory.
   */
  public SearchFactory(MetricSet<AsciiImage> saved) {
    this.saved = saved;
  }

  public Operation create(Scanner scanner) throws FactoryException {
    if (!scanner.hasNext()) {
      throw new FactoryException("Insufficient parameter");
    }
    String metric = scanner.next();
    Metric<AsciiImage> m = null;
    if (metric.equals("pixelcount")) {
      m = new PixelCountMetric();
    } else if (metric.equals("uniquechars")) {
      m = new UniqueCharsMetric();
    } else {
      throw new FactoryException("Wrong parameter");
    }
    return new SearchOperation(saved, m);
  }
}


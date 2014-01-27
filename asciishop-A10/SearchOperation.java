public class SearchOperation implements Operation {

  private MetricSet<AsciiImage> saved;
  private Metric<AsciiImage> m;

  public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
    this.saved = saved;
    this.m = m;
  }

  public AsciiImage execute(AsciiImage img) throws OperationException {
    if (saved.isEmpty()) {
      throw new OperationException("saved is empty");
    }
    return saved.search(img, m).iterator().next();
  }

}

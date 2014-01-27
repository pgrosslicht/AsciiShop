import java.util.Arrays;

public class MedianOperation extends FilterOperation {
  public MedianOperation() {
  }

  @Override
  public int filter(int[] values) {
    Arrays.sort(values);
    return values[values.length / 2];
  }
}

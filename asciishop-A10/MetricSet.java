import java.util.LinkedHashSet;
import java.util.Collection;
public class MetricSet<E> extends LinkedHashSet<E> {

  public MetricSet() {}

  public MetricSet(Collection<? extends E> c) {
    this.addAll(c);
  }

  public MetricSet<E> search(E e, Metric<? super E> m) {
    int lowestDist = Integer.MAX_VALUE;
    MetricSet<E> result = new MetricSet<E>();
    for (E o: this) {
      int dist = m.distance(e, o);
      if (dist < lowestDist) {
        result.clear();
        lowestDist = dist;
      }
      if (dist == lowestDist) {
        result.add(o);
      }
    }
    return result;
  }
}

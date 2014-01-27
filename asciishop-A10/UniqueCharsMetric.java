public class UniqueCharsMetric implements Metric<AsciiImage> {
  /** 
   * liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild
   * @param i1 one of the images to compare
   * @param i2 picture to compare with
   * @return Differenz der Anzahl unterschiedlicher Zeichen
   */
  public int distance(AsciiImage i1, AsciiImage i2) {
    return Math.abs(i1.getUniqueChars() - i2.getUniqueChars());
  }
}

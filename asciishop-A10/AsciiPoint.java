public class AsciiPoint {
  private int x;
  private int y;

  public AsciiPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public String toString() {
    return "("+getY()+","+getX()+")";
  }
}

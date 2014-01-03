public class AsciiStack {
  private class AsciiStackNode {
    private AsciiStackNode next = null;
    private AsciiImage image = null;
    public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
      this.image = image;
      this.next = next;
    }

    public int size() {
      if (next != null) {
        return next.size() + 1;
      } else {
        return 1;
      }
    }
  }

  private AsciiStackNode head = null;

  public AsciiStack() {
  }

  public boolean empty() {
    return this.head == null;
  }

  public AsciiImage pop() {
    if(empty()) {
      return null;
    }
    AsciiImage img = this.head.image;
    this.head = this.head.next;
    return img;
  }

  public AsciiImage peek() {
    return this.head.image;
  }

  public void push(AsciiImage img) {
    this.head = new AsciiStackNode(img, this.head);
  }

  public int size() {
    return this.empty() ? 0 : this.head.size();
  }

}

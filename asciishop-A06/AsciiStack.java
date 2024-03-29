import java.util.Arrays;

public class AsciiStack {
  private int increment = 0;
  private int pTopElement = -1;
  private AsciiImage[] stack;

  public AsciiStack(int increment) {
    this.stack = new AsciiImage[increment];
    this.increment = increment;
  }

  public int capacity() {
    return stack.length;
  }

  public boolean empty() {
    return pTopElement == -1;
  }

  public AsciiImage pop() {
    if (pTopElement >= 0) {
      AsciiImage top = stack[pTopElement--];
      stack[pTopElement + 1] = null;
      if (capacity() - pTopElement-1 > increment && capacity() > increment) {
        resize(capacity() - increment);
      }
      return top;
    } else {
      return null;
    }
  }

  public AsciiImage peek() {
    if(pTopElement >= 0) {
      return stack[pTopElement];
    } else {
      return null;
    }
  }

  public void push(AsciiImage img) {
    if(pTopElement + 1 >= stack.length) {
      resize(stack.length + increment);
    }
    stack[++pTopElement] = img;
  }

  public int size() {
    return this.pTopElement+1;
  }

  private void resize(int targetSize) {
    AsciiImage[] newStack = new AsciiImage[targetSize];
    int stackEnd = targetSize;
    if(targetSize > stack.length) {
      stackEnd = stack.length;
    }
    for(int i = 0; i < stackEnd; i++) {
      newStack[i] = stack[i];
    }
    this.stack = newStack;
  }
}

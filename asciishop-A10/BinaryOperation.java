import java.util.ArrayList;

public class BinaryOperation implements Operation {

  private char threshold;

  /**
   * Creates a new ReplaceOperation that will replace all oldChars by newChar.
   * 
   * @param threshold
   *            The char that is the threshold
   */
  public BinaryOperation(char threshold) {
    this.threshold = threshold;
  }

  /**
   * Executes this BinaryOperation and returns as new AsciiImage where all characters
   * below the treshold become the darkest character, the others become the brightest characters.
   * 
   * @param img
   *            The AsciiImage to use as basis for executing the Operation, it will remain
   *            unchanged
   * @return A new AsciiImage reflecting the result of the executed Operation
   * @throws OperationException
   */
  public AsciiImage execute(AsciiImage img) throws OperationException {
    String charset = img.getCharset();
    if (charset.indexOf(threshold) < 0) {
      throw new OperationException("Invalid char");
    }
    char darkestChar = charset.charAt(0);
    char brightestChar = charset.charAt(charset.length() - 1);
    int indexOfThreshold = charset.indexOf(threshold);

    AsciiImage result = new AsciiImage(img);

    for(int i = 0; i < result.getHeight(); i++) {
      for(int j = 0; j < result.getWidth(); j++) {
        if (charset.indexOf(result.getPixel(i, j)) < indexOfThreshold) {
        result.setPixel(i, j, darkestChar);
        } else {
        result.setPixel(i, j, brightestChar);
        }
      }
    }

    return result;

  }

}

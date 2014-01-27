import java.util.Arrays;
public abstract class FilterOperation implements Operation {
  public FilterOperation() {}

  public AsciiImage execute(AsciiImage img) {
    int[] brightnessValues = new int[9];
    int nextIndex = 0;
    String charset = img.getCharset();
    AsciiImage result = new AsciiImage(img);
    for(int i = 0; i < result.getHeight(); i++) {
      for(int j = 0; j < result.getWidth(); j++) {

        for(int a = i-1; a <= i+1; a++) {
          for(int b = j-1; b <= j+1; b++) {
            if(a >= 0 && a < result.getHeight() && b >= 0 && b < result.getWidth()) {
              brightnessValues[nextIndex%9] = charset.indexOf(img.getPixel(a, b));
            } else {
              brightnessValues[nextIndex%9] = charset.length() - 1;
            }
            nextIndex++;
          }
        }
        result.setPixel(i, j, charset.charAt(filter(brightnessValues)));
      }
    }
    return result;
  }

  public abstract int filter(int[] values);
}

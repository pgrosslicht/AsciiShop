import java.util.ArrayList;
import java.util.Collections;

public class MedianOperation implements Operation {
  public MedianOperation() {
  }


  public AsciiImage execute(AsciiImage image) throws OperationException {
    String charset = image.getCharset();

    AsciiImage result = new AsciiImage(image);
    char brightestChar = charset.charAt(charset.length() - 1);

    for(int i = 0; i < result.getHeight(); i++) {
      for(int j = 0; j < result.getWidth(); j++) {
        ArrayList<Integer> pixels = new ArrayList<Integer>();

        for(int a = i-1; a <= i+1; a++) {
          for(int b = j-1; b <= j+1; b++) {
            if(a >= 0 && a < image.getHeight() && b >= 0 && b < image.getWidth()) {
              pixels.add(charset.indexOf(image.getPixel(a, b)));
            } else {
              pixels.add(charset.length() - 1);
            }
          }
        }

        Collections.sort(pixels);
        result.setPixel(i, j, charset.charAt(pixels.get(4)));
      }
    }
    return result;
  }
}

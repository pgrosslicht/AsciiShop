import java.util.ArrayList;

public class LoadOperation implements Operation {

  private String data;

  public LoadOperation(String data) {
    this.data = data;
  }

  public AsciiImage execute(AsciiImage img) throws OperationException {
    AsciiImage result = new AsciiImage(img);

    int lines = 0;
    for(String line : data.split("\n")) {
      if(line.length() != result.getWidth()) {
        throw new OperationException();
      }

      for(int i = 0; i < line.length(); i++) {
        char pixel = line.charAt(i);
        if(result.getCharset().indexOf(pixel) < 0) {
          throw new OperationException();
        }
        result.setPixel(lines, i, pixel);
      }

      lines++;
    }

    if(lines != result.getHeight()) {
      throw new OperationException();
    }

    return result;
  }

}

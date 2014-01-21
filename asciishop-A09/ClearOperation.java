public class ClearOperation implements Operation {
  public ClearOperation() {}

  public AsciiImage execute(AsciiImage img) {

    char brightestChar = img.getCharset().charAt(img.getCharset().length()-1);
    AsciiImage result = new AsciiImage(img);

     for(int i = 0; i < result.getHeight(); i++) {
      for(int j = 0; j < result.getWidth(); j++) {
        result.setPixel(i, j, brightestChar);
      }
    }
    return result;
  }

}

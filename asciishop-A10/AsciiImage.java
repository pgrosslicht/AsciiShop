import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class AsciiImage {
  private int width, height = 0;
  private char[][] image;
  private String charset;

  /**
   * initializes the image
   */
  public AsciiImage(int width, int height, String charset) {
    if(width <=0 || height <= 0 || charset.length() == 0 || doubleCharacters(charset)) {
      throw new IllegalArgumentException();
    }
    this.image = new char[height][width];
    this.width = width;
    this.height = height;
    this.charset = charset;
    char brightestChar = charset.charAt(charset.length()-1);
    for (char[] row : image) {
      Arrays.fill(row, brightestChar);
    }
  }

  /**
   * initializes a new image from an existing image
   * @param img image to copy
   */
  public AsciiImage(AsciiImage img) {
    height = img.height;
    width = img.width;
    charset = img.charset;
    image = new char[height][width];

    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        image[i][j] = img.image[i][j];
      }
    }
  }

  private boolean doubleCharacters(String string) {
    for (int i = 0; i < string.length(); i++) {
      if (i != string.lastIndexOf(string.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return width of the image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * @return height of the image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * @return charset of the image
   */
  public String getCharset() {
    return this.charset;
  }

  /**
   * @return the formatted ASCII image with line breaks according to its width
   */
  public String toString() {
    String formattedString = "";
    for (int i = 0; i < this.height; i++) {
      for (char c : this.image[i]) {
        formattedString += c;
      }
      formattedString += "\n";
    }
    return formattedString;
  }

  /**
   * returns the character at the position (x|y)
   * @param x x-coordinate
   * @param y y-coordinate
   * @return character at that position
   */
  public char getPixel(int x, int y) {
    if (x >= 0 && x < this.getHeight() && y >= 0 && y < this.getWidth()) {
      return this.image[x][y];
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * returns the character at the position (x|y)
   * @param p AsciiPoint of the chosen position
   * @return character at that position
   */
  public char getPixel(AsciiPoint p) {
    return getPixel(p.getX(), p.getY());
  }

  /**
   * sets the pixel at the position (x|y) to character c
   * @param x x-coordinate
   * @param y y-coordinate
   * @param c character to set
   */
  public void setPixel(int x, int y, char c) {
    if (x >= 0 && x < this.getHeight() && y >= 0 && y < this.getWidth() && !(this.getCharset().indexOf(c) < 0)) {
      this.image[x][y] = c;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * sets the pixel at the position (x|y) to character c
   * @param p AsciiPoint of the chosen position
   * @param c character to set
   */
  public void setPixel(AsciiPoint p, char c) {
    setPixel(p.getX(), p.getY(), c);
  }

  /**
   * returns an ArrayList with all positions as AsciiPoints that are c
   * @param c character to look for
   * @return ArrayList of AsciiPoints
   */
  public ArrayList<AsciiPoint> getPointList(char c) {
    ArrayList<AsciiPoint> pixels = new ArrayList<AsciiPoint>();

    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        if(image[i][j] == c) { pixels.add(new AsciiPoint(i, j)); }
      }
    }
    return pixels;
  }

  public boolean equals(Object o) {
    if (this.getClass() != o.getClass()) {
      return false;
    }
    AsciiImage other = (AsciiImage)o;
    if (this.getHeight() != other.getHeight() || this.getWidth() != other.getWidth()) {
      return false;
    }
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        if(this.getPixel(i, j) != other.getPixel(i, j)) { return false; }
      }
    }
    return true;
  }

  public int hashCode() {
    int hash = 0;
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        hash += image[i][j];
      }
    }
    return hash;
  }

  /**
   * @return the count of unique characters in the image
   */
  public int getUniqueChars() {
    HashSet unique_chars = new HashSet();
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        unique_chars.add(image[i][j]);
      }
    }
    return unique_chars.size();
  }

}

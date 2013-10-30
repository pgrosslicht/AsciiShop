import java.util.Arrays;

public class AsciiImage {
  private int width, height = 0;
  private char[][] image;

  /*
   * Zeile - y
   * Spalte - x
   */

  /**
   * initializes the image
   */
  public AsciiImage(int width, int height) {
    /* System.out.println("initializing with height: "+height+" and width: "+width); */
    this.image = new char[height][width];
    this.width = width;
    this.height = height;
    this.clear();
  }

  /**
   * adds a line to the image
   * if it's the first line, it sets the width
   * @param line the line to be added
   * @return true if the line was added, false otherwise
   */
  public boolean addLine(String line) {
    if (line.length() > 0 && line.length() == this.getWidth()) {
      this.image[this.image.length-1] = line.toCharArray();
      this.height++;
      return true;
    }
    return false;
  }

  /**
   * clears the image and sets all the pixels to dots.
   */
  public void clear() {
    for (char[] row : image) {
      Arrays.fill(row, '.');
    }
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
    return this.image[y][x];
  }

  /**
   * sets the pixel at the position (x|y) to character c
   * @param x x-coordinate
   * @param y y-coordinate
   * @param c character to set
   */
  public void setPixel(int x, int y, char c) {
    this.image[y][x] = c;
  }

  /**
   * Floodfill algorithm
   * checks if neighbouring characters are the same as the replaced, if so, repeats the process for this one as well.
   * @param image Array of strings, represents an ASCII image
   * @param x x-coordinate
   * @param y y-coordinate
   * @param c the character that's going to replace the current character at the position (x|y)
   */
  public void fill(int x, int y, char c) {
    char old_char = this.getPixel(x, y);
    this.setPixel(x, y, c);

    if(x + 1 < this.width && old_char == getPixel(x+1, y))
      fill(x+1, y, c);
    if(x - 1 >= 0 && old_char == getPixel(x-1, y))
      fill(x-1, y, c);
    if(y + 1 < this.height && old_char == getPixel(x, y+1))
      fill(x, y+1, c);
    if(y - 1 >= 0 && old_char == getPixel(x, y-1))
      fill(x, y-1, c);
  }

  /**
   * Drawing lines
   * @param x0 x-coordinate of start
   * @param y0 y-coordinate of start
   * @param x1 x-coordinate of end
   * @param y1 x-coordinate of end
   * @param c character to draw the line with
   */
  public void drawLine(int x0, int y0, int x1, int y1, char c) {
    int dx = x1 - x0;
    int dy = y1 - y0;

    double slope = dx == 0 ? 0 : 1.0 * dy/dx;

    if(dx >= 0 && Math.abs(dy) <= Math.abs(dx)) {
      for(int i = 0; i <= dx; i++) {
        setPixel(x0 + i, (int) Math.round(y0 + i*slope), c);
      }
    } else if(dx < 0 && Math.abs(dy) <= Math.abs(dx)) {
      drawLine(x1, y1, x0, y0, c);
    } else {
      transpose();
      if(dy >= 0 && Math.abs(dy) > Math.abs(dx)) {
        drawLine(y0, x0, y1, x1, c);
      } else {
        drawLine(y1, x1, y0, x0, c);
      }
      transpose();
    }
  }

  /**
   * transposes the image, i.e. every pixel (x, y) becomes (y, x) and changes the dimensions accordingly
   */
  public void transpose() {
    char[][] newImage = new char[this.width][this.height];

    for(int i = 0; i < this.height; i++) {
      for(int j = 0; j < this.width; j++) {
        newImage[j][i] = image[i][j];
      }
    }

    int temp = this.height;
    this.height = this.width;
    this.width = temp;

    this.image = newImage;
  }

  /**
   * replaces old character with new character
   * @param oldChar old character
   * @param newChar new character
   */
  public void replace(char oldChar, char newChar) {
    for (int i = 0; i < this.height; i++) {
      for(int j = 0; j < this.width; j++) {
        if (image[i][j] == oldChar) {
          image[i][j] = newChar;
        }
      }
    }
  }

  /**
   * checks if image is horizontally symmetric by checking if each line is a palindrome
   * @return boolean
   */
  /* public boolean isSymmetricH() { */
  /*   for(int i = 0; i < height; i++) { */
  /*     if(!isPalindrome(this.image.substring(i * this.width, (i+1) * this.width))) { */
  /*       return false; */
  /*     } */
  /*   } */
  /*   return true; */
  /* } */

  /**
   * checks if string is a palindrome
   * @param string string to be checked
   * @return boolean
   */
  /* private boolean isPalindrome(String string){ */
  /*   int i1 = 0; */
  /*   int i2 = string.length() - 1; */
  /*   while (i2 > i1) { */
  /*       if (string.charAt(i1) != string.charAt(i2)) { */
  /*           return false; */
  /*       } */
  /*       i1++; */
  /*       i2--; */
  /*   } */
  /*   return true; */
  /* } */
}

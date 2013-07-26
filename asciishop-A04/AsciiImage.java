public class AsciiImage {
  private int width, height = 0;
  private String image = "";


  /**
   * adds a line to the image
   * if it's the first line, it sets the width
   * @param line the line to be added
   * @return true if the line was added, false otherwise
   */
  public boolean addLine(String line) {
    //if adding first line, set width and height
    if (this.image.length() == 0 && line.length() > 0) {
      this.image += line;
      this.width = line.length();
      this.height++;
      return true;
    }
    if (line.length() > 0 && line.length() == this.getWidth()) {
      this.image += line;
      this.height++;
      return true;
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
   * @return the formatted ASCII image with line breaks according to its width
   */
  public String toString() {
    String formattedString = "";
    int interval = this.getWidth();
    for (int i = 0; i < this.image.length(); i += interval) {
      formattedString = formattedString + this.image.substring(i, i + interval) + "\n";
    }
    return formattedString;
  }

  /**
   * @return the count of unique characters in the image
   */
  public int getUniqueChars() {
    String unique_chars = "";
    for (char ch: image.toCharArray()) {
        if (!unique_chars.contains(String.valueOf(ch))) {
          unique_chars += ch;
        }
      }
    return unique_chars.length();
  }

  /**
   * flips the image vertically, i.e. the first line becomes the last, the second becomes the second to last and so on.
   */
  public void flipV() {
    String tempImage = "";
    for(int i = this.height; i > 0; i--) {
      tempImage += this.image.substring((i-1) * this.width, i * this.width);
    }
    this.image = tempImage;
  }

  /**
   * transposes the image, i.e. the first column becomes the first line, and sets width and height accordingly
   */
  public void transpose() {
    String tempImage = "";
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        tempImage += getPixel(i, j);
      }
    }
    int temp = this.height;
    this.height = this.width;
    this.width = temp;
    this.image = tempImage;
  }

  /**
   * returns the character at the position (x|y)
   * @param x x-coordinate
   * @param y y-coordinate
   * @return character at that position
   */
  public char getPixel(int x, int y) {
    return this.image.charAt(x + this.getWidth() * y);
  }

  /**
   * sets the pixel at the position (x|y) to character c
   * @param x x-coordinate
   * @param y y-coordinate
   * @param c character to set
   */
  public void setPixel(int x, int y, char c) {
    this.image = this.image.substring(0, x + this.getWidth() * y) + c + this.image.substring(x + this.getWidth() * y + 1);
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
   * checks if image is horizontally symmetric by checking if each line is a palindrome
   * @return boolean
   */
  public boolean isSymmetricH() {
    for(int i = 0; i < height; i++) {
      if(!isPalindrome(this.image.substring(i * this.width, (i+1) * this.width))) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if string is a palindrome
   * @param string string to be checked
   * @return boolean
   */
  private boolean isPalindrome(String string){
    int i1 = 0;
    int i2 = string.length() - 1;
    while (i2 > i1) {
        if (string.charAt(i1) != string.charAt(i2)) {
            return false;
        }
        i1++;
        i2--;
    }
    return true;
}
}

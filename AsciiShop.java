import java.util.Scanner;
import java.util.Arrays;

public class AsciiShop {
    static boolean ausgabe = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] image = null;
        int length = 0;
        int lines = 0;
        boolean ende = false;
        if (sc.hasNext()) {
          if ("read".equals(sc.next())) {
            if (sc.hasNextInt()) {
              lines = sc.nextInt();
              image = new String[lines];
            } else {
              System.out.println("INPUT MISMATCH");
              ende = true;
              ausgabe = false;
            }
          } else {
            System.out.println("INPUT MISMATCH");
            ende = true;
            ausgabe = false;
          }
        }
        // Loop through input
        for (int i = 0; i < lines; i++) {
          if (ende) {
            break;
          }
          if (sc.hasNext() && !ende) {
            image[i] = sc.next();
          } else {
            System.out.println("INPUT MISMATCH");
            ende = true;
            ausgabe = false;
          }
        }
        
        if (!ende) {
          length = image[0].length();
          //Loop through array to check if all lines are equally long
          for (int i = 0; i < image.length; i++) {
            if (length != image[i].length() && !ende) {
              System.out.println("INPUT MISMATCH");
              ende = true;
              ausgabe = false;
            }
          }
        }
        while (sc.hasNext() && !ende) {
          if ("fill".equals(sc.next())) {
            if (sc.hasNextInt()) {
              int x = sc.nextInt();
              if (sc.hasNextInt()) {
                int y = sc.nextInt();
                  if (sc.hasNext()) {
                    char c = sc.next().charAt(0);
                    fill(image, x, y, c);
                  } else {
                    System.out.println("INPUT MISMATCH");
                    ende = true;
                    ausgabe = false;
                  }
              } else {
                System.out.println("INPUT MISMATCH");
                ende = true;
                ausgabe = false;
              }
            } else {
              System.out.println("INPUT MISMATCH");
              ende = true;
              ausgabe = false;
            }
          } else {
            System.out.println("INPUT MISMATCH");
            ende = true;
            ausgabe = false;
          }
        }
        
        
        
        if (ausgabe) {
          for (int i = 0; i < image.length; i++) {
            System.out.println(image[i]);
          }
          System.out.print(length);
          System.out.print(" "+ lines);
        }
    }
    
    public static void fill(String[] image, int x, int y, char c) {
      boolean error = false;
      int lines = image.length;
      int length = image[0].length();
      if (y < 0 || y > lines || x < 0 || x > length) {
        System.out.println("OPERATION FAILED");
        ausgabe = false;
      } else {
        char old_char = image[y].charAt(x);
        image[y] = image[y].substring(0,x) + c + image[y].substring(x+1);

        if ((y - 1) >= 0 && image[y-1].charAt(x) == old_char) {
          fill(image, x, y - 1, c);
        }
        if ((y + 1) <= lines && image[y+1].charAt(x) == old_char) {
          fill(image, x, y + 1, c);
        }
        if ((x + 1) < length  && image[y].charAt(x+1) == old_char) {
          fill(image, x + 1, y, c);
        }
        if ((x - 1) >= 0 && image[y].charAt(x-1) == old_char) {
          fill(image, x - 1, y , c);
        }

      }
    }
}


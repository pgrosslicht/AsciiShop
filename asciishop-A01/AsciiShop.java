import java.util.Scanner;

public class AsciiShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        int l;
        boolean ende = false;
        l = sc.next().length();
        // Loop through Scanner
        while (sc.hasNext() && !ende) {
          int sl = sc.next().length();
          if(sl != l) {
            // break loop and exit if lines are not of equal length
            System.out.println("INPUT MISMATCH");
            ende = true;
          } else {
            l = sl;
          }
          i++;
        }
        if (!ende) {
          System.out.print(l);
          System.out.print(" "+ i);
        }
    }
}

import java.util.Scanner;

/**
 * BarPlot generiert horizontale Balkendiagramme, die aus einer Beschriftung bestehen
 * @author Patrick Grosslicht
 */

public class BarPlot {

  /**
   * Hauptprogramm, die alle anderen funktionen aufruft
   * @params args Parameter
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean fehler = false;
    while (sc.hasNext() && !fehler) {
      String label = sc.next();
      if (sc.hasNextInt()) {
        int value_i = sc.nextInt();
        if (value_i > 30 || value_i < 0) {
          System.out.println("INPUT ERROR");
          fehler = true;
        } else {
          System.out.println(drawBar(drawLabel(label, 8), value_i));
        }
      } else if (sc.hasNextDouble()) {
        double value_d = sc.nextDouble();
        if (value_d > 1.0 || value_d < 0.0) {
          System.out.println("INPUT ERROR");
          fehler = true;
        } else {
          System.out.println(drawBar(drawLabel(label, 8), value_d));
        }
      } else {
        System.out.println("INPUT ERROR");
        fehler = true;
      }
    }       
    sc.close();
  }

  /**
   * repeat gibt einen String der Länge n bestehend aus dem Charakter c aus.
   * @param c Charakter der wiederholt werden soll
   * @param n Wie oft der Charakter wiederholt werden soll
   *
   * @return String der Länge n bestehend aus c
   */
  static String repeat(char c, int n) {
    String output = new String(new char[n]).replace('\0', c); //char mit Länge n erstellen, in String umwandeln, entstehende 0er mit c ersetzen
    return output;
  }

  /**
   * drawLabel gibt einen String aus, der label beinhaltet aber n Zeichen lang ist.
   * @param label String der zum Label gemacht wird
   * @param n Länge des Labels
   *
   * @return String der Länge n, der label beinhaltet
   */
  static String drawLabel(String label, int n) {
    if (label.length() <= n) {
      return label + repeat(' ', n - label.length());
    } else {
      return label.substring(0, n);
    }
  }

  /**
   * drawBar generiert eine Zeile des Balkendiagramms mit dem Label label und der Länge value
   * @param label Label der Zeile
   * @param value Länge des Balken
   *
   * @return fertig formatierte Zeile
   */
  static String drawBar(String label, int value) {
    return label + "|" + repeat('#', value) + repeat(' ', 30-value) + "|";
  }

  /**
   * drawBar generiert eine Zeile des Balkendiagramms mit dem Label label und der prozentuellen Länge value
   * @param label Label der Zeile
   * @param value prozentuelle Länge des Balken
   *
   * @return fertig formatierte Zeile
   */
  static String drawBar(String label, double value) {
    int barlength = (int) Math.round(0.3 * value*100);
    return label + "|" + repeat('#', barlength) + repeat(' ', 30-barlength) + "|";
  }
}

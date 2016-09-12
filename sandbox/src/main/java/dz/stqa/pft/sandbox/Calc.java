package dz.stqa.pft.sandbox;

/**
 * Created by aleksandr.petrov on 10.09.16.
 */
public class Calc {

  public static void main(String[] args) {

    Point p1 = new Point(7,10);
    Point p2 = new Point(12,15);
    System.out.print("Ответ " + p1.distance(p2));
  }
}
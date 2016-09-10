package dz.stqa.pft.sandbox;

/**
 * Created by aleksandr.petrov on 10.09.16.
 */
public class Calc {

  public static void main(String[] args) {

    Point p1 = new Point(7,10);
    Point p2 = new Point(12,15);



    System.out.print("Ответ " + distance(p1,p2));
  }

  public static double distance(Point p1, Point p2){

    double a = Math.pow((p2.x - p1.x), 2);
    double b = Math.pow((p2.y - p1.y), 2);
    double c = Math.sqrt(a + b);
    return c;
  }
}

/*
c^2 = a^2 + b^2
a = x2 - x1
b = y2 - y1
 */
package dz.stqa.pft.sandbox;
/**
 * Created by aleksandr.petrov on 09.09.16.
 */
public class Point {

  double x;
  double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }
  public double distance(Point p){

    double a = Math.pow((this.x - p.x), 2);
    double b = Math.pow((this.y - p.y), 2);
    double c = Math.sqrt(a + b);
    return c;
  }

  }


package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

    Rectangle e = new Rectangle(2 , 4);
    System.out.println("Площадь прямоугольника со сторонами " + e.a + " и " + e.b + " = " + area(e));

    }

  public static double area (Square s){
    return s.l * s.l;
  }

  public static double area (Rectangle one){
    return one.a * one.b;
  }
}
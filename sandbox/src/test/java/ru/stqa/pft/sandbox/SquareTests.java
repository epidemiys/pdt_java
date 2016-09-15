package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

/**
 * Created by aleksandr.petrov on 15.09.16.
 */
public class SquareTests {

  @Test

  public void testAria(){
    Square s = new Square(5);
    assert s.area() == 25;

  }
}

package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by aleksandr.petrov on 15.09.16.
 */
public class SquareTests {

  @Test
  public void testAria(){
    Square a = new Square(5);
    Assert.assertEquals(MyFirstProgram.area(a), 25.0);
  }
}

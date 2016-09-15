package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by aleksandr.petrov on 15.09.16.
 */
public class SquareTests {

  @Test

  public void testAria(){
    MyFirstProgram a = new MyFirstProgram(5);
    Assert.assertEquals(a.area(), 25);
  }
}

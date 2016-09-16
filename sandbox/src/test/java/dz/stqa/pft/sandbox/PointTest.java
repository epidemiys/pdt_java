package dz.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by aleksandr.petrov on 15.09.16.
 */
public class PointTest {
  @Test
  //Проверка расстояния равного нулю
  public void testNull(){
    Point a = new Point(0, 0);
    Point b = new Point(0, 0);
    Assert.assertEquals(a.distance(b) , 0.0);
  }

  @Test
  /*Проверка расстояния не равного нулю
  Вообще переменные типа double тестировать на точное сравнение вероятно не достаточно корректно, в рамках данного задания предлагаю считать, что это ок
   */
  public void testNonNull(){
    Point a = new Point(1, 1);
    Point b = new Point(2, 2);
    Assert.assertEquals(a.distance(b) , 1.4142135623730951);
  }

  @Test
  //Проверка того, что значение не равно корректному. Проверка бред, просто потрогал новый метод.
  public void testPo(){
    Point a = new Point(1, 1);
    Point b = new Point(2, 2);
    Assert.assertNotEquals(a.distance(b) , 0);
  }
}

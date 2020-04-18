package ru.stqa.pft.lessonOne.partOne;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void test1() {
        Point p = new Point(1, 1,5, 5);
        Assert.assertEquals(p.distance(), 5.656854249492381);
    }

    @Test
    public void test2() {
        Point p = new Point(1, 1,1, 1);
        Assert.assertEquals(p.distance(), 0);
    }

    @Test
    public void test3() {
        Point p = new Point(-1, 0.1,100000, -100000);
        Assert.assertEquals(p.distance(), 141422.1340562007);
    }
    
}

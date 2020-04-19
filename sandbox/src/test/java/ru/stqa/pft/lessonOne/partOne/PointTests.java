package ru.stqa.pft.lessonOne.partOne;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void test1() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        Assert.assertEquals(p1.distance(p2), 5.656854249492381);
    }

    @Test
    public void test2() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void test3() {
        Point p1 = new Point(-1, 0.1);
        Point p2 = new Point(10000, -10000);

        Assert.assertEquals(p1.distance(p2), 14142.91345550838);
    }
    
}

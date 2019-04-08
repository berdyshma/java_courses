package by.berdysh.java_course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

	@Test
	public void testDistance1 (){

		Point p1 = new Point(0,0);
		Point p2 = new Point(3,4);
		Assert.assertEquals(p1.distance(p2),5.0);

	}

	@Test
	public void testDistance2 (){

		Point p3 = new Point(-1,-9);
		Point p4 = new Point(6,5);
		Assert.assertEquals(p3.distance(p4),15.652475842498529);
	}
}

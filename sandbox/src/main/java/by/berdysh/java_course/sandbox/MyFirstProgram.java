package by.berdysh.java_course.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point p1 = new Point(0,0);
		Point p2 = new Point(3,4);

		System.out.println("Расстояние между точками P1 и P2 = " + p1.distance(p2));

		Point p3 = new Point(-1,-9);
		Point p4 = new Point(6,5);

		System.out.println("Расстояние между точками P1 и P2 = " + p3.distance(p4));
	}
}
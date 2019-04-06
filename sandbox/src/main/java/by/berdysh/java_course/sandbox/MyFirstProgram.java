package by.berdysh.java_course.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point p = new Point(3,6,5,10);
			System.out.println("Расстояние между точками P1 (с координатами " + p.x1 + " и " + p.x2 + " ) " +
							"и P2 (с координатами " + p.y1 + " и " + p.y2 + " ) " + " = " + p.distance());
	}


	}




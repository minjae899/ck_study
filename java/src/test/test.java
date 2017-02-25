package test;

public class test {
	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint(5);
	}
}

class P {
	private int x = 1;

	P() {
		System.out.println("P의 생성자 실행");
	}

	P(int x) {
		System.out.println("P x = " + x);
	}
}

class Point extends P {
	protected static int x = 2;

	Point() {
		super(x);
		System.out.println("Point의 생성자 실행");
	}

	Point(int x) {
		this();
		System.out.println("Point x = " + x);
	}
}

class ColorPoint extends Point {
	int x = 3;

	ColorPoint() {
		System.out.println("ColorPoint의 생성자 실행");
	}

	ColorPoint(int x) {
		super(x);
		System.out.println("ColorPoint x = " + super.x + ", " + this.x);
	}
}
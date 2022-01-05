public class Toolbox {

	public static double sq(double a) {
		return a * a;
	}

	public static double pythagoras(double x1, double y1, double x2, double y2) {
		return Math.sqrt(sq(x1 - x2) + sq(y1 - y2));
	}

	public static double inverse(double a) {
		return -(1 / a);
	}

	public static double slope(double x1, double y1, double x2, double y2) {
		return (y2 - y1) / (x2 - x1);
	}

	public static double slope(double x, double y) {
		return y / x;
	}

	public static double yint(double x1, double y1, double x2, double y2) {
		double slope = slope(x1, y1, x2, y2);
		return y1 - slope * x1;
	}

	public static double yint(double slope, double x, double y) {
		return y - slope * x;
	}

	public static double getAngle(double x1, double y1, double x2, double y2) {
		if (x2 > x1) {
			return Math.atan(slope(x1, y1, x2, y2));
		}
		if (x2 < x1) {
			return Math.atan(slope(x1, y1, x2, y2)) + Math.PI;
		}
		if (x1 == x2) {
			if (y1 < y2) {
				return Math.PI / 2;
			}
			return 3 * Math.PI / 2;
		}
		return 42;
	}

	public static double polarToRectangularX(Double ang, Double r) {
		return Math.cos(ang) * r;
	}

	public static double polarToRectangularY(Double ang, Double r) {
		return Math.sin(ang) * r;
	}

	public static double angleSimplify(double ang) {
		return ang % 2 * Math.PI;
	}

	public static double flipAcrossLine(double ang, double line) {
		double a = line - ang;
		return ang + 2 * a;
	}
}

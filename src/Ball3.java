import java.awt.Color;

public class Ball3 {
	// field variables
	private double rx, ry;
	private double vx, vy;
	private double radius;
	private int color;
	private double bouncy;
	private double gravity;
	private boolean square;
	private boolean landed, upready, downready, changeready, leftready,
			rightready, uppressed, downpressed, leftpressed, rightpressed,
			changepressed = false;
	private int left, up, right, down, change;
	private int score = 0;
	private double tailrx1, tailry1, tailrx2, tailry2, tailrx3, tailry3;
	private int keyboard;
	private Ball3 target;

	// general constructor
	public Ball3(int keyboardi, int shape, int x, int y) {
		if (Math.random() > .5) {
			square = true;
		} else {
			square = false;
		}
		if (shape == -1) {
			square = true;
		}
		if (shape == 1) {
			square = false;
		}

		keyboard = keyboardi;

		if (keyboardi == 0 || keyboardi == 3) {
			left = 37;
			up = 38;
			right = 39;
			down = 40;
			change = 35;
			color = 4;
		}

		if (keyboardi == 1) {
			left = 65;
			up = 87;
			right = 68;
			down = 83;
			change = 69;
			color = 0;
		}

		if (keyboardi ==2) {
			color = 0;
		}

		rx = x;
		ry = y;
		vx = 0.015 - Math.random() * 0.03;
		vy = 0.015 - Math.random() * 0.03;
		radius = .15;
		bouncy = 0.5;
		gravity = .01;
		tailrx1 = rx;
		tailry1 = ry;
		tailrx2 = rx;
		tailry2 = ry;
		tailrx3 = rx;
		tailry3 = ry;

	}

	// specific constructor
	public Ball3(double rxi, double ryi, double vxi, double vyi,
			double radiusi, int colori, double bouncyi, double gravityi) {

		rx = rxi;
		ry = ryi;
		vx = vxi;
		vy = vyi;
		radius = radiusi;
		color = colori;
		bouncy = bouncyi;
		gravity = gravityi;

	}

	// definitions
	public static final Color RED = new Color(255, 0, 0);
	public static final Color ORANGE = new Color(255, 128, 0);
	public static final Color YELLOW = new Color(255, 255, 0);
	public static final Color GREEN = new Color(0, 255, 0);
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color PURPLE = new Color(128 + 64 + 32, 0, 255);
	public static final Color GRASS = new Color(0, 128 + 64, 0);
	public static final Color SKY = new Color(0, 128 + 64 + 32, 128 + 64 + 32);
	public static final Color METAL = new Color(128 + 64 + 32, 16, 16);
	public static final Color SEA = new Color(0, 125, 255);
	public static final Color SPACE = new Color(0, 32, 128);
	public static final Color MOON = new Color(240, 240, 255);
	public static final Color PLANET = new Color(225, 225, 195);

	static Color[] color3s = { RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE };

	// moves the ball
	public void plan(Ball3[] b) {

		vy = vy - gravity;

		double k = radius;

		if (StdDraw.isKeyPressed(change)) {
			changepressed = true;
		} else {
			changepressed = false;
		}

		if (StdDraw.isKeyPressed(left)) {
			leftpressed = true;
		} else {
			leftpressed = false;
		}

		if (StdDraw.isKeyPressed(right)) {
			rightpressed = true;
		} else {
			rightpressed = false;
		}

		if (StdDraw.isKeyPressed(up)) {
			uppressed = true;
		} else {
			uppressed = false;
		}

		if (StdDraw.isKeyPressed(down)) {
			downpressed = true;
		} else {
			downpressed = false;
		}

		if (keyboard == 2) {
			double n = 1;

			if (ry > target.ry + k) {
				if (rx > target.rx) {
					leftpressed = true;
				}

				if (rx < target.rx) {
					rightpressed = true;
				}

				for (int i = 0; i < 40 * (.25 - Math.abs(target.vx)); i += 1) {
					double m = .1;
					if (Math.abs((-.2 * i + ry - radius)
							- (target.vy * i + target.ry + target.radius)) < m
							&& Math.abs((rx) - (target.vx * i + target.rx)) < m) {
						downpressed = true;
					} else {
						uppressed = true;
					}
				}
			}
			if (Math.abs(ry - target.ry) < k) {
				if (rx > target.rx + k * 3) {
					if (vy > target.vy) {
						leftpressed = true;
						uppressed = true;
					}
					if (target.vy > vy) {
						rightpressed = true;
					}
				}
				if (Math.abs(rx - target.rx) < k * 3) {
					if (vy > target.vy) {
						uppressed = true;
					}
					if (target.vy > vy) {
						downpressed = true;
					}
				}
				if (3 * k + rx < target.rx) {
					if (vy > target.vy) {
						rightpressed = true;
						uppressed = true;
					}
					if (target.vy > vy) {
						leftpressed = true;
					}
				}
			}
			if (k + ry < target.ry) {
				if (target.rx > n && rx > n) {
					downpressed = true;
					leftpressed = true;
				} else if (target.rx < -n && rx < -n) {
					downpressed = true;
					rightpressed = true;
				} else if (rx > -n && rx < n && target.rx > -n && target.rx < n) {
					if (rx - target.rx > k) {
						if (rx > target.rx) {
							if (vx < 0) {
								downpressed = true;
							}
							rightpressed = true;
						}
						if (rx < target.rx) {
							if (vx > 0) {
								downpressed = true;
							}
							leftpressed = true;
						}
					} else {
						if (ry - target.ry < k * 5 && !landed) {
							downpressed = true;
						}
						if (rx < tailrx1) {
							leftpressed = true;
						}
						if (rx > tailrx1) {
							rightpressed = true;
						}
					}
				} else {
					if (rx > target.rx) {
						rightpressed = true;
					}
					if (rx < target.rx) {
						leftpressed = true;
					}
					if (Math.abs(rx - target.rx) > k * 3) {
						uppressed = true;
					} else {
						downpressed = true;
					}
				}

			}

		}

		if (landed) {
			upready = true;
		}

		if (uppressed && upready) {
			vy += .15;
			landed = false;
			upready = false;
		}

		if (!downpressed) {
			downready = true;
		}

		if (downpressed && downready) {
			vx = 0;
			vy = -.2;
			downready = false;
		}

		if (!leftpressed) {
			leftready = true;
		}

		if (leftpressed && leftready) {
			vx -= .015;
			leftready = false;
		}

		if (!rightpressed) {
			rightready = true;
		}

		if (rightpressed && rightready) {
			vx += .015;
			rightready = false;
		}

		if (!changepressed) {
			changeready = true;
		}

		if (changepressed && changeready) {
			color++;
			color = color % 6;
			changeready = false;
		}

		if (leftpressed) {
			vx -= .0075;
		}
		if (rightpressed) {
			vx += .0075;
		}
		if (uppressed) {
			vy += .005;
		}

		if (Math.abs(vx) > 1) {
			vx = vx / Math.abs(vx);
		}
		if (Math.abs(vy) > 1) {
			vy = vy / Math.abs(vy);
		}

		if (checkReflection(b)) {
			Reflect(checkWhichReflection(b));
		}

		if (keyboard == 3) {
			vx = 0;
			vy = 0;
			if (leftpressed) {
				rx -= .15;
			}
			if (uppressed) {
				ry += .15;
			}
			if (rightpressed) {
				rx += .15;
			}
			if (downpressed) {
				ry -= .15;
			}
		}
	}

	public void move() {
		if (rx + radius > 1.5) {
			bounceOffVerticalWallRight();
		}

		if (rx - radius < -1.5) {
			bounceOffVerticalWallLeft();
		}

		if (ry - radius < -1.5) {
			bounceOffHorizontalWallUp();
		}

		if (ry + radius > 1.5) {
			bounceOffHorizontalWallDown();
		}
		rx = rx + vx;
		ry = ry + vy;
	}

	// methods for ball to ball collision
	public boolean checkReflection(Ball3[] arr) {
		for (int i = 0; i < arr.length; i++) {
			Ball3 a = arr[i];
			if (Toolbox.pythagoras(rx, ry, a.rx, a.ry) < radius + a.radius
					&& Toolbox.pythagoras(rx, ry, a.rx, a.ry) != 0) {
				return true;

			}
		}
		return false;
	}

	public Ball3 checkWhichReflection(Ball3[] arr) {
		for (int i = 0; i < arr.length; i++) {
			Ball3 a = arr[i];
			if (Toolbox.pythagoras(rx, ry, a.rx, a.ry) < radius + a.radius
					&& Toolbox.pythagoras(rx, ry, a.rx, a.ry) != 0) {
				return arr[i];
			}
		}
		return arr[0];
	}

	public void setTarget(Ball3 b) {
		target = b;
	}

	public void Reflect(Ball3 b) {
		double k = .5;
		if (ry - b.ry > radius / 2 && vy - b.vy < 0) {
			score++;
			upready = true;
		}
		if (b.ry - ry > radius / 2 && b.vy - vy < 0) {
			b.score++;
			b.upready = true;
		}
		vx = k
				* (Toolbox.pythagoras(0, 0, b.vx, b.vy) + Toolbox.pythagoras(0,
						0, vx, vy))
				* Math.cos(Toolbox.getAngle(b.rx, b.ry, rx, ry));
		vy = k
				* (Toolbox.pythagoras(0, 0, b.vx, b.vy) + Toolbox.pythagoras(0,
						0, vx, vy))
				* Math.sin(Toolbox.getAngle(b.rx, b.ry, rx, ry));
		b.vx = k
				* (Toolbox.pythagoras(0, 0, b.vx, b.vy) + Toolbox.pythagoras(0,
						0, vx, vy))
				* Math.cos(Toolbox.getAngle(rx, ry, b.rx, b.ry));
		b.vy = k
				* (Toolbox.pythagoras(0, 0, b.vx, b.vy) + Toolbox.pythagoras(0,
						0, vx, vy))
				* Math.sin(Toolbox.getAngle(rx, ry, b.rx, b.ry));
	}

	// methods for ball to wall collision
	private void bounceOffVerticalWallRight() {
		vx = bouncy * -Math.abs(vx);
	}

	private void bounceOffVerticalWallLeft() {
		vx = bouncy * Math.abs(vx);
	}

	private void bounceOffHorizontalWallUp() {
		vy = bouncy * Math.abs(vy);
		landed = true;
	}

	private void bounceOffHorizontalWallDown() {
		vy = bouncy * -Math.abs(vy);
	}

	private void bounceOffHorizontalWall() {
		vy = -vy * bouncy;
	}

	private void bounceOffVerticalWall() {
		vx = -vx * bouncy;
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(rx, ry, tailrx1, tailry1);
		StdDraw.line(tailrx2, tailry2, tailrx1, tailry1);
		StdDraw.line(tailrx2, tailry2, tailrx3, tailry3);

		if (square) {
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledSquare(tailrx1, tailry1, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(tailrx1, tailry1, radius * .25);
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledSquare(tailrx2, tailry2, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(tailrx2, tailry2, radius * .25);
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledSquare(tailrx3, tailry3, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(tailrx3, tailry3, radius * .25);
		} else {
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledCircle(tailrx1, tailry1, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(tailrx1, tailry1, radius * .25);
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledCircle(tailrx2, tailry2, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(tailrx2, tailry2, radius * .25);
			StdDraw.setPenColor(color3s[color]);
			StdDraw.filledCircle(tailrx3, tailry3, radius * .25);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(tailrx3, tailry3, radius * .25);
		}

		StdDraw.setPenRadius(.005);
		StdDraw.setPenColor(color3s[color]);
		double k = 3;

		if (square) {
			if (Toolbox.pythagoras(0, 0, vx, vy) > 0) {
				StdDraw.filledRectangle(
						rx,
						ry,
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vx)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5),
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vy)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5));
				StdDraw.setPenColor(Color.black);
				StdDraw.rectangle(
						rx,
						ry,
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vx)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5),
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vy)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5));
			} else {
				StdDraw.filledSquare(rx, ry, radius);
				StdDraw.setPenColor(Color.black);
				StdDraw.square(rx, ry, radius);
			}
			StdDraw.filledCircle(rx + .25 * radius, ry + .25 * radius, .01);
			StdDraw.filledCircle(rx - .25 * radius, ry + .25 * radius, .01);
			StdDraw.arc(rx, ry, radius / 2, 225, 315);
		} else {
			if (Toolbox.pythagoras(0, 0, vx, vy) > 0) {
				StdDraw.filledEllipse(
						rx,
						ry,
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vx)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5),
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vy)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5));
				StdDraw.setPenColor(Color.black);
				StdDraw.ellipse(
						rx,
						ry,
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vx)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5),
						radius
								+ k
								* radius
								* Toolbox.pythagoras(0, 0, vx, vy)
								* (Math.abs(vy)
										/ Toolbox.pythagoras(0, 0, vx, vy) - .5));
			} else {
				StdDraw.filledCircle(rx, ry, radius);
				StdDraw.setPenColor(Color.black);
				StdDraw.circle(rx, ry, radius);
			}
			StdDraw.filledCircle(rx + .25 * radius, ry + .25 * radius, .01);
			StdDraw.filledCircle(rx - .25 * radius, ry + .25 * radius, .01);
			StdDraw.arc(rx, ry, radius / 2, 225, 315);
		}
		double j = .25;
		tailrx1 += (rx - tailrx1) / 2;
		tailry1 += ((ry - tailry1) / 2) - gravity * 2;
		tailrx2 += (tailrx1 - tailrx2) / (2 + j);
		tailry2 += ((tailry1 - tailry2) / (2 + j)) - gravity * 2;
		tailrx3 += (tailrx2 - tailrx3) / (2 + 2 * j);
		tailry3 += ((tailry2 - tailry3) / (2 + 2 * j)) - gravity * 2;
	}

	public static void main(String[] args) {

		StdDraw.setXscale(-1.5, 1.5);
		StdDraw.setYscale(-1.5, 1.5);

		Ball3 a = new Ball3(0, 1, -1, -1);
		Ball3 b = new Ball3(2, 1, 1, -1);
		a.setTarget(b);
		b.setTarget(a);
		Ball3[] ball3s = { a, b };

		while (true) {
			StdDraw.setPenColor(SKY);
			StdDraw.filledSquare(0, 0, 5);
			StdDraw.setPenColor(GRASS);
			StdDraw.filledRectangle(0, -1.65, 5, .15);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(-1.7, -1.5, 1.7, -1.5);
			for (Ball3 i : ball3s) {
				i.plan(ball3s);
				i.draw();
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(i.rx, i.ry + i.radius + .1, "" + i.score);
			}
			for (Ball3 i : ball3s) {
				i.move();
			}
			StdDraw.show(37);
		}
	}
}
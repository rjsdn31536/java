package hw4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BounceBall extends Frame implements ActionListener {
	private Canvas canvas;
	public static ArrayList<Ball> b = new ArrayList<Ball>(5); // make parameter that saves balls as ArrayList

	public BounceBall() // Default constructor
	{
		canvas = new Canvas(); // make canvas
		add("Center", canvas); // put at center
		Panel p = new Panel(); // make panel
		Button s = new Button("Start"); // make button as start
		Button c = new Button("Close"); // make button as close
		p.add(s);
		p.add(c); // add buttons at panel
		s.addActionListener(this); // make button can work
		c.addActionListener(this); // make button can work
		add("South", p); // put at south
	}

	public void actionPerformed(ActionEvent evt) // action listener
	{
		if (evt.getActionCommand() == "Start") // if button is start
		{
			// if we already have ball, then remove balls.
			for (int i = 0; i < b.size(); i++) {
				b.get(i).interrupt(); // stop running thread
			}
			b.clear(); // clear ArrayList

			// make canvas be white
			Graphics g = canvas.getGraphics();
			g.setColor(getBackground());
			g.clearRect(0, 0, canvas.getSize().width, canvas.getSize().height);

			// make balls
			for (int i = 0; i < 5; i++) {
				b.add(new Ball(canvas));
				b.get(i).start(); // execute thread
			}
		} else if (evt.getActionCommand() == "Close") // if button is close
			System.exit(0); // quit program
	}

	public static void main(String[] args) {
		Frame f = new BounceBall(); // make frame
		f.setSize(400, 300); // set frame size
		f.addWindowListener(new WindowAdapter() { // activate "x"button of window
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true); // visible at screen
	}
}

class Ball extends Thread {
	private Canvas box;
	private int XSIZE; // ball's radius
	private int YSIZE; // ball's radius
	private int x; // ball's x position at canvas
	private int y; // ball's y position at canvas
	private double rx; // ball's real x position
	private double ry; // ball's real x position
	private double theta = Math.PI * (Math.random() * 2); // to generate balls that goes to random direction
	private double dx = 2 * Math.cos(theta); // balls speed are 2 (2cos^2 + 2sin^2 = 2)
	private double dy = 2 * Math.sin(theta); // balls speed are 2
	private static int i = 0; // for not to collide at generate
	private boolean smallest = false; // for smallest ball flag
	Boolean flag = true; // flag that if ball can move

	public Ball() {
	} // default constructor

	public Ball(Canvas c) { // constructor as get canvas c
		// set default setting
		box = c;
		XSIZE = 20;
		YSIZE = 20;
		x = box.getWidth() / 2;
		y = box.getHeight() / 2;
		rx = x;
		ry = y;
	}

	// constructor if generate balls after collide
	public Ball(Canvas c, int x, int y, int xsize, double theta) {
		// default setting
		this.x = x;
		this.y = y;
		this.XSIZE = xsize;
		this.YSIZE = xsize;
		this.theta = theta;
		dx = 2 * Math.cos(theta);
		dy = 2 * Math.sin(theta);
		rx = this.x;
		ry = this.y;
		box = c;
	}

	// draw balls at canvas
	public void draw() {
		i = 0;
		Graphics g = box.getGraphics();
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}

	// move balls at canvas
	public void move() {
		Graphics g = box.getGraphics();

		// remove previous ball
		g.setXORMode(box.getBackground());
		g.fillOval(x, y, XSIZE, YSIZE);
		// set position after moving
		rx += dx;
		ry += dy;
		x = (int) rx;
		y = (int) ry;
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		} // if ball collides at left wall
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		} // if ball collides at right wall
		if (y < 0) {
			y = 0;
			dy = -dy;
		} // if ball collides at upper wall
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		} // if ball collides at downward wall

		// check if ball collides with another ball
		for (int j = 0; j < BounceBall.b.size(); j++) {
			Ball target = BounceBall.b.get(j);
			// checking ball must not be itself
			if (j != BounceBall.b.indexOf(this) && collidecheck(target)) {
				collide(this, target);
				break;
			}
		}
		// to draw ball
		g.setPaintMode();
		g.fillOval(x, y, XSIZE, YSIZE);
		g.dispose();
	}

	// Thread
	public void run() {
		draw(); // call draw method
		if (smallest) { // if smallest ball, move a little and remove it
			for (int k = 0; k < 10; k++) {
				move();
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					return;
				}
			}
			BounceBall.b.remove(this);

			if (BounceBall.b.size() <= 1)
				System.exit(0); // ball remains under 1, quit program
		}

		while (this.flag) {
			i++;
			move(); // call move method
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
			if (BounceBall.b.size() <= 1)
				System.exit(0); // ball remains under 1, quit program
		}
	}

	private Boolean collidecheck(Ball target) {
		// get distance between two balls.(distance between core of ball)
		int distX = this.x - target.x;
		int distY = this.y - target.y;
		double dist = Math.sqrt((distX * distX) + (distY * distY));
		if (i <= 500)
			return false; // if i under 500, it doesn't collide
		if (dist <= (this.XSIZE + target.XSIZE) / 2)
			return true; // if distance is less than sum of two ball's radius then they collides
		return false; // if they doesn't collide, return false
	}

	private void collide(Ball b1, Ball b2) { // split two balls into small two balls each.
		spliteach(b1, b2.theta); // split b1's ball into two small balls. angle is b2's angle +- pi/4
		spliteach(b2, b1.theta); // split b2's ball into two small balls. angle is b1's angle +- pi/4
	}

	private void spliteach(Ball b, double theta) { // split into two ball
		double inc = theta + Math.PI / 4; // set angle
		double dec = theta - Math.PI / 4; // set angle
		if (inc >= Math.PI * 2)
			inc -= Math.PI * 2; // if upper ball's angle is over 2*pi, then set under 2*pi
		if (dec < 0)
			dec += Math.PI * 2; // if lower ball's angle is below 0, then set angle to over 0
		Ball new1 = new Ball(b.box, b.x, b.y, b.XSIZE / 2, inc); // make small ball
		Ball new2 = new Ball(b.box, b.x, b.y, b.XSIZE / 2, dec); // make small ball
		BounceBall.b.add(new1); // add to ArrayList
		BounceBall.b.add(new2); // add to ArrayList
		b.flag = false; // let collided ball doesn't move(quit thread)
		if (b.XSIZE / 2 <= 1) {// if ball's radius is lower than 1, then it is smallest ball.
			new1.smallest = true;
			new1.flag = false; // smallest ball doesn't go into while loop
			new2.smallest = true;
			new2.flag = false; // smallest ball doesn't go into while loop
		}
		b.interrupt();
		Graphics g = box.getGraphics();
		g.setXORMode(box.getBackground());
		g.fillOval(b.x, b.y, b.XSIZE, b.YSIZE);// remove ball's paint
		BounceBall.b.remove(b); // remove at ArrayList
		i = 480; // let new balls not to be collide immediately
		new1.start(); // start thread
		new2.start(); // start thread
	}
}

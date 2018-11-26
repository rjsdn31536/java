package hw4;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class BounceThread extends Frame implements ActionListener {
	private Canvas canvas;
	private static ArrayList<Ball> ball_list = new ArrayList<Ball>();
	private static int num_ball = 0;
	private static Semaphore sema;

	public BounceThread() {
		canvas = new Canvas_m();
		add("Center", canvas);
		Panel p = new Panel();
		Button s = new Button("Start");
		Button c = new Button("Close");
		p.add(s);
		p.add(c);
		s.addActionListener(this);
		c.addActionListener(this);
		add("South", p);
		sema = new Semaphore(1);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand() == "Start") {
			for (int i = 0; i < 5; i++) {
				ball_list.add(new Ball(canvas, 20));
				ball_list.get(ball_list.size() - 1).x += 10 * ball_list.get(ball_list.size() - 1).dx;
				ball_list.get(ball_list.size() - 1).y += 10 * ball_list.get(ball_list.size() - 1).dy;
				ball_list.get(ball_list.size() - 1).start();
			}
		} else if (evt.getActionCommand() == "Close")
			System.exit(0);
	}

	public static void main(String[] args) {
		Frame f = new BounceThread();
		f.setSize(400, 300);
		WindowDestroyer listener = new WindowDestroyer(); 
		f.addWindowListener(listener);
		f.setVisible(true);
	}

	class Canvas_m extends Canvas {
		public Canvas_m() {
			super();
		}

		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			for (int i = 0; i < ball_list.size(); i++) {
				if (ball_list.get(i).XSIZE >= 2)
					g.fillOval(ball_list.get(i).x, ball_list.get(i).y, ball_list.get(i).XSIZE, ball_list.get(i).YSIZE);
			}
		}
	}

	class Ball extends Thread {
		private Canvas box;
		public int XSIZE = 20;
		public int YSIZE = 20;
		public int x = 0;
		public int y = 0;
		public int dx = 2;
		public int dy = 2;

		public Ball(Canvas c, int size) {
			num_ball++;
			box = c;
			XSIZE = YSIZE = size;
			x = box.getWidth() / 2;
			y = box.getHeight() / 2;
			dx = (int) (Math.random() * 6) - 3;
			dy = (int) (Math.random() * 6) - 2;
			if (dx == 0)
				dx = 3;
			if (dy == 0)
				dy = -3;
		}

		public void move() {
			x += dx;
			y += dy;
			Dimension d = box.getSize();
			if (x < 0) {
				x = 0;
				dx = -dx;
			}
			if (x + XSIZE >= d.width) {
				x = d.width - XSIZE;
				dx = -dx;
			}
			if (y < 0) {
				y = 0;
				dy = -dy;
			}
			if (y + YSIZE >= d.height) {
				y = d.height - YSIZE;
				dy = -dy;
			}
		}

		public void collision() {
			for (int i = 0; i < ball_list.size(); i++) {
				int d_square = ((x + dx) - (ball_list.get(i).x + ball_list.get(i).dx))
						* ((x + dx) - (ball_list.get(i).x + ball_list.get(i).dx))
						+ ((y + dy) - (ball_list.get(i).y + ball_list.get(i).dy))
								* ((y + dy) - (ball_list.get(i).y + ball_list.get(i).dy));
				int r_square = (XSIZE / 2 + ball_list.get(i).XSIZE / 2) * (XSIZE / 2 + ball_list.get(i).XSIZE / 2);
				if ((!Ball.currentThread().equals(ball_list.get(i))) && (XSIZE >= 2) && (ball_list.get(i).XSIZE >= 2)
						&& (d_square < r_square) && (ball_list.size() <= 5050)) {
					XSIZE /= 2;
					YSIZE /= 2;
					dx = -dx;
					dy = -dy;
					ball_list.get(i).XSIZE /= 2;
					ball_list.get(i).YSIZE /= 2;
					ball_list.get(i).dx = -ball_list.get(i).dx;
					ball_list.get(i).dy = -ball_list.get(i).dy;

					ball_list.add(new Ball(canvas, XSIZE));
					ball_list.get(ball_list.size() - 1).x = ((x + ball_list.get(i).x) / 2) + 16 * (-dx);
					ball_list.get(ball_list.size() - 1).y = ((y + ball_list.get(i).y) / 2)
							+ 16 * (-ball_list.get(i).dx);
					ball_list.get(ball_list.size() - 1).dx = -dx;
					ball_list.get(ball_list.size() - 1).dy = -ball_list.get(i).dx;
					ball_list.get(ball_list.size() - 1).start();

					ball_list.add(new Ball(canvas, XSIZE));
					ball_list.get(ball_list.size() - 1).x = ((x + ball_list.get(i).x) / 2)
							+ 16 * (-ball_list.get(i).dy);
					ball_list.get(ball_list.size() - 1).y = ((y + ball_list.get(i).y) / 2) + 16 * (-dy);
					ball_list.get(ball_list.size() - 1).dx = -ball_list.get(i).dy;
					ball_list.get(ball_list.size() - 1).dy = -dy;
					ball_list.get(ball_list.size() - 1).start();
				}
			}
		}

		public void run() {
			while (true) {
				try {
					sema.acquire();
				} catch (InterruptedException e) {
				}
				move();
				collision();
				if (XSIZE < 2 && YSIZE < 2) {
					num_ball--;
					if (num_ball == 0)
						System.exit(0);
					sema.release();
					break;
				} else {
					sema.release();
				}
				box.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
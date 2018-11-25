package chap6;

import java.awt.*;
import java.awt.event.*;

public class evTukSuKey implements KeyListener {
	private Frame f;
	private int x = 250, y = 150;

	public evTukSuKey() {
		f = new Frame("KeyEvent Test");
		f.setBackground(Color.white);
		f.addKeyListener(this);
		f.setSize(200, 200);
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		int l = 10;
		char c = e.getKeyChar();
		int keyCode = e.getKeyCode();
		int modifiers = e.getModifiers();
		Graphics g = f.getGraphics();
		if (keyCode == 16) // VK_SHIFT
			l += 10;
		if (keyCode == 17) // VK_CONTROL
			l += 20;
		switch (keyCode) {
		case 38:
			y -= l;
			break; // VK_UP
		case 40:
			y += l;
			break;// VK_DOWN
		case 37:
			x -= l;
			break; // VK_LEFT
		case 39:
			x += l;
			break; // VK_RIGHT
		case 35:
			System.exit(0);
		}
		g.fillOval(x, y, l, l); // draw oval
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		evTukSuKey mf = new evTukSuKey();
	}
}

package chap6;

import java.awt.*;
import java.awt.event.*;

public class evKbd implements KeyListener {
	private Frame f;

	public evKbd() {
		f = new Frame("KeyEvent Test");
		f.setBackground(Color.white);
		f.addKeyListener(this);
		f.setSize(200, 200);
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}


	public void keyTyped(KeyEvent e) {
		Graphics g = f.getGraphics();
		g.setFont(new Font("바탕체", Font.PLAIN, 20));
		g.clearRect(0, 0, 200, 200); // delete privious printed value
		g.drawString("키 : " + e.getKeyChar(), 30, 100);
		g.drawString("키코드 : " + (int) e.getKeyChar(), 30, 130);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

//---------------------------------------------------------------------
	public static void main(String[] args) {
		evKbd mf = new evKbd(); // frame construction
	}
}
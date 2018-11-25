package chap6;

import java.awt.*;
import java.awt.event.*;

public class TwoListen implements MouseMotionListener, MouseListener {
	private TextField tf;
	private Frame f;
	private String button;

	public TwoListen() {
		f = new Frame("Two mouse listeners example");
		f.add(new Label("Click and drag the mouse"), BorderLayout.NORTH);
		tf = new TextField(100);
		f.add(tf, BorderLayout.SOUTH);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);
		f.setSize(400, 300);
		WindowDestroyer listener = new WindowDestroyer(); // window destroy button
		f.addWindowListener(listener);
		f.setVisible(true);
	}

	public void mouseDragged(MouseEvent e) {
		String s = "Mouse " + button + " dragging: X = " + e.getX() + " Y = " + e.getY();
		tf.setText(s);
	}

	public void mouseEntered(MouseEvent e) {
		String s = "The mouse Entered";
		tf.setText(s);
	}

	public void mouseExited(MouseEvent e) {
		String s = "The mouse has left the building";
		tf.setText(s);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// 어느 버튼인지 확인
		int whatButton = e.getButton();
		if (whatButton == 1)
			button = "left";
		else
			button = "right";
	}

	public static void main(String args[]) {
		TwoListen two = new TwoListen();
	}
}

package chap5;

import java.awt.*;

public class grLineRect extends Frame {
	public grLineRect(String str) {
		super(str);
	}

	public void paint(Graphics g) {
		g.drawLine(10, 30, 400, 30); // line
		g.drawRect(10, 50, 80, 200); // rectangle
		g.fillRect(110, 50, 80, 200); // colored rectangle
		g.drawRoundRect(200, 50, 80, 200, 10, 30); // round corner rectangle
		g.fillRoundRect(300, 50, 80, 200, 30, 10);
	} // colored rectangle

	public static void main(String[] args) {
		Frame frm = new grLineRect("선/사각형"); // frame construction
		frm.setSize(500, 300); // size of the frame : 500*300(pixel)
		WindowDestroyer listener = new WindowDestroyer(); // window destroy button
		frm.addWindowListener(listener);
		frm.setVisible(true); // indication of frame
	}
}
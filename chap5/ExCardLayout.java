package chap5;

import java.awt.*;
import java.awt.event.*;

public class ExCardLayout implements MouseListener {
	private CardLayout myCard = new CardLayout();
	private Frame f;

	public ExCardLayout() {
		f = new Frame("Card Test");
		f.setLayout(myCard);
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		Panel p4 = new Panel();
		Label lb1 = new Label("This is the first Panel");
		p1.setBackground(Color.yellow);
		p1.add(lb1);
		Label lb2 = new Label("This is the second Panel");
		p2.setBackground(Color.green);
		p2.add(lb2);
		Label lb3 = new Label("This is the third Panel");
		p3.setBackground(Color.white);
		p3.add(lb3);
		Label lb4 = new Label("This is the fourth Panel");
		p4.setBackground(Color.blue);
		p4.add(lb4);
		p1.addMouseListener(this);
		p2.addMouseListener(this);
		p3.addMouseListener(this);
		p4.addMouseListener(this);
		f.add(p1, "First");
		f.add(p2, "Second");
		f.add(p3, "Third");
		f.add(p4, "Fourth");
		myCard.show(f, "First");
		f.setSize(200, 200);
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}

	public void mousePressed(MouseEvent e) {
		myCard.next(f);
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public static void main(String args[]) {
		ExCardLayout mf = new ExCardLayout();
	}
}
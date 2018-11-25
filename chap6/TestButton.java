package chap6;

import java.awt.*;

import chap5.WindowDestroyer;

public class TestButton {
	public static void main(String args[]) {
		Frame f = new Frame("Test");
		Button b = new Button("Press Me");
		b.addActionListener(new ButtonHandler());
		f.add(b, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		WindowDestroyer listener = new WindowDestroyer(); // window destroy button
		f.addWindowListener(listener);
		f.setVisible(true); // indication of frame
	}
}

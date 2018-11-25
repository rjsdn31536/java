package chap5;

import java.awt.*;

public class ExBorderLayout extends Frame {
	public ExBorderLayout(String str) {
		super(str);
	}

	public static void main(String args[]) {
		ExFlowLayout f = new ExFlowLayout("Simple BorderLayout Example");
		f.setLayout(new BorderLayout());
		Button bn = new Button("Bn");
		Button bs = new Button("Bs");
		Button be = new Button("Be");
		Button bw = new Button("Bw");
		Button bc = new Button("Bc");
		f.add(bn, BorderLayout.NORTH);
		f.add(bs, BorderLayout.SOUTH);
		f.add(be, BorderLayout.EAST);
		f.add(bw, BorderLayout.WEST);
		f.add(bc, BorderLayout.CENTER);
		f.setSize(300, 300);
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}
}
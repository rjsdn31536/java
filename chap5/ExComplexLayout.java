package chap5;

import java.awt.*;

public class ExComplexLayout extends Frame {
	public ExComplexLayout(String str) {
		super(str);
	}

	public static void main(String args[]) {
		ExComplexLayout f = new ExComplexLayout("Complex Layout Example");
		f.setLayout(new BorderLayout());
		Button bw = new Button("West");
		Button bc = new Button("WorkSpace Region");
		f.add(bw, BorderLayout.WEST);
		f.add(bc, BorderLayout.CENTER);
		Panel p = new Panel();
		Button bfile = new Button("File");
		Button bhelp = new Button("Help");
		p.add(bfile);
		p.add(bhelp);
		f.add(p, BorderLayout.NORTH);
		f.pack();
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}
}
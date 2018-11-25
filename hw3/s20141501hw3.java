package hw3;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class s20141501hw3 {
	private Frame f;

	private JTextField tf1;
	private JTextField tf2;
	private String calc = "";
	private String calc_space = "";
	private String origin = "";
	private String result = "";

	public s20141501hw3() {
		f = new Frame("Calculator");
		f.setLayout(new BorderLayout());

		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(2, 1));
		tf1 = new JTextField(50);
		tf1.addKeyListener(new NameHandler());
		tf1.setHorizontalAlignment(JTextField.RIGHT);
		tf1.setBorder(BorderFactory.createLineBorder(Color.white));
		tf2 = new JTextField(50);
		tf2.addKeyListener(new NameHandler());
		tf2.setHorizontalAlignment(JTextField.RIGHT);
		tf2.setBorder(BorderFactory.createLineBorder(Color.white));
		p2.add(tf1, BorderLayout.NORTH);
		p2.add(tf2, BorderLayout.SOUTH);

		Panel p = new Panel();
		p.setLayout(new GridLayout(4, 5));
		Button b7 = new Button("7");
		b7.setForeground(Color.blue);
		Button b8 = new Button("8");
		b8.setForeground(Color.blue);
		Button b9 = new Button("9");
		b9.setForeground(Color.blue);
		Button bDiv = new Button("/");
		bDiv.setForeground(Color.red);
		Button bC = new Button("C");
		bC.setForeground(Color.red);
		Button b4 = new Button("4");
		b4.setForeground(Color.blue);
		Button b5 = new Button("5");
		b5.setForeground(Color.blue);
		Button b6 = new Button("6");
		b6.setForeground(Color.blue);
		Button bMul = new Button("*");
		bMul.setForeground(Color.red);
		Button bBack = new Button("<-");
		bBack.setForeground(Color.red);
		Button b1 = new Button("1");
		b1.setForeground(Color.blue);
		Button b2 = new Button("2");
		b2.setForeground(Color.blue);
		Button b3 = new Button("3");
		b3.setForeground(Color.blue);
		Button bMi = new Button("-");
		bMi.setForeground(Color.red);
		Button bL = new Button("(");
		bL.setForeground(Color.red);
		Button b0 = new Button("0");
		b0.setForeground(Color.blue);
		Button bDot = new Button(".");
		bDot.setForeground(Color.red);
		Button bEq = new Button("=");
		bEq.setForeground(Color.red);
		Button bPl = new Button("+");
		bPl.setForeground(Color.red);
		Button bR = new Button(")");
		bR.setForeground(Color.red);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(bDiv);
		p.add(bC);

		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(bMul);
		p.add(bBack);

		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(bMi);
		p.add(bL);

		p.add(b0);
		p.add(bDot);
		p.add(bEq);
		p.add(bPl);
		p.add(bR);
		b7.addActionListener(new ButtonHandler());
		b8.addActionListener(new ButtonHandler());
		b9.addActionListener(new ButtonHandler());
		bDiv.addActionListener(new ButtonHandler());
		bC.addActionListener(new ButtonHandler());
		b4.addActionListener(new ButtonHandler());
		b5.addActionListener(new ButtonHandler());
		b6.addActionListener(new ButtonHandler());
		bMul.addActionListener(new ButtonHandler());
		bBack.addActionListener(new ButtonHandler());
		b1.addActionListener(new ButtonHandler());
		b2.addActionListener(new ButtonHandler());
		b3.addActionListener(new ButtonHandler());
		bMi.addActionListener(new ButtonHandler());
		bL.addActionListener(new ButtonHandler());
		b0.addActionListener(new ButtonHandler());
		bDot.addActionListener(new ButtonHandler());
		bEq.addActionListener(new ButtonHandler());
		bPl.addActionListener(new ButtonHandler());
		bR.addActionListener(new ButtonHandler());

		f.add(p2, BorderLayout.NORTH);
		f.add(p, BorderLayout.SOUTH);
		f.pack();
		WindowDestroyer listener = new WindowDestroyer();
		f.addWindowListener(listener);
		f.setVisible(true);
	}

	// return -1000.10001 : ERROR!
	public double calculate(ArrayList listA) {

		while (listA.size() > 1) {
			try {
				double a = (double) listA.get(0);
				double b = (double) listA.get(2);
			} catch (IndexOutOfBoundsException e) {
				result = "ERROR!";
				return -1000.10001;
			} catch (ClassCastException e) {
				result = "ERROR!";
				return -1000.10001;
			}

			double a = (double) listA.get(0);
			double b = (double) listA.get(2);
			if (listA.get(1).equals("+")) {
				listA.remove(0);
				listA.remove(0);
				listA.remove(0);
				listA.add(0, a + b);
			} else if (listA.get(1).equals("-")) {
				listA.remove(0);
				listA.remove(0);
				listA.remove(0);
				listA.add(0, a - b);
			} else if (listA.get(1).equals("*")) {
				listA.remove(0);
				listA.remove(0);
				listA.remove(0);
				listA.add(0, a * b);
			} else if (listA.get(1).equals("/")) {
				listA.remove(0);
				listA.remove(0);
				listA.remove(0);
				listA.add(0, a / b);
			} else {
				listA.add(0, -1000.10001);
				break;
			}
		}

		try {
			double return_value = (double) listA.get(0);
		} catch (IndexOutOfBoundsException e) {
			result = "ERROR!";
			return -1000.10001;
		} catch (ClassCastException e) {
			result = "ERROR!";
			return -1000.10001;
		}

		return (double) listA.get(0);
	}

	// return 0 : 숫자가 아님
	// return 1 : 숫자임
	// return -1 : ERROR(ex : 12.34.56)
	static int isNumber(String str) {
		char tempCh;
		int dotCount = 0;
		int result = 1;

		for (int i = 0; i < str.length(); i++) {
			tempCh = str.charAt(i);
			if (str.length() != 1 && ((int) tempCh == (int) '+' || (int) tempCh == (int) '-')) {
			} else if ((int) tempCh < 48 || (int) tempCh > 57) {
				if (tempCh != '.') {
					result = 0;
					break;
				} else if (dotCount > 0) {
					result = -1;
					break;
				} else {
					dotCount++;
				}
			}
		}

		return result;
	}

	class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String s = e.getActionCommand();

			// ERROR인 경우 다음 계산에서 초기화해주는 과정
			if (result.equals("ERROR!")) {
				calc = "";
				origin = "";
				calc_space = "";
				result = "";
			}

			// 결과값을 return해준 후에 새롭게 시작함
			if (calc.contains("=")) {
				calc = "";
				origin = "";
				calc_space = "";
				result = "";
			}

			if (s == "C") {
				calc = "";
				origin = "";
				calc_space = "";
				result = "";
			} else if (s == "<-") {
				int origin_len = origin.length();

				if (origin_len == 0) {
				}

				else if (origin.substring(origin_len - 1, origin_len).equals("+")
						| origin.substring(origin_len - 1, origin_len).equals("-")
						| origin.substring(origin_len - 1, origin_len).equals("*")
						| origin.substring(origin_len - 1, origin_len).equals("/")) {
					origin = origin.substring(0, origin_len - 1);
					calc_space = calc_space.substring(0, calc_space.length() - 3);
					calc = calc.substring(0, calc.length() - 3);
				}

				else if (origin.substring(origin_len - 1, origin_len).equals("(")
						| origin.substring(origin_len - 1, origin_len).equals(")")) {
					origin = origin.substring(0, origin_len - 1);
					calc_space = calc_space.substring(0, calc_space.length() - 3);
					calc = calc.substring(0, calc.length() - 2);
				} else {
					origin = origin.substring(0, origin_len - 1);
					calc_space = calc_space.substring(0, calc_space.length() - 1);
					calc = calc.substring(0, calc.length() - 1);
				}
			} else if (s == "=") {
				if (origin.length() == 0)
					result = "ERROR!";
				else {
					origin = origin + s;
					s = " " + s;
					calc = calc + s;
					String[] values = calc_space.split("\\s+");
					ArrayList listA = new ArrayList();

					for (int i = 0; i < values.length; i++) {
						if (values[i].equals("")) {
						} else if (isNumber(values[i]) == 1) {
							listA.add(Double.parseDouble(values[i]));
						} else if (isNumber(values[i]) == 0) {
							listA.add(values[i]);
						} else if (isNumber(values[i]) == -1) {
							result = "ERROR!";
							break;
						}
					}

					int l_par = 0;
					int r_par = 0;
					int flag = 1;
					int par_flag = 0;
					int l_num = 0;
					int r_num = 0;

					if (result.equals("ERROR!")) {
					} else {

						while (listA.size() > 0) {
							if (result.equals("ERROR!"))
								break;

							if (flag == 2 | flag == 0)
								break;
							for (int i = 0; i < listA.size(); i++) {
								if (listA.get(i).equals("(")) {
									l_par = i;
									par_flag = 1;
									l_num += 1;

								} else if (listA.get(i).equals(")")) {
									ArrayList listB = new ArrayList();
									r_par = i;
									r_num += 1;

									// CASE. ')'가 '('보다 많이 나오는 경우, 먼저 나오는 경우
									if (r_num > l_num) {
										result = "ERROR!";
										break;
									}

									for (int j = l_par + 1; j < r_par; j++) {
										listB.add(listA.get(j));
									}
									for (int j = l_par; j < r_par + 1; j++) {
										listA.remove(l_par);
									}

									listA.add(l_par, calculate(listB));
									break;
								}

								if (i == listA.size() - 1 & r_par == 0) {
									flag = 0;
									break;
								} else if (i == listA.size() - 1) {
									flag = 2;
									break;
								}
							}
						}

						// (개수와 )개수가 다른 경우
						if (l_num != r_num) {

						}

						double result_double = 0.0;
						int result_int = 0;

						if (result.equals("ERROR!")) {
						} else if (flag == 2 & par_flag == 0) {
							listA.remove(0);
							listA.remove(listA.size());
							result_double = calculate(listA);
							result_int = (int) result_double;

						} else if (flag == 0) {
							result_double = calculate(listA);
							result_int = (int) result_double;
						} else {
							result_double = calculate(listA);
							result_int = (int) result_double;
						}

						if (result.equals("ERROR!")) {
						}
						// CASE 1. 결과값이 double인 경우
						else if (result_double - result_int != 0.0) {
							result = Double.toString(result_double);
						}

						// CASE 2. 결과값이 int인 경우
						else {
							result = Integer.toString(result_int);
						}
					}
				}

			} else if (s == "+" | s == "-" | s == "*" | s == "/") {
				int origin_len = origin.length();

				// 맨 처음부터 부호를 입력하는 경우
				if (origin_len == 0) {
					// 첫 부호가 +, -인 경우(가능함)
					if (s == "+" | s == "-") {
						origin = origin + s;
						calc = calc + s;
						calc_space = calc_space + s;
					}
					// 첫 부호가 *, /인 경우(불가능)
					else {
						result = "ERROR!";
					}
				} else {

					if (origin.substring(origin_len - 1, origin_len).equals("(")) {
						origin = origin + s;
						s = " " + s;
						calc = calc + s;
						calc_space = calc_space + s;
					} else {
						if (origin.substring(origin_len - 1, origin_len).equals("+")

								| origin.substring(origin_len - 1, origin_len).equals("-")
								| origin.substring(origin_len - 1, origin_len).equals("*")
								| origin.substring(origin_len - 1, origin_len).equals("/")) {
							origin = origin.substring(0, origin_len - 1);
							calc = calc.substring(0, calc.length() - 3);
							calc_space = calc_space.substring(0, calc_space.length() - 3);
						}

						origin = origin + s;
						s = " " + s + " ";
						calc = calc + s;
						calc_space = calc_space + s;
					}
				}
			} else if (s == "(") {
				origin = origin + s;
				s = " " + s;
				calc = calc + s;
				s = s + " ";
				calc_space = calc_space + s;

			} else if (s == ")") {
				origin = origin + s;
				s = s + " ";
				calc = calc + s;
				s = " " + s;
				calc_space = calc_space + s;
			} else {
				origin = origin + s;
				calc = calc + s;
				calc_space = calc_space + s;
			}
			tf1.setText(calc);

			tf2.setText(result);
		}
	}

	class TextHandler implements TextListener {
		public void textValueChanged(TextEvent e) {
			// TODO Auto-generated method stub
		}
	}

	class NameHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			e.consume();
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}
	}

	// ---------------------------------------------------------------------
	public static void main(String[] args) {
		s20141501hw3 mf = new s20141501hw3(); // frame construction
	}
}

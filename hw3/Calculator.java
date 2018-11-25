package hw3;
//----------------------------------------------//
//Java Assignment #2					        //
//Title : Engineer Calculator				    //
//Develop Period : '13. 10. 30 ~ '13. 11. 11   //
//Student No : 20091679					    //
//Student Name : Pyo Se Joon				    //
//----------------------------------------------//


import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;


public class Calculator
{
		// Calculator Frame
		private Frame f;
		// Expression Textfield
		private TextField tf;	
		// Main Panel
		private Panel mainPan;
		// Button Panel
		private Panel btnPan;
		// Display Panel
		private Panel dspPan;
		// Checkbox Panel
		private Panel chkPan;	
		// Store operand Stack value
		private Stack <Double> value = new Stack<Double>();
		// Store operator Stack operator 
		private Stack <String> operator = new Stack<String>();	

		// Declare variable to final result to Double type
		static double result = 0;
		// Declare variable to know result value is integer type or Double type
		static int temp_result = 0;
		// Declare variable to push operand to value stack 
		double midresult = 0;
		// Declare variable to display text in textfield
		static String input = "0";
		// Declare variable to final result to String type
		static String finalresult ="";
		static int i = 0;
		static int j = 0;

		Button b1_1;
		Button b1_2;
		Button b1_3;
		Button b1_4;
		Button b1_5;
		Button b1_6;
		Button b2_1;
		Button b2_2;
		Button b2_3;
		Button b2_4;
		Button b2_5;
		Button b2_6;
		Button b3_1;
		Button b3_2;
		Button b3_3;
		Button b3_4;
		Button b3_5;
		Button b3_6;
		Button b4_1;
		Button b4_2;
		Button b4_3;
		Button b4_4;
		Button b4_5;
		Button b4_6;
		Button b5_1;
		Button b5_2;
		Button b5_3;
		Button b5_4;
		Button b5_5;
		Button b5_6;
		
		CheckboxGroup chkg;
		Checkbox chk1;
		Checkbox chk2;
		Checkbox chk3;
		Checkbox chk4;
		
		// Create Method to sort operator's priority //
		// priority is refered by [Fundamental of Data Structure in C 2nd edition]
		public int precedence(String token)
		{
			int prior = 0;
			if(token.equals("(") || token.equals(")"))
				prior = 17;
			else if(token.equals("^"))
				prior = 7;
			else if(token.equals("*") || token.equals("/"))
				prior = 12;
			else if(token.equals("+") || token.equals("-")||token.equals("%"))
				prior = 13;
			else
				prior = 18;

			return prior;
		}
		
		// Using Stack, calculate expression of value and operator.
		// To calculate similiar to postfix evaluation. 
		// evalution must be shown in decimal.
		// negative operand calculating must be required parentheses.
		// e.g :  3 * -2 is not correct. 3*(-2) = -6 is operating.
		// e.g : -(-3*2) is not correct. -1*(-3*2) = 6 is operating.
		public double clc_alu(String input)
		{
			String token;
			// Push operand and operator through lparen['('], rparen[')']      
			// Totally expression should be added up to initial parentheses one more.
			input = "(" + input + ")"; 

			// To use StringTokenizer, each operand is distinguished, and specify 'sep'
			StringTokenizer sep = new StringTokenizer(input, "()*/+-^%", true);
			
			// Decide to sep is in expression, looping until sep has no value.
			while(sep.hasMoreTokens())
			{
				
				// If token is operator, push operator Stack.
				token = sep.nextToken();
			
				if(token.equals("(")){
					operator.push(token);
				}


				// If token is operand, push value Stack.
				if(!token.equals("(")&& !token.equals(")")&&!token.equals("+")&& !token.equals("-")&& !token.equals("*")&& !token.equals("/")&& !token.equals("^")&& !token.equals("%")){
					midresult = Double.parseDouble(token);
					value.push(midresult);
				}

				
				// Until found left parenthesis pushed in Stack, calculating operand using operator in parenthsis.
				if(token.equals(")")){
					while(operator.peek().equals("(") == false){
						String expr = (String) operator.peek();
						operator.pop();
						Double operand1 = (double) value.peek();
						value.pop();
						Double operand1_temp = operand1;
						Double operand2 = (double) value.peek();
						value.pop();
						Double operand2_temp = operand2;

						switch(expr)
						{
							case "^":
								result = Math.pow(operand2_temp,operand1_temp);	break;
							case "+":
								result = operand1_temp + operand2_temp;	break;
							case "-":
								result = operand2_temp - operand1_temp;	break;
							case "*":
								result = operand1_temp * operand2_temp;	break;
							case "/":
								result = operand2_temp / operand1_temp;	break;
							case "%":
								result = operand2_temp % operand1_temp; break;
						}
						value.push(result);
					}
					operator.pop();
				}
				// if token is not parenthsis, calculting operend by operator priority until Stack is empty.
				if(token.equals("*")||token.equals("/")||token.equals("+")||token.equals("-")||token.equals("^")||token.equals("%")){
					
					// compare priority of the operand to the others in operator Stack.
					while(operator.isEmpty()==false && precedence(operator.peek())<=precedence(token))
					{
						String expr = (String) operator.peek();
						operator.pop();
						Double operand1 = (double) value.peek();
						value.pop();
						Double operand1_temp = operand1;
						Double operand2 = (double) value.peek();
						value.pop();
						Double operand2_temp = operand2;

						switch(expr)
						{
							case "^":
								result = Math.pow(operand2_temp,operand1_temp);	break;
							case "+":
								result = operand1_temp + operand2_temp;	break;
							case "-":
								result = operand2_temp - operand1_temp;	break;
							case "*":
								result = operand1_temp * operand2_temp;	break;
							case "/":
								result = operand2_temp / operand1_temp;	break;
							case "%":
								result = operand2_temp % operand1_temp; break;

						}

						value.push(result);
					}
					operator.push(token);
				}
			}
			result = (double) value.peek();
			return result;
		}

		
		public Calculator(){
			
			f = new Frame("공학용 계산기 20091679_표세준");
			mainPan = new Panel(new BorderLayout());

			tf = new TextField(44);
			tf.setText("0");
			chkPan = new Panel();
			dspPan = new Panel();
			btnPan = new Panel(new GridLayout(5,6,2,2));

		
			
			b1_1 = new Button(" C ");
			b1_2 = new Button("back");
			b1_3 = new Button(" ");
			b1_4 = new Button("sin");
			b1_4.setForeground(Color.red);
			b1_5 = new Button("cos");
			b1_5.setForeground(Color.red);
			b1_6 = new Button("tan");
			b1_6.setForeground(Color.red);

			b2_1 = new Button("7");
			b2_1.setFont(new Font("gothic",Font.BOLD, 13));
			b2_1.setForeground(Color.blue);
			b2_2 = new Button("8");
			b2_2.setFont(new Font("gothic",Font.BOLD, 13));
			b2_2.setForeground(Color.blue);
			b2_3 = new Button("9");
			b2_3.setFont(new Font("gothic",Font.BOLD, 13));
			b2_3.setForeground(Color.blue);
			b2_4 = new Button("(");
			b2_5 = new Button(")");
			b2_6 = new Button("^");

			b3_1 = new Button("4");
			b3_1.setFont(new Font("gothic",Font.BOLD, 13));
			b3_1.setForeground(Color.blue);
			b3_2 = new Button("5");
			b3_2.setFont(new Font("gothic",Font.BOLD, 13));
			b3_2.setForeground(Color.blue);
			b3_3 = new Button("6");
			b3_3.setFont(new Font("gothic",Font.BOLD, 13));
			b3_3.setForeground(Color.blue);
			b3_4 = new Button("!");
			b3_5 = new Button("%");
			b3_5.setFont(new Font("gothic",Font.ITALIC, 13));
			b3_6 = new Button("+");
			b3_6.setFont(new Font("gothic",Font.ITALIC, 13));
			b3_6.setForeground(Color.red);

			b4_1 = new Button("1");
			b4_1.setFont(new Font("gothic",Font.BOLD, 13));
			b4_1.setForeground(Color.blue);
			b4_2 = new Button("2");
			b4_2.setFont(new Font("gothic",Font.BOLD, 13));
			b4_2.setForeground(Color.blue);
			b4_3 = new Button("3");
			b4_3.setFont(new Font("gothic",Font.BOLD, 13));
			b4_3.setForeground(Color.blue);
			b4_4 = new Button("-");
			b3_6.setFont(new Font("gothic",Font.ITALIC, 13));
			b4_4.setForeground(Color.red);
			b4_5 = new Button("*");
			b4_5.setFont(new Font("gothic",Font.ITALIC, 13));
			b4_5.setForeground(Color.red);
			b4_6 = new Button("/");
			b4_6.setFont(new Font("gothic",Font.ITALIC, 13));
			b4_6.setForeground(Color.red);

			b5_1 = new Button(".");
			b5_1.setFont(new Font("gothic",Font.BOLD, 13));
			b5_2 = new Button("0");
			b5_2.setFont(new Font("gothic",Font.BOLD, 13));
			b5_2.setForeground(Color.blue);
			b5_3 = new Button("=");
			b5_4 = new Button("ln");
			b5_5 = new Button("root");
			b5_6 = new Button(" ");

			btnPan.add(b1_1); btnPan.add(b1_2); btnPan.add(b1_3); btnPan.add(b1_4); btnPan.add(b1_5); btnPan.add(b1_6);
			btnPan.add(b2_1); btnPan.add(b2_2); btnPan.add(b2_3); btnPan.add(b2_4);	btnPan.add(b2_5); btnPan.add(b2_6);
			btnPan.add(b3_1); btnPan.add(b3_2); btnPan.add(b3_3); btnPan.add(b3_4);	btnPan.add(b3_5); btnPan.add(b3_6);
			btnPan.add(b4_1); btnPan.add(b4_2); btnPan.add(b4_3); btnPan.add(b4_4); btnPan.add(b4_5); btnPan.add(b4_6);
			btnPan.add(b5_1); btnPan.add(b5_2); btnPan.add(b5_3); btnPan.add(b5_4); btnPan.add(b5_5); btnPan.add(b5_6);

			chkg = new CheckboxGroup();
			chk1 = new Checkbox("Hex",chkg, false);
			chk2 = new Checkbox("Dec",chkg, true);
			chk3 = new Checkbox("Oct",chkg, false);
			chk4 = new Checkbox("Bin",chkg, false);
			chkPan.setLayout(new FlowLayout());
			
			chkPan.add(chk1);
			chkPan.add(chk2);
			chkPan.add(chk3);
			chkPan.add(chk4);
			
			chk1.addItemListener(new ItemHandler());	// hexadecimal
			chk2.addItemListener(new ItemHandler());	// decimal
			chk3.addItemListener(new ItemHandler());	// octal
			chk4.addItemListener(new ItemHandler());	// binary
			
			b5_2.addActionListener(new EventHandler());	// 0
			b4_1.addActionListener(new EventHandler()); // 1
			b4_2.addActionListener(new EventHandler()); // 2
			b4_3.addActionListener(new EventHandler()); // 3
			b3_1.addActionListener(new EventHandler()); // 4
			b3_2.addActionListener(new EventHandler()); // 5
			b3_3.addActionListener(new EventHandler()); // 6
			b2_1.addActionListener(new EventHandler()); // 7
			b2_2.addActionListener(new EventHandler()); // 8
			b2_3.addActionListener(new EventHandler()); // 9

			b1_1.addActionListener(new EventHandler()); // clear
			b1_2.addActionListener(new EventHandler()); // back
			
			b1_4.addActionListener(new EventHandler()); // sin
			b1_5.addActionListener(new EventHandler()); // cos
			b1_6.addActionListener(new EventHandler()); // tan
			b2_4.addActionListener(new EventHandler()); // lparen
			b2_5.addActionListener(new EventHandler()); // rparen
			b2_6.addActionListener(new EventHandler()); // power
			b3_4.addActionListener(new EventHandler()); // factorial
			b3_5.addActionListener(new EventHandler()); // modular
			b3_6.addActionListener(new EventHandler()); // plus
			b4_4.addActionListener(new EventHandler()); // minus
			b4_5.addActionListener(new EventHandler()); // multiple
			b4_6.addActionListener(new EventHandler()); // divide
			b5_1.addActionListener(new EventHandler()); // point(dot)
			b5_3.addActionListener(new EventHandler()); // equal
			b5_4.addActionListener(new EventHandler()); // ln
			b5_5.addActionListener(new EventHandler()); // root

			f.setSize(350,260);
			f.setBackground(new Color(235,235,235));
			
			//attach main Panel to frame.
			f.add(mainPan, BorderLayout.CENTER);
			
			//attach textfield to Display panel.
			dspPan.add(tf);	
			
			//attach each Panel without main paenl to main panel.
			mainPan.add(dspPan, BorderLayout.NORTH);
			mainPan.add(btnPan, BorderLayout.CENTER);
			mainPan.add(chkPan, BorderLayout.SOUTH);

			WindowDestroyer listener = new WindowDestroyer();
			f.addWindowListener(listener);
			f.setVisible(true);
			tf.setBackground(Color.white);
			tf.setEnabled(false);
			tf.setFont(new Font("gothic",Font.BOLD, 12));
			
		}

		public static class WindowDestroyer extends WindowAdapter{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}


		class ItemHandler implements ItemListener
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					if(e.getItem()== "Hex"){
						// check finalresult has value, if not value in finalresult that means state is input enter state.
						if(finalresult=="")
							i = Integer.parseInt(input);
						else
							i = Integer.parseInt(finalresult);

						String hex = Integer.toHexString(i);
						tf.setText(hex);
					}

					else if(e.getItem()=="Dec"){
						if(finalresult=="")
							i = Integer.parseInt(input);
						else
							i = Integer.parseInt(finalresult);

						String dec = Integer.toString(i);
						tf.setText(dec);
					}

					else if(e.getItem()=="Oct"){
						if(finalresult=="")
							i = Integer.parseInt(input);
						else
							i = Integer.parseInt(finalresult);

						String oct = Integer.toOctalString(i);
						tf.setText(oct);
					}

					else if(e.getItem()=="Bin"){
						if(finalresult=="")
							i = Integer.parseInt(input);
						else
							i = Integer.parseInt(finalresult);

						String bin = Integer.toBinaryString(i);
						tf.setText(bin);
					}
				}
			}
		}

		// Press button to act each event
		// Attach the textfield to StringType( Number(0~9), plus, minus, multiple, divide, lparen, rparen, etc.)
		// or Press Button and perform calculating(clear, backspace, =, root, ln, sin, cos, tan, etc.)
		// '=' calls clc_alu method that actually calculating and manage stack.
		class EventHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(e.getSource().equals(b5_2))
				{
					// 0
					if(input.length()==1 && input.equals("0"))
						input = "";
					input += '0';
					tf.setText(input);

				}

				else if(e.getSource().equals(b4_1))
				{
					// 1
					if(input.length()==1 && input.equals("0"))
						input = "";
					input += '1';
					tf.setText(input);
					
				}

				else if(e.getSource().equals(b4_2))
				{
					// 2
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='2';
					tf.setText(input);

				}

				else if(e.getSource().equals(b4_3))
				{
					// 3
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='3';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b3_1))
				{
					// 4
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='4';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b3_2))
				{
					// 5
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='5';
					tf.setText(input);
				}				
				
				else if(e.getSource().equals(b3_3))
				{
					// 6
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='6';
					tf.setText(input);
				}

				else if(e.getSource().equals(b2_1))
				{
					// 7
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='7';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b2_2))
				{
					// 8
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='8';
					tf.setText(input);
				}

				else if(e.getSource().equals(b2_3))
				{
					// 9
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='9';
					tf.setText(input);
				}
				
				
				else if(e.getSource().equals(b2_4))
				{
					//lparen//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input+='(';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b2_5))
				{
					//rparen//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input+=')';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b2_6))
				{
					//power//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input+='^';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b3_5))
				{
					//modular//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input+='%';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b3_6))	
				{
					//plus//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='+';
					tf.setText(input);
				}
				
				else if(e.getSource().equals(b4_4))
				{
					//minus//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='-';
					tf.setText(input);
				}

				else if(e.getSource().equals(b4_5))
				{
					//multiple//
					if(input.length()==1 && input.equals("0"))
						input = "";
					input +='*';
					tf.setText(input);
				}

				else if(e.getSource().equals(b4_6))
					{
						if(input.length()==1 && input.equals("0"))
							input = "";
						input +='/';
						tf.setText(input);
					}
				
				else if(e.getSource().equals(b5_1))
				{
					//point(dot)
					if(input.length()==1 && input.equals("0")){
						input ="0.";
						tf.setText(input);
					}
					else{
						input +='.';
						tf.setText(input);
					}

				}
				// special function in calc(clear, backspace, =, root, ln, sin, cos, tan, etc.)
				else if(e.getSource().equals(b1_1))
				{
					//clear//
					input = "0";
					finalresult="";
					// To empty Stack saved the result in Stack.
					while(value.isEmpty()==false)
						value.pop();	

					tf.setText(input);
				}
				
				else if(e.getSource().equals(b1_2))
				{
					//back//
					if(input.length()>0){
						if(input.length()==1){
							input="0";
						}
						else{
							input = input.substring(0,input.length()-1);
						}
					}
					else
						input = "0";
					tf.setText(input);
				}
	
				else if(e.getSource().equals(b1_4))
				{
					//sin//
					// sin, cos, tan has parameter type of radien.
					if(value.isEmpty()==false){
						result = value.peek();
						result = Math.sin(result);
						finalresult = Double.toString(result);
						tf.setText(finalresult);
					}
					else{
						input = String.valueOf(Math.sin(Double.parseDouble(input)));
						tf.setText(input);
					}
				}
				
				else if(e.getSource().equals(b1_5))
				{
					//cos//
					if(value.isEmpty()==false){
						result = value.peek();
						result = Math.cos(result);
						finalresult = Double.toString(result);
						tf.setText(finalresult);
					}
					else{
						input = String.valueOf(Math.cos(Double.parseDouble(input)));
						tf.setText(input);
					}
				}
				
				else if(e.getSource().equals(b1_6))
				{
					//tan//
					if(value.isEmpty()==false){
						result = value.peek();
						result = Math.tan(result);
						finalresult = Double.toString(result);
						tf.setText(finalresult);
					}
					else{
						input = String.valueOf(Math.tan(Double.parseDouble(input)));
						tf.setText(input);
					}
				}
				
				else if(e.getSource().equals(b3_4))
				{
					//factorial//
					int tmp =0;
					if(value.isEmpty()==false){
						result = value.peek();
						j=Integer.parseInt(String.valueOf(Math.round(result)));

						if((result-j)!=0){
							finalresult = "Not Integer";
							tf.setText(finalresult);
						}
						tmp = j;							
						for(i=tmp-1; i>0; i--)
							j*=i;

						finalresult=Integer.toString(j);
						tf.setText(finalresult);
					}
					else{
						j=Integer.parseInt(input);
						System.out.println(j);
						tmp = j;
						for(i=tmp-1; i>0; i--)
							j*=i;
						finalresult=Integer.toString(j);
						tf.setText(finalresult);
					}


				}
				
				else if(e.getSource().equals(b5_3))
				{
					// =
					input = tf.getText();
					result = clc_alu(input);		
					temp_result = Integer.parseInt(String.valueOf(Math.round(result)));
					if((result - temp_result) == 0)
						finalresult = Integer.toString(temp_result);
					else
						finalresult = Double.toString(result);
					tf.setText(finalresult);

				}
				
				else if(e.getSource().equals(b5_4))
				{
					//ln//
					if(value.isEmpty()==false){
						result = value.peek();
						result = Math.log(result);
						finalresult = Double.toString(result);
						tf.setText(finalresult);
					}
					else{
						input = String.valueOf(Math.log(Double.parseDouble(input)));
						tf.setText(input);
					}
				}

				else if(e.getSource().equals(b5_5))
				{
					//root//
					if(value.isEmpty()==false){
						result = value.peek();
						result = Math.sqrt(result);
						finalresult = Double.toString(result);
						tf.setText(finalresult);
					}
				
					else{
						input = String.valueOf(Math.sqrt(Double.parseDouble(input)));
						tf.setText(input);
					}

				}

			}
		}

		public static void main(String args[]){
			Calculator cs = new Calculator();
		}
}
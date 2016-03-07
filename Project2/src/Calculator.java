//@author Ben King

import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Calculator {
	MyQueue<Object> postfix;
	MyStack<String> opStack;
	MyStack<Double> numStack;
	Scanner in;
	String[] operators;
	Hashtable<String, Integer> precedence;
	boolean lastWasOp = true; //b/c preceded by nothing would be a unary minus
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		
		Calculator calc = new Calculator(args[0], args[1]);
	}
	
	public Calculator(String input, String output) throws FileNotFoundException, UnsupportedEncodingException{
		postfix = new MyQueue<>();
		opStack = new MyStack<>();
		numStack = new MyStack<>();
		operators = new String[]{"+","-","*","/","^","(",")","<",">","=","&","|","!","%","sin","cos","tan"};
		precedence= new Hashtable<>();
		//order of operations, 1 is highest precedence, taken roughly from https://en.wikipedia.org/wiki/Order_of_operations
		precedence.put("(",-1);
		precedence.put(")",-1);
		precedence.put("sin(",0);
		precedence.put("cos(",0);
		precedence.put("tan(",0);
		precedence.put("!", 1 + 1);//b/c right-asssociative
		precedence.put("^", 2 + 1);
		precedence.put("*", 3);//unary minus = 3; less than ^
		precedence.put("%", 3);
		precedence.put("/", 3);
		precedence.put("+", 4);
		precedence.put("-", 4);
		precedence.put("<", 5);
		precedence.put(">", 5);
		precedence.put("=", 6);
		precedence.put("|", 7);
		precedence.put("&", 8);
		
		
		PrintWriter out = new PrintWriter(output, "UTF-8");
		
		try {
			Scanner fileScan = new Scanner(new File(input));
			while(fileScan.hasNextLine()){
				String s = fileScan.nextLine();
				
				//condense
				String expression = "";
				Scanner condenser = new Scanner(s);//get rid of spaces so sin (x) works
				while(condenser.hasNext()){
					expression += condenser.next();//bad bad
				}
				
				Double solve = evaluate(expression);
				out.printf("%.2f\n", solve);
				
				
				
				
				//reset just in case
				postfix = new MyQueue<>();
				opStack = new MyStack<>();
				numStack = new MyStack<>();
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		

			out.close();
		
		
		
		
		
		
		
		
	}
	
	
	public Double evaluate(String exp){
		parseString(exp);
		while(!opStack.isEmpty())
			postfix.enqueue(opStack.pop());
		
		Double result = postfixCalculate();
		
		//2 dec places
		return result;

	}
	
	
	
	
	void parseString(String s){
		for(int i = 0; i < s.length(); i++){
			//parse conventional operands
			if(arrContains(operators, s.substring(i,i+1))){
				
				String c = s.substring(i,i+1);
				if(c.equals(")")){ 
					while(!opStack.peek().equals("(") && !opStack.peek().equals("sin(") && !opStack.peek().equals("cos(") && !opStack.peek().equals("tan(")){
						postfix.enqueue(opStack.pop());
					}
					//if its a trig					
					if(!opStack.peek().equals("(")){
						postfix.enqueue(opStack.pop());
					}//if its a paren
					else{
						opStack.pop();
					}
					
				}else if(c.equals("-")){//unary minus special case
					if(lastWasOp){//unary minus case
						while(!opStack.isEmpty() && !opStack.peek().equals("(") && !opStack.peek().equals("sin(") && !opStack.peek().equals("cos(") && !opStack.peek().equals("tan(") && precedence.get(opStack.peek()) <= 3){
							postfix.enqueue(opStack.pop());
						}
						postfix.enqueue(new Double(0));
						opStack.push("-");
					}else{
						while(!opStack.isEmpty() && !opStack.peek().equals("(") && !opStack.peek().equals("sin(") && !opStack.peek().equals("cos(") && !opStack.peek().equals("tan(") && precedence.get(opStack.peek()) <= precedence.get(c)){
							postfix.enqueue(opStack.pop());
						}
						opStack.push(c);
					}
					
					
				}else{
					//middle condition b/c ( should not be popped even though it has lower precedence
					while(!opStack.isEmpty() && !opStack.peek().equals("(") && !opStack.peek().equals("sin(") && !opStack.peek().equals("cos(") && !opStack.peek().equals("tan(") && precedence.get(opStack.peek()) <= precedence.get(c)){
						postfix.enqueue(opStack.pop());
					}
					opStack.push(c);
					
					
				}
				
				lastWasOp=true;
				
			}//parse sin | cos | tan
			else if(s.substring(i,i+1).equals("c")|| s.substring(i,i+1).equals("t") || s.substring(i,i+1).equals("s")){
				String c = s.substring(i, i+4);
				opStack.push(c);
				i+=3;
				lastWasOp=true;
			}//parse numbers
			else{
	
				Double d = 0.0;
				boolean dec = false;
				double decCounter = 10;
				String c = s.substring(i, i+1);
				while(Character.isDigit(c.charAt(0)) || c.equals("."))
				{
					if(c.equals(".")){
						dec = true;
					}
					else if(dec){
						d+=Double.parseDouble(c)/decCounter;
						decCounter*=10;
					}else{
						d*=10;
						d+=Double.parseDouble(c);
					}
					i++;
					if(i >= s.length()){
						//postfix.enqueue(new Double(d));
						break;
					}
					c = s.substring(i,i+1);
				}
				i--;
				postfix.enqueue(new Double(d));
				lastWasOp=false;
			}
		}
	}
	
	boolean arrContains(String[] ops, String s){
		for(String t:ops){
			if(t.equals(s)){
				return true;
			}
		}
		return false;
	}

	double postfixCalculate(){
		
		Object c;
		while(!postfix.isEmpty()){
		c = postfix.dequeue();

		if(c instanceof Double)
			numStack.push((Double)c);
		else{
			String op = (String)c;
			switch (op){//"%","sin","cos","tan"
				case "+":
					numStack.push(numStack.pop() + numStack.pop());
					break;
				case "-":
					{
					double b = numStack.pop();
					double a = numStack.pop();
					numStack.push(a - b);
					break;}
				case "/":{
					double b= numStack.pop();
					double a = numStack.pop();
					numStack.push( a/b);
					break;}
				case "*":
					numStack.push(numStack.pop() * numStack.pop());
					break;
				case "^":{
					double b= numStack.pop();
					double a = numStack.pop();
					numStack.push(Math.pow(a , b));
					break;}
				case "<":{
					double b = numStack.pop();
					double a = numStack.pop();
					if(a < b){
						numStack.push(new Double(1));
					}else{
						numStack.push(new Double(0));
					}
					break;
					}
				case ">":{
					double b = numStack.pop();
					double a = numStack.pop();
					if(a > b){
						numStack.push(new Double(1));
					}else{
						numStack.push(new Double(0));
					}
					break;
					}
				case "=":{
					double b = numStack.pop();
					double a = numStack.pop();
					if(a == b){
						numStack.push(new Double(1));
					}else{
						numStack.push(new Double(0));
					}
					break;
					}
				case "&":{
					double b = numStack.pop();
					double a = numStack.pop();
					if(a ==0 || b ==0){
						numStack.push(new Double(0));
					}else{
						numStack.push(new Double(1));
					}
					break;
					}
				case "|":{
					double b = numStack.pop();
					double a = numStack.pop();
					if(a == 0 && b == 0){
						numStack.push(new Double(0));
					}else{
						numStack.push(new Double(1));
					}
					break;
					}
				case "!":{
					double a = numStack.pop();
					if(a == 0){
						numStack.push(new Double(1));
					}else{
						numStack.push(new Double(0));
					}
					break;
					}
				case "%":{
					double b = numStack.pop();
					double a = numStack.pop();
					numStack.push(a%b);
					break;
					}
				case "sin(":{
					double a = numStack.pop();
					numStack.push(Math.sin(a));
					break;
					}
				case "cos(":{
					double a = numStack.pop();
					numStack.push(Math.cos(a));
					break;
					}
				case "tan(":{
					double a = numStack.pop();
					numStack.push(Math.tan(a));
					break;
					}
			}// end switch

			}

		}
		
		return numStack.pop();
	} 
	
	
}

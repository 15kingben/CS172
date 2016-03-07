
public class StackTester {

	public static void main(String[] args){
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(9);
		System.out.println("pushing 9");
		stack.push(8);
		System.out.println("pushing 8");
		stack.push(7);
		System.out.println("pushing 7");
		stack.push(6);
		System.out.println("pushing 6");
		System.out.println("popping first element");
		System.out.println(stack.pop());
		System.out.println("popping first element");
		System.out.println(stack.pop());
		System.out.println("peeking first element");
		System.out.println(stack.peek());
		System.out.println("pushing 5");
		stack.push(5);
		System.out.println("popping first element");
		System.out.println(stack.pop());
		System.out.println("popping first element");
		System.out.println(stack.pop());
		System.out.println("popping first element");
		System.out.println(stack.pop());
		System.out.println("popping first element");
		System.out.println(stack.pop());
		
	}
}

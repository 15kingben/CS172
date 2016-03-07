//@author Ben King
//code largely copied from prev lab
public class QueueTester {
public static void main(String[] args){
	MyQueue<Integer> queue = new MyQueue<Integer>();
	queue.enqueue(9);
	System.out.println("enqueueing 9");
	queue.enqueue(8);
	System.out.println("enqueueing 8");
	queue.enqueue(7);
	System.out.println("enqueueing 7");
	queue.enqueue(6);
	System.out.println("enqueueing 6");
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
	System.out.println("peeking first element");
	System.out.println(queue.peek());
	System.out.println("enqueueing 5");
	queue.enqueue(5);
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
	System.out.println("dequeueing last element");
	System.out.println(queue.dequeue());
}
}
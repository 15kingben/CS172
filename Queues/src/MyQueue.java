//@author Ben King
public class MyQueue<T> implements Queue<T> {

	MyDoublyLinkedList<T> list = new MyDoublyLinkedList<>();
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(T x) {
		list.insert(x);
		
	}

	@Override
	public T dequeue() {
		return list.removeReturnEnd();
	}

	@Override
	public T peek() {
		if(list.isEmpty()){
			return null;
		}else{
			return list.head.next.data;
		}
	}

	
}

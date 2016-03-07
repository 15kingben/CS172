//@author Ben King
public class MyStack <T> implements Stack<T> {

	
	private MyLinkedList<T> list;
	
	public MyStack(){
		list = new MyLinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(T x) {
		list.insert(x);
	}

	@Override
	public T pop() {
		if(isEmpty())
			return null;
		else{
			T ret = list.first.data;
			list.first = list.first.next;
			return ret;
		}
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return list.first.data;
	}

	
}

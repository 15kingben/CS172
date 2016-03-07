//@author Ben King
public class MyDoublyLinkedList<AnyType> implements DoublyLinkedList<AnyType> {

	MyDoubleNode<AnyType> head;
	MyDoubleNode<AnyType> tail;
	
	
	public MyDoublyLinkedList(){
		head = new MyDoubleNode<AnyType>();
		tail = new MyDoubleNode<AnyType>();
		head.data = null;
		tail.data = null;
		head.prev = null;
		tail.next = null;
		head.next = tail;
		tail.prev = head;
	}
	
	@Override
	public void insert(AnyType x) {
		//create wrapper node for data
		if(lookup(x))
			return;
		MyDoubleNode<AnyType> newNode = new MyDoubleNode<>();
		newNode.data = x;
		newNode.next = head.next;
		head.next = newNode;
		newNode.prev = head;
		newNode.next.prev = newNode;
	}

	@Override
	public void delete(AnyType x) {
		MyDoubleNode<AnyType> next = head.next;
		while(next!= tail){
			if(next.data.equals(x)){
				next.prev.next = next.next;
				next.next.prev = next.prev;
			}
			next = next.next;
		}
	}

	@Override
	public boolean lookup(AnyType x) {
		MyDoubleNode<AnyType>next = head.next;
		while(next != tail){
			if(next.data.equals(x))
				return true;
			next = next.next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return head.next == tail;
	}

	@Override
	public void printList() {
		MyDoubleNode<AnyType>next = head.next;
		while(next != tail){
			System.out.print(next.data + " ");
			next = next.next;
		}
		System.out.println();
	}

	@Override
	//O(n) runtime
	public void printListRev() {
		MyDoubleNode<AnyType>prev = tail.prev;
		while(prev != head){
			System.out.print(prev.data + " ");
			prev = prev.prev;
		}
		System.out.println();
	}

	
	

}

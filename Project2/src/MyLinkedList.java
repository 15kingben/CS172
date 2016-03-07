//@author Ben King
//question 3
public class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	//head of list
	public MyNode<AnyType> first;
	
	//constructor: initialize head to null
	public MyLinkedList (){
		first = null;
	}
	
	
	//Always insert at head of list
	@Override
	public void insert(AnyType x) {
		MyNode<AnyType> newX = new MyNode<AnyType>();
		//if(lookup(x)) //this kills the calculator
		//	return;
		newX.data = x;
		newX.next = first;
		first = newX;		
	}

	//will delete multiple instances of same item
	@Override
	public void delete(AnyType x) {
		//special cases of empty list or element at start of list
		if(first == null)
			return;
		while(first.data.equals(x)){
			first = first.next;
		}
		
		MyNode<AnyType> prev = first;
		MyNode<AnyType> next = first.next;
		while(next != null){
			if(next.data.equals(x)){
				prev.next = next.next;
				next = next.next;
			}else{
				next = next.next;
				prev = prev.next;
			}			
		}
		
	}

	@Override
	public boolean lookup(AnyType x) {
		MyNode<AnyType> next = first;
		while(next != null){
			if(next.data.equals(x)){
				return true;
			}
			next = next.next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (first == null);
	}

	//linear time algorithm, although slower than an array data type
	@Override
	public void printList() {
		MyNode<AnyType> next = first;
		while(next != null){
			System.out.print(next.data + " ");
			next = next.next;
		}
		System.out.println();
	}

	
	
	
}
 
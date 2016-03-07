//@author Ben King
public interface DoublyLinkedList <AnyType>{

	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
}
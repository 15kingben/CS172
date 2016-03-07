//@author Ben King
public class Tester {

	public static void main(String[] args) {
		MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<>();
		list.insert(9);
		list.insert(3);
		list.insert(9);
		list.insert(9);
		list.insert(2);
		list.insert(1);
		list.delete(9);
		
		list.printList();
		list.printListRev();
	}

}


public class Tester {
	
	public static void main(String[] args) {
		MyBST<Integer> tree = new MyBST<>();
		System.out.println("Insert: 9,7,5,14,11,10,8\n\n");
		
		
		tree.insert(9);
		tree.insert(7);
		tree.insert(5);
		tree.insert(14);
		tree.insert(11);
		tree.insert(10);
		tree.insert(8);
		
		//dupes
		tree.insert(14);
		tree.insert(11);
		tree.insert(10);
		tree.insert(8);
		
		
		System.out.println("Pre");
		tree.printPreOrder();
		System.out.println();
		System.out.println("Post");
		tree.printPostOrder();
		System.out.println();
		System.out.println("In");
		tree.printInOrder();
		
		
		System.out.println("\n\nDelete: 7, 14, 10\n\n");
		tree.delete(7);//2 children
		tree.delete(14);//1 child
		tree.delete(10);//no children
		
		System.out.println("Pre");
		tree.printPreOrder();
		System.out.println();
		System.out.println("Post");
		tree.printPostOrder();
		System.out.println();
		System.out.println("In");
		tree.printInOrder();
		
	}
}
//@author Ben King


public class HeapTester {

	public static void main(String[] args) {
		MyHeap<Integer> heap = new MyHeap<Integer>(1);
		System.out.println(heap.isEmpty());

		heap.insert(5);
		heap.insert(9);
		heap.insert(1);
		heap.insert(2);
		heap.insert(6);
		heap.insert(2);
		heap.insert(7);
		heap.insert(3);
		heap.printHeap();
		System.out.println();
		System.out.println();
		heap.deleteMin();
		heap.printHeap();
		System.out.println();
		System.out.println();

		heap.deleteMin();
		heap.printHeap();
		
		System.out.println();
		System.out.println();
		System.out.println();
		MyHeap<Integer> heap2 = new MyHeap<Integer>(new Integer[]{9,1,4,2,7,3,5,14,6,67,11,22});
		heap2.printHeap();
	}

}

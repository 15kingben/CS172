public class MyTreeNode<T extends Comparable<T>> {
	public T data;
	public MyTreeNode<T> leftChild;
	public MyTreeNode<T> rightChild;
	public MyTreeNode<T> parent;
}
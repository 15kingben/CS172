
public class MyBST<T extends Comparable<T>> implements BST<T> {

	MyTreeNode<T> root;
	public int preCount;
	
	
	public MyBST(){
		
	}
	
	@Override
	public void insert(T x) {
		if(root == null){
			root = new MyTreeNode<T>();
			root.data = x;
			return;
		}
		
		if(lookup(x))
			return;
		
		rInsert(x, root, null, false);
	}
	
	private void rInsert(T x, MyTreeNode<T> node, MyTreeNode<T> parent, boolean left){
		if(node == null){
			node = new MyTreeNode<T>();
			node.data = x;
			node.parent = parent;
			if(left)
				parent.leftChild = node;
			else
				parent.rightChild = node;
			return;
		}
		
		if(x.compareTo(node.data) > 0){
			rInsert(x,node.rightChild, node, false);
		}else{
			rInsert(x, node.leftChild, node, true);
		}
	}
	
	
	

	@Override
	public void delete(T x) {
		
		if(root ==null)
			return;
		rDelete(root, x);
		
		
	}
	
	private void rDelete(MyTreeNode<T> node, T x){
		if(node == null)
			return;
		if(node.data == x)
			deleteNode(node);
		rDelete(node.leftChild, x); 
		rDelete(node.rightChild, x);
	}

	private void deleteNode(MyTreeNode<T> node) {
		//case 1
		if(node.leftChild == null && node.rightChild == null){
			if(node.parent.leftChild == node)
				node.parent.leftChild = null;
			else
				node.parent.rightChild = null;
		}
		
		//case 2
		else if((node.leftChild == null )!= (node.rightChild == null)){
			
			MyTreeNode<T> newNode;
			if(node.leftChild == null)
				newNode = node.rightChild;
			else
				newNode= node.leftChild;
			
			if(node.parent.leftChild == node)
				node.parent.leftChild = newNode;
			else
				node.parent.rightChild = newNode;
		}
		
		//case 3
		else{
			MyTreeNode<T> replace = findLeftChild(node.rightChild);
			node.data = replace.data;
			deleteNode(replace);//must be case 1 or 2 so no infinite recursion
		}
		
		
	}

	private MyTreeNode<T> findLeftChild(MyTreeNode<T> node) {
		//node wont ever be null if its called by delete()
		
		if(node.leftChild == null)
			return node;
		return findLeftChild(node.leftChild);
	}

	@Override
	public boolean lookup(T x) {
		return rLookup(root, x);
	}
	
	private boolean rLookup(MyTreeNode<T> node, T x){
		if(node == null)
			return false;
		if(node.data == x)
			return true;
		return rLookup(node.leftChild, x) || rLookup(node.rightChild, x);
		
	}

	@Override
	public void printPreOrder() {
		rPre(root);

	}

	@Override
	public void printInOrder() {
		rIn(root);
	}

	@Override
	public void printPostOrder() {
		rPost(root);
		
	}

	private void rIn(MyTreeNode<T> node){
		
		if(node == null){
			return;
		}
		rIn(node.leftChild);
		System.out.print(" " + node.data + " ");
		rIn(node.rightChild);
		

	}
	
	private void rPost(MyTreeNode<T> node){
		if(node==null){
			return;
		}
		rPost(node.leftChild);
		rPost(node.rightChild);
		System.out.print(" " + node.data + " " );
	}
	
	private void rPre(MyTreeNode<T> node){
		if(node==null){
			return;
		}
		System.out.print(" " + node.data + " " );
		rPre(node.leftChild);
		rPre(node.rightChild);
	}
}

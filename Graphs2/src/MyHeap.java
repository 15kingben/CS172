//@author Ben King

import java.util.ArrayList;

public class MyHeap <T extends Comparable<T>> implements Heap<T>{

	T[] items;
	int currentSize;
	int defaultCapacity;
	
	
	public MyHeap(){
		defaultCapacity = 10 + 1;
		items = (T[]) new Comparable[defaultCapacity];//Weiss 24
		currentSize = 1;
	}
	
	public MyHeap(int size){
		defaultCapacity = size + 1;
		items = (T[]) new Comparable[defaultCapacity];//Weiss 24
		currentSize = 1;
	}
	
	public MyHeap(T[] newItems){
		currentSize = 1;
		defaultCapacity = newItems.length + 1;
		items = (T[]) new Comparable[defaultCapacity];//Weiss 24
		for(int i = 0; i < defaultCapacity - 1; i++){
			items[currentSize++] = newItems[i];
		}
		
		heapify();
	}
	
	
	//From buildHeap() Weiss pg. 236
	private void heapify(){
		for(int i = currentSize / 2; i > 0; i--){
			bubbleDown(i);
		}
	}
	
	@Override
	public void insert(T item) {
		
		if(currentSize >= defaultCapacity){
			defaultCapacity = (2*(defaultCapacity-1)) +1;
			cpArray(defaultCapacity);
		}
		
		items[currentSize++] = item;
		bubbleUp(currentSize - 1);
	}
	
	private void cpArray(int newSize){
		T[] newItems = (T[]) new Comparable[defaultCapacity];
		for(int i = 0; i < items.length; i++){
			newItems[i] = items[i];
		}
		items= newItems;
	}
	
	private void bubbleUp(int index){
		if(index == 1)
			return;
		if(items[index/2
		         ].compareTo(items[index]) > 0 ){
			T temp = items[index];
			items[index] = items[index/2];
			items[index/2] = temp;
			bubbleUp(index/2);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize <= 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

	@Override
	public T deleteMin() {
		T temp = items[0];
		items[0] = items[--currentSize];
		bubbleDown(0);
		
		
		return temp;
	}
	
	public void bubbleDown(int index){
		
		T left = null;
		T right = null;
		
		if(2*index < currentSize)
			left = items[2*index];
		if(2*index + 1 < currentSize)
			right = items[2*index + 1];
		
		int less = -1;
		
		if(left != null && right != null)
			less = (left.compareTo(right) < 0) ? 2*index : 2*index+1;
		else if(left != null)
			less = 2*index;
		else if(right != null)
			less = 2*index  +1;
		
		if(less == -1)
			return;
		
		if(items[less].compareTo(items[index]) < 0 ){
			T temp = items[index];
			items[index] = items[less];
			items[less] = temp;
		}
		bubbleDown(less);
		
	}
	
	public void printHeap(){
		for(int i= 1; i < currentSize; i++){
			
			
			System.out.print(items[i] + " ");
		}
		
	}

}

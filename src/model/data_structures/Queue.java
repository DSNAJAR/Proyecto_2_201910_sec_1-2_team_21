package model.data_structures;

import java.util.Iterator;

public class Queue<T extends Comparable<T>> implements IQueue<T> {

	
	class Node <T extends Comparable<T>>
	{
		public T item;
		
		public Node<T> siguiente;
		
		public Node(T pItem)
		{
			this.item = pItem;
			
			this.siguiente = null;
		}
	}
	
	//--------------------------------------------------------------------------------------
	// Atributos
	//--------------------------------------------------------------------------------------
	
	/**
	 * Primer elemento de la cola
	 */
	private Node<T> first;
	
	/**
	 * Último elemento de la cola
	 */
	private Node<T> last;
	
	/**
	 * Tamaño de la cola
	 */
	private int size;
	
	//--------------------------------------------------------------------------------------
	// Constructor
	//--------------------------------------------------------------------------------------
	
	/**
	 * Crea una nueva cola vacía
	 */
	public Queue() {
		first = null;
		last = null;
		size = 0;
	}
	
	//--------------------------------------------------------------------------------------
	// Métodos
	//--------------------------------------------------------------------------------------
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	/**
	 * Clase que representa el iterador para la cola
	 */
	private class ListIterator implements Iterator<T> {
		
		private Node<T> current = first;
		
		public boolean hasNext() {
		 return current != null; 
		}
		
		public T next() {
			 T item = current.item;
			 current = current.siguiente;
			 return item;
		}
	 }

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return first == null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public void enqueue(T t) {
		// TODO Auto-generated method stub
		Node<T> oldLast = last;
		last = new Node<T>(t);
		last.siguiente = null;
		
		if(isEmpty()){
			first = last;
		}
		else {
			oldLast.siguiente = last;
		}
		
		size++;
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		T item = first.item;
		first = first.siguiente;
		
		if(!isEmpty()) {
			last = null;
			size--;
		}		
		
		return item;
	}
	
	public Node<T> getNodoFirst() { 
		return first;
	}
}

package model.data_structures;

import java.util.Iterator;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class MaxPQ <Key extends Comparable<Key>, T> extends Queue<T>{
	
	/**
	 * Primer nodo de la cola
	 */
	private NodoPriorityQueue<Key, T> primero;
	
	/**
	 * Último nodo de la cola
	 */
	private NodoPriorityQueue<Key, T> ultimo;
	
	/**
	 * Número de elementos
	 */
	private int N;
	
	public MaxPQ(int pN) {
		this.N = pN;
		this.primero = null;
		this.ultimo = null;
	}
	
	/**
	* Retorna el primer nodo de la cola. Sin eliminarlo
	* @return El primer nodo de la cola
	*/
	public NodoPriorityQueue<Key, T> darPrimero( )
	{
	return primero;
	}

	/**
	* Retorna el último nodo de la cola. Sin eliminarlo
	* @return El último nodo de la cola<}
	*/
	public NodoPriorityQueue<Key, T> darUltimo( )
	{
	return ultimo;
	}

	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<T>(primero);
	}
	
	 private class ListIterator<Item> implements Iterator<T> {
	        private NodoPriorityQueue<Key, T> current;

	        public ListIterator(NodoPriorityQueue<Key, T> first) {
	            current = first;
	        }

	        public boolean hasNext()  { return current != null;                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public T next() {
	            if (!hasNext()) return null;
	            T item = current.getItem();
	            current = current.getSiguiente(); 
	            return item;
	        }
	    }

	public void agregar(T item, Key prioridad) {
		// TODO Auto-generated method stub
		NodoPriorityQueue<Key, T> nuevo = new NodoPriorityQueue<Key, T>(prioridad, item);
		if(isEmpty()) {
			primero = nuevo;
			ultimo = nuevo;
		}
		else if(primero.getPriority().compareTo(nuevo.getPriority()) < 0) {
			nuevo.setSiguiente(primero);
			primero = nuevo;
		}
		else {
			NodoPriorityQueue<Key, T> aux = primero;
			while(aux != null) {
				if(aux.getSiguiente().getPriority().compareTo(nuevo.getPriority()) < 0) {
					aux.setSiguiente(nuevo);
					nuevo.setSiguiente(aux.getSiguiente());
				}
				aux = aux.getSiguiente();
			}
		}
		N++;
	}

	public T getMax() {
		// TODO Auto-generated method stub
		NodoPriorityQueue<Key, T> max = primero;
		if(primero.getSiguiente() != null) {
			N--;
			primero = max.getSiguiente();
			max.setSiguiente(null);
		}
		return max.getItem();
	}

	/**
	 * Indica si está vacío
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return N == 0;
	}

	/**
	 * Retorna el tamaño dela cola
	 */
	public int size() {
		// TODO Auto-generated method stub
		return N;
	}
}
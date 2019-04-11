package model.data_structures;

public class DoubleLinkedList <T extends Comparable<T>> implements ILinkedList{
	
	/**
	 * Primer nodo de la lista
	 */
	private Nodo<T> first;
	
	/**
	 * Ultimo nodo de la lista
	 */
	private Nodo<T> last;
	
	/**
	 * Tamanho de la lista
	 */
	private int size;
	
	//CONSTRUCTOR
	
	public DoubleLinkedList() {
		// TODO Auto-generated constructor stub
		first = null;
		last = null;
		size = 0;
	}
	
	/**
	 * Retorna el primer nodo
	 * @return el primer nodo
	 */
	public Nodo<T> getFirst() {
		if(estaVacia()) {
			return null;
		}
		else {
			return first;
		}
	}
	
	/**
	 * Retorna el ultimo nodo
	 * @return el ultimo nodo
	 */
	public Nodo<T> getLast() {
		if(estaVacia()) {
			return null;
		}
		else {
			return last;
		}
	}
	
	@Override
	public void agregar(Comparable item) {
		// TODO Auto-generated method stub
		if(estaVacia()) {
			addFirst(item);
		}
		else
			addLast(item);
	}
	
	/**
	 * Anhade un elemento a la lista en la primera posición
	 * @param item. Item a añadir a la lista.
	 */
	public void addFirst ( Comparable<T>  item ) {
		Nodo<T> nodo = new Nodo<T>((T) item);
		
		if( first == null ) {
			first = nodo;
			last = nodo;
		}
		else {
			nodo.cambiarSiguiente(first);
			first.cambiarAnterior(nodo);
			first = nodo;
		}
		size++;
	}
	/**
	 * Anhade un elemento a la lista en la ultima posisión.
	 * @param item. Item a añadir a la lista.
	 */
	public void addLast ( Comparable<T> item ) {
		Nodo<T> nodo = new Nodo<T>((T) item);
		
		if( first == null ) {
			first = nodo;
			last = nodo;
		}
		else {
			nodo.cambiarAnterior(last);
			last.cambiarSiguiente(nodo);
			last = nodo;
		}
		size++;
	}
	

	@Override
	public void eliminar(Comparable item) {
		// TODO Auto-generated method stub
		boolean eliminado = false;
		Nodo<T> nodo = getFirst();
		Nodo<T> anterior = null;
		Nodo<T> siguiente = null;
		
		while (eliminado && nodo.getSiguiente() != null && estaVacia()!=true)
		{
			if( nodo.getItem().equals(item))
			{
				eliminado = true;
				anterior = nodo.getrAnterior();
				siguiente = nodo.getSiguiente();
				
				anterior.cambiarSiguiente(siguiente);
				siguiente.cambiarAnterior(anterior);
				size--;
			}
			else {
				nodo = nodo.getSiguiente();
			}
		}
	}

	@Override
	public Comparable getItem(int numero) {
		// TODO Auto-generated method stub
		Nodo<T> x = first;
		int i = 1;
		
		if(estaVacia()) {
			while(i != numero) {
				x = x.getSiguiente(); 
			}
		}
		else {
			x =null;
		}
		
		return x.getItem();
	}

	@Override
	public int getTamanho() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean estaVacia() {
		// TODO Auto-generated method stub
		if(size != 0) {
			return false;
		}
		else {
			return true;
		}
	}

}

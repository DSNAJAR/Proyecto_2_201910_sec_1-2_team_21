package model.data_structures;

public class NodoHT <T extends Comparable <T>, key, Value> {
	
	//------------------------------------------------------------
	// ATRIBUTOS
	//------------------------------------------------------------
	private NodoHT<T, key, Value> next;

	private Object key;
	
	private Object val;

	
	//-------------------------------------------------------------
	// CONSTRUCTURES
	//-------------------------------------------------------------
	/**
	 * Constructor 
	 * @param item2 Objeto el cual vamos a usar
	 */
	public NodoHT (key llave, Value val, NodoHT<T, key, Value> nodo) {
		this.key = llave;
		this.val = val;
		this.next = nodo;
	}
	/**
	 * Devuelvo el siguiente de la lista
	 * @return Nodo siguiente
	 */
	public NodoHT<T, key, Value> getNext() {
		return next;
	}
	
	/**
	 * Devuelve la llave
	 * @return Object. Llave
	 */
	public Object getKey() {
		return  key;
	}
	
	/**
	 * Devuelve el valor
	 * @return Object. value
	 */
	public Object getValue() {
		return  val;
	}
	
	/**
	 * Inserta en la posicion siguiente de la lista
	 * @return Nodo siguiente
	 */
	public void setNext(NodoHT<T, key, Value> siguiente) {
		this.next = siguiente ;
	}
	
	/**
	 * Inserta el item en el nodo
	 * @param item. Item a insertar
	 */
	public void changeValue(Object item) {
		this.val = item;
	}
}

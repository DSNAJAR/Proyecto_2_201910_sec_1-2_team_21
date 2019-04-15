package model.data_structures;

public class NodoHT <T extends Comparable <T>, Key, Value> {
	
	//------------------------------------------------------------
	// ATRIBUTOS
	//------------------------------------------------------------
	private NodoHT<T, Key, Value> next;

	private Key key;
	
	private Value val;

	
	//-------------------------------------------------------------
	// CONSTRUCTURES
	//-------------------------------------------------------------
	/**
	 * Constructor 
	 * @param item2 Objeto el cual vamos a usar
	 */
	public NodoHT (Key llave, Value val, NodoHT<T, Key, Value> nodo) {
		this.key = llave;
		this.val = val;
		this.next = nodo;
	}
	/**
	 * Devuelvo el siguiente de la lista
	 * @return Nodo siguiente
	 */
	public NodoHT<T, Key, Value> getNext() {
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
	public void setNext(NodoHT<T, Key, Value> siguiente) {
		this.next = siguiente ;
	}
	
	/**
	 * Inserta el item en el nodo
	 * @param item. Item a insertar
	 */
	public void changeValue(Value item) {
		this.val = item;
	}
}

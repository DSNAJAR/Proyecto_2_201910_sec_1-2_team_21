package model.data_structures;

public class NodoHT <T, K, V> {
	
	//------------------------------------------------------------
	// ATRIBUTOS
	//------------------------------------------------------------
	private NodoHT<T, K, V> next;

	private K key;
	
	private V val;

	
	//-------------------------------------------------------------
	// CONSTRUCTURES
	//-------------------------------------------------------------
	/**
	 * Constructor 
	 * @param item2 Objeto el cual vamos a usar
	 */
	public NodoHT (K llave, V val, NodoHT<T, K, V> nodoSiguiente) {
		this.key = llave;
		this.val = val;
		this.next = nodoSiguiente;
	}
	/**
	 * Devuelvo el siguiente de la lista
	 * @return Nodo siguiente
	 */
	public NodoHT<T, K, V> getNext() {
		return next;
	}
	
	/**
	 * Devuelve la llave
	 * @return Object. Llave
	 */
	public K getKey() {
		return  key;
	}
	
	/**
	 * Devuelve el valor
	 * @return Object. value
	 */
	public V getValue() {
		return  val;
	}
	
	/**
	 * Inserta en la posicion siguiente de la lista
	 * @return Nodo siguiente
	 */
	public void setNext(NodoHT<T, K, V> siguiente) {
		this.next = siguiente ;
	}
	
	/**
	 * Inserta el item en el nodo
	 * @param item. Item a insertar
	 */
	public void changeValue(V item) {
		this.val = item;
	}
}

package model.data_structures;

/**
 * Nodo cola de prioridad
 * @param <Key> Tipo de elemento con el cual se le dará la prioridad al nodo de la cola
 * @param <T> Tipo de elemento que va a contener cada nodo de la cola
 */
public class NodoPriorityQueue<Key extends Comparable<Key>, T>
{
	private Key prioridad;
	
	private T item;
	
	private NodoPriorityQueue<Key, T> siguiente;
	
	public NodoPriorityQueue(Key pPrioridad, T pItem)
	{
		this.prioridad = pPrioridad;
		
		this.item = pItem;
		
		this.siguiente = null;
	}
	
	/**
	 * Devuelve el nodo siguiente
	 * @return Nodo siguiente
	 */
	public NodoPriorityQueue<Key, T> getSiguiente() 
	{
		return siguiente;
	}
	
	/**
	 * Método que inserta en la posición siguiente
	 * @param pSuiguiente
	 */
	public void setSiguiente ( NodoPriorityQueue<Key, T> pSiguiente) 
	{
		this.siguiente = pSiguiente;
	}
	
	/**
	 * Retorna el item que contiene el ndo
	 * @return item que contiene el nodo
	 */
	public T getItem()
	{
		return item;
	}
	
	/**
	 * Inserta el item en el nodo
	 * @param item. Item a insertar
	 */
	public void cambiarItem (T item) 
	{
		this.item = item;
	}
	
	/**
	 * Retorna la prioridad del item
	 * @return prioridad del item
	 */
	public Key getPriority() {
		return prioridad;
	}
}


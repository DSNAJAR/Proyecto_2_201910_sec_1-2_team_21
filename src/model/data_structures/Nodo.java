package model.data_structures;

public class Nodo <T extends Comparable<T>>
{
	private T item;
	
	private Nodo<T> siguiente;
	
	private Nodo<T> anterior;
	
	public Nodo(T pItem)
	{
		this.item = pItem;
		
		this.siguiente = null;
		
		this.anterior = null;
	}
	
	/**
	 * Devuelve el siguiente de la lista
	 * @return Nodo siguiente
	 */
	public Nodo<T> getSiguiente() 
	{
	return siguiente;
	}
	
	/**
	 * Devuelve el anterior de la lista
	 * @return Nodo anterior
	 */
	public Nodo<T> getrAnterior()
	{
		return anterior;
	}
	
	/**
	 * Método que inserta en la posición siguiente.
	 * @param next
	 */
	public void cambiarSiguiente ( Nodo<T> pSiguiente ) 
	{
		this.siguiente = pSiguiente ;
	}
	
	/**
	 * Méto que inserta un nodo en la posición anterior.
	 */
	public void cambiarAnterior ( Nodo<T> pAnterior )
	{
		this.anterior = pAnterior;		
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
}


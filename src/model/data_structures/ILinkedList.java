package model.data_structures;

public interface ILinkedList <T extends Comparable<T>>{
	
	/**
	 * Anhande el elmento a una posicion de la lista
	 * @param item Item a anhadir
	 */
	public void agregar(T item);
	
	/**
	 * Elimina un elemento de la lista dada por parametro
	 * @param item elemento a eliminar
	 */
	public void eliminar(T item);
	
	/**
	 * Retorna el elemento dado por parametro
	 * @param numeroposicion del 
	 * @return el elemento T
	 */
	public T getItem(int numero);
	
	/**
	 * Retorna el tamanho de lista 
	 * @return Tamanho de la lista
	 */
	public int getTamanho();
	
	/**
	 * Retorna si la lista esta vacia o no
	 * @return True si la lista esta Vacia y False si tiene algun elemento
	 */
	public boolean estaVacia();
}

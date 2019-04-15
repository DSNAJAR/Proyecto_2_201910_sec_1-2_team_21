package model.data_structures;

public interface ILinkedListHT <K, V>{
	
	/**
	 * Anhande una llave y su repectivo valor a una posicion de la lista
	 * @param key Llave a anhadir
	 * @param value Valor asociado a la llave
	 */
	public void put(K key, V value);
	
	/**
	 * Elimina la llave y el vallor de la lista
	 * @param llave del valor
	 */
	public void delete(K key);
	
	/**
	 * Retorna el asociado a la llave key
	 * @param numeroposicion del 
	 * @return value V
	 */
	public V get(K key);
	
	/**
	 * Retorna el tamanho de lista 
	 * @return Tamanho de la lista
	 */
	public int size();
	
	/**
	 * Retorna si la lista esta vacia o no
	 * @return True si la lista esta Vacia y False si tiene algun elemento
	 */
	public boolean isEmpty();
	
	/**
	 * La lista contiene la llave key
	 * @param key llave
	 * @return True si se encuentra la llave. False si no
	 */
	public boolean contains(K key);
}

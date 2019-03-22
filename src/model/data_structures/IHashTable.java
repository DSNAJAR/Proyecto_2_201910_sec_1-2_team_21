package model.data_structures;

public interface IHashTable<K extends Comparable<K>, V> extends Iterable<K>{
	
	/**
	 * Agregar una dupla K,V) a la tabla. Si la llave K existe, se reemplaza su valor V asociado. V no puede ser null.
	 * @param key - llave de la dupla.
	 * @param value - valor de la dupla.
	 */
	public void put(K key, V value);
	
	/**
	 * Obtener el valor V asociado a la llave K. V no puede ser null.
	 * @param key - Llave asociada al valor V.
	 * @return valor V.
	 */
	public V get(K key);
	
	/**
	 * Borrar la dupla asociada a la llave K. Se obtiene el valor V asociado a la llave K.
	 * @param key - Llave asociada al valor V.
	 * @return valor V. Se obtiene null si la llave K no existe.
	 */
	public V delete(K key);
}

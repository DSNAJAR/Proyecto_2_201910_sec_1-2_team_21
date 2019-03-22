package model.data_structures;

import java.util.Iterator;

public class LinearProbingHT <K extends Comparable<K>, V> implements IHashTable{
	
	private int N; // number of key-value pairs in the table
	private int M = 16; // size of linear-probing table
	private K[] keys; // the keys
	private V[] vals; // the values
	
	public LinearProbingHT(int pCapacidad) {
		// TODO Auto-generated constructor stub
		M = pCapacidad;
		N = 0;
		keys = (K[]) new Object[M];
		vals = (V[]) new Object[M];
	}
	
	/**
	 * Conjunto de llaves K presentes en la tabla.
	 */
	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		DoubleLinkedList<K> list = new DoubleLinkedList<K>();
        for (int i = 0; i < M; i++) {
        	if (keys[i] != null) list.agregar(keys[i]);
        }
             return (Iterator<K>) list;
	}

	@Override
	public void put(Comparable key, Object value) {
		// TODO Auto-generated method stub
		if (N/M > 0.75)
			resize(2*M); // double M (see text)
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
			{
				vals[i] = (V) value;
				return;
			}
		keys[i] = (K) key;
		vals[i] = (V) value;
		N++;
	}

	@Override
	public Object get(Comparable key) {
		// TODO Auto-generated method stub
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	@Override
	public Object delete(Comparable key) {
		// TODO Auto-generated method stub
		if(!contains(key)) return null;
		
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		while (keys[i] != null) {
			K keyToRedo = keys[i];
			V valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		N-- ;
		if (N > 0 && N == M/8) resize(M/2);
		return key;
	}
	
	private void resize(int capacity) {
		LinearProbingHT<K, V> temp = new LinearProbingHT<K, V>(capacity);
		
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M    = temp.M;
	}
	
	private int hash(Comparable key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public boolean contains(Comparable key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
}

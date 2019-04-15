package model.data_structures;

import java.util.Iterator;

public class SeparateChainingHT <K extends Comparable<K>, V> implements IHashTable{
	
	
	private int n;       // number of key-value pairs
    private int M;       // hash table size
	private NodoHT[] st = new NodoHT[M]; // array of chains
	
	public SeparateChainingHT( ) {
        this.M = view.MovingViolationsManagerView.N;
        st = new NodoHT[M];
    } 
	
	
	
	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
	
	private void resize(int chains) {
		SeparateChainingHT temp = new SeparateChainingHT<K, V>();
        for (int i = 0; i < M; i++) {
            while(this.iterator().hasNext()) {
            	Iterator x = iterator();
            	NodoHT nodo = (NodoHT) x.next();
            	temp.put((Comparable) nodo.getKey(), nodo.getValue());
            }
        }
        this.M  = temp.M;
        this.n  = temp.n;
        this.st = temp.st;
    }



	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		int i = hash((K) key);
		for (NodoHT x = st[i]; x != null; x = x.getNext()) {
			if (key.equals(x.getKey()))
			{
				x.changeValue(value);;
				System.out.println("Cambio valor");
				return;
			}
		}
		if (n >= 10*M) resize(2*M);
		n++;
		st[i] = new NodoHT(key, value, st[i]);
	}



	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object delete(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
}

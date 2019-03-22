package model.data_structures;

import java.util.Iterator;

public class SeparateChainingHT <K extends Comparable<K>, V> implements IHashTable{
	
	private int M = 97; // number of chains
	
	private NodoHT[] st = new NodoHT[M]; // array of chains
	
	public SeparateChainingHT(int m) {
        this.M = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    } 
	@Override
	public void put(Comparable key, Object value) {
		// TODO Auto-generated method stub
		int i = hash(key);
		for (NodoHT x = st[i]; x != null; x = x.getNext())
			if (key.equals(x.getNext())) {
				x.changeValue(value);;
				return;
			}
		st[i] = new NodoHT(key, value, st[i]);
	}

	@Override
	public Object get(Comparable key) {
		// TODO Auto-generated method stub
		int i = hash(key);
		System.out.println(i);
		for (NodoHT x = st[i]; x != null; x = x.getNext())
			if (key.equals(x.getKey())) 
				return  (V) x.getValue();
		return null;
	}

	@Override
	public Object delete(Comparable key) {
		// TODO Auto-generated method stub
        int i = hash(key);
        if (st[i].getKey().equals(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
	}
	
	private int hash(Comparable key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
	
	private void resize(int chains) {
        Separa temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}

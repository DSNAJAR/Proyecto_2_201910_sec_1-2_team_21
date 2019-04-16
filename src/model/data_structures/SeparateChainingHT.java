package model.data_structures;

import java.util.Iterator;

public class SeparateChainingHT <K extends Comparable<K>, V> implements IHashTable{
	private int n;       // number of key-value pairs
    private int M;       // hash table size 
	private LinkedListHT<K, V>[] st;// array of chains
	
	public SeparateChainingHT( ) {
        this.M = view.MovingViolationsManagerView.N;
        st = (LinkedListHT<K, V>[]) new LinkedListHT[M];
        for (int i = 0; i < M; i++)
            st[i] = new LinkedListHT<K, V>();
    }
	
	private int hash(K key) {
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
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		int i = hash((K) key);
        if (!st[i].contains((K) key)) n++;
        st[i].put((K) key, (V) value);
	}

	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		int i = hash((K) key);
        return st[i].get((K) key);
	}

	@Override
	public void delete(Object key) {
		// TODO Auto-generated method stub
		int i = hash((K) key);
        if (st[i].contains((K) key)) n--;
        st[i].delete((K) key);
	}
	
	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		IQueue<K> queue = new Queue<K>();
        for (int i = 0; i < M; i++) {
            for (K key : st[i].keys())
                queue.enqueue(key);
        }
        return queue.iterator();
	}
	
	public int size() {
        return n;
    }
}

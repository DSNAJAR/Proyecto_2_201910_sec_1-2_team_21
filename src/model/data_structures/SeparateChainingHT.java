package model.data_structures;

import java.util.Iterator;

public class SeparateChainingHT <K extends Comparable<K>, V> implements IHashTable{
	
	
	private int n;       // number of key-value pairs
    private int M;       // hash table size
	private NodoHT[] st = new NodoHT[M]; // array of chains
	
	public SeparateChainingHT(int m) {
        this.M = view.MovingViolationsManagerView.N;
        st = new NodoHT[m];
    } 
	
	@Override
	public void put(Comparable key, Object value) {
		// TODO Auto-generated method stub
		int i = hash(key);
		for (NodoHT x = st[i]; x != null; x = x.getNext()) {
			if (key.compareTo(x.getNext()) == 0) {
				x.changeValue(value);
				return;
			}
		}
		if (n >= 10*M) resize(2*M);
		n++;
		st[i] = new NodoHT(key, value, st[i]);
	}

	@Override
	public Object get(Comparable key) {
		// TODO Auto-generated method stub
		int i = hash(key);
		for (NodoHT x = st[i]; x != null; x = x.getNext())
			if (key.equals(x.getKey())) {
				return  (V) x.getValue();
			}
		return null;
	}

	@Override
	public Object delete(Comparable key) {
		// TODO Auto-generated method stub
        int i = hash(key);
        NodoHT x = st[i];
        NodoHT rt = x;
        NodoHT siguiente = null;
        if (st[i].getKey().equals(key)) {
        	n--;
        	if(x.getNext() != null) {
        		siguiente = x.getNext();
        		x.setNext(null);
        		x = siguiente;
        		x.setNext(siguiente.getNext());
        	}
        	else{
        		x = null;
        	}
        }

        // halve table size if average length of list <= 2
        if (n <= 2*M) resize(M/2);
		return rt;
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
		SeparateChainingHT temp = new SeparateChainingHT<K, V>(chains);
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
		Queue<K> queue = new Queue<K>();
        for (int i = 0; i < M; i++) {
            for (NodoHT x = st[i]; x != null; x = x.getNext()) {
                queue.enqueue( (K) x.getKey());
            }
        }
        return (Iterator) queue;
	}
}

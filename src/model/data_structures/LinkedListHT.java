package model.data_structures;

public class LinkedListHT<K, V> implements ILinkedListHT<K, V>{
	
	/**
	 * numero de parejas K,V
	 */
	private int n;
	
	/**
	 * Primer nodo de la lista
	 */
	private NodoHT first;
	
	public LinkedListHT() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		if (value == null) {
            delete(key);
            return;
        }
		
        for (NodoHT x = first; x != null; x = x.getNext()) {
            if (key.equals(x.getKey())) {
                x.changeValue(value);
                return;
            }
        }
        first = new NodoHT(key, value, first);
        n++;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub
		if(contains(key)) {
			NodoHT x = first;
			while(x.getNext() != null) {
				if(x.getKey().equals(key)) {
					n--;
					return ;
				}
				x = x.getNext();
			}
		}
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		V value  = null;
		for (NodoHT x = first; x != null; x = x.getNext()) {
            if (key.equals(x.getKey()))
            	value = (V) x.getValue();
                return value;
        }
        return value;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return get(key) != null;
	}
	
	public Iterable<K> keys()  {
        Queue<K> queue = new Queue<K>();
        for (NodoHT x = first; x != null; x = x.getNext())
            queue.enqueue((K) x.getKey());
        return queue;
    }

}

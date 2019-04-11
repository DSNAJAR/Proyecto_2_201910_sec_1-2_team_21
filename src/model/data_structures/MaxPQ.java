package model.data_structures;

import java.util.Iterator;

public class MaxPQ <Key extends Comparable<Key>> implements IQueue<Key>{

	private Key[] pq;
	private int N;

	@SuppressWarnings("unchecked")
	public MaxPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity + 1];
	}

	/**
	 * Indica si está vacío
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * Elimina la raíz
	 * @return max
	 */
	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		sink(1);
		pq[N + 1] = null;
		return max;
	}

	/**
	 * Intercambia la llave del padre con la del hijo mas grande
	 * @param k
	 */
	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	/**
	 * Retorna true si el elemento en la posición i es menor que el elemento en
	 * la posición j
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	/**
	 * Intercambia las posiciones de los elementos de las posiciones que llegan
	 * por parámetro
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	/**
	 * Intercambia la llave del hijo con la del padre hasta que esté en orden
	 * @param k
	 */
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	/**
	 * Inserta un elemento
	 * @param x
	 */
	public void insert(Key x) {
		pq[++N] = x;
		swim(N);
	}
	
	
	public static void main(String...arg) {
		int[] h = {4, 5, 6, 67, 8, 8};
		MaxPQ<Integer> ff = new MaxPQ<Integer>(h.length);
		for(Integer ha: h) {
			ff.insert(ha);
		}
		 
		for(int i= 0; i<h.length; i++) {
			System.out.println(ff.delMax());
		}
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(Key item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Key dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
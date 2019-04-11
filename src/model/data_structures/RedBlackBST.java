package model.data_structures;

import java.util.Iterator;

public class RedBlackBST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	
	//Constantes
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	//Atributos
	
	private Node root;
	
	
	//Clase nodo Arbol R-N
	private class Node {
		
		/**
		 * Key
		 */
		Key key;
		
		/**
		 * Data asociada
		 */
		Value val;
		
		/**
		 * Nodo enlazados
		 */
		Node left, right;
		
		/**
		 * Color del padre
		 */
		boolean color; // color of parent link
		
		/**
		 * Tamanho del subarbol
		 */
		int size;
		
		public Node(Key pKey, Value pVal, boolean pColor, int pSize) {
			key = pKey;
			val = pVal;
			color = pColor;
			size = pSize;
		}
	}
	
	//Metodos
	
	/**
	 * Retorna el numero de nodos en el subarbol de X
	 * @param x Raiz del subarbol
	 * @return Numero de nodos, 0 si X es null
	 */
	private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 
	
	/**
	 * Retorna el numero de duplas<k,v> en el arbol
	 * @return Nimero de duplas
	 */
	public int size() {
        return size(root);
    }
	
	/**
	 * Informa si el arbol esta vacio
	 * @return True si esta vaccio/False si no esta vacio
	 */
	public boolean isEmpty() {
        return root == null;
    }
	
	/**
	 * Retorna un valor asociado a una llave dada por parametro
	 * @param pKey Llave del valor
	 * @return Valor Value, null si la llave no se encuentra
	 */
	public Value get(Key pKey) {
		Node x = root;
		
		if(pKey == null) return null;
		
		while(x != null) {
            
			int cmp = pKey.compareTo(x.key);
            
            if(cmp < 0) {
            	x = x.left;
            }
            else if(cmp > 0) {
            	x = x.right;
            }
            else {
            	return x.val;
            }
        }
        return null;
	}
	
	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave pKey
	 * @param pKey llave destino
	 * @return altura desde la raiz hasta la llave, -1 si la llave no existe
	 */
	public int getHeight(Key pKey) {
		if(contains(pKey) == false) return -1;
		
		Node x = root;
		int contador = 0;
		boolean ya = false;
		
		while(ya) {
			int cmp = pKey.compareTo(x.key);
			
			if(cmp < 0) {
				x = x.left;
				contador++;
			}
			else if(cmp > 0) {
				x = x.right;
				contador++;
			}
			else {
				ya = true;
			}
		}
		
		return contador;
	}
	
	/**
	 * Indica si la llave se encuentra en el arbol
	 * @param pKey llave a buscar
	 * @return True si se encuentra, False si no
	 */
	public boolean contains(Key pKey) {
		return get(pKey) != null;
	}
	
	/**
	 * Inserta una dupla [key, val] en el arbol. Si la llave ya existe reemplaza el valor
	 * @param key llave de la dupla
	 * @param val valor asociado a la llave
	 */
	public void put(Key key, Value val) {
        if (key == null || val == null) throw new IllegalArgumentException("Valores no validos");
        
        root = put(root, key, val);
        root.color = BLACK;
    }
	
	/**
	 * Retorna la altura del arbol
	 * @return altura del arbol
	 */
	public int height() {
		return Math.max(getHeight(min()), getHeight(max()));
	}
	
	/**
	 * Retorna la llave mas pequeña del arbol
	 * @return Llave mas pequeña, null si el arbol esta vacio
	 */
	public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }
	
	/**
	 * Retorna la llave mas grande del arbol
	 * @return Llave mas grande, null si el arbol esta vacio
	 */
	public Key max() {
		if (isEmpty()) return null;
        return max(root).key;
	}
	
	/**
	 * Retorna todas llaves del árbol como un iterador
	 * @return iterator
	 */
	public Iterator<Key> keys() {
		if (isEmpty()) return (Iterator<Key>) new DoubleLinkedList<Key>();
        return keysInRange(min(), max());
	}
	
	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado
	 * @param init rango inicial
	 * @param end rango final
	 * @return iterator
	 */
	public Iterator<Key> keysInRange(Key init, Key end) {
	    DoubleLinkedList<Key> list = new DoubleLinkedList<Key>();
	    keys(root, list, init, end);
	    
	    return (Iterator<Key>) list;
	}
	
	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango de llaves dado
	 * @param init rango inicial
	 * @param end rango final
	 * @return iterator
	 */
	public Iterator<Value> valuesInRange(Key init, Key end) {
		DoubleLinkedList<Value> list = new DoubleLinkedList<Value>();
	    values(root, list, init, end);
	    
	    return (Iterator<Value>) list;
	}

	//----------Metodos auxiliares
	
	private Node put(Node h, Key key, Value val) { 
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val); 
        else if (cmp > 0) h.right = put(h.right, key, val); 
        else              h.val   = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }
	
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}
	
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	 }
	
	private Node min(Node x) { 
		if(x.left == null) return x; 
        else return min(x.left); 
    }
	
	private Node max(Node x) { 
		if(x.right == null) return x; 
        else return max(x.right); 
    } 
	
	private void keys(Node x, DoubleLinkedList<Key> list, Key lo, Key hi) {
		if (x == null) return; 
	    int cmplo = lo.compareTo(x.key); 
	    int cmphi = hi.compareTo(x.key); 
	    if (cmplo < 0) keys(x.left, list, lo, hi); 
	    if (cmplo <= 0 && cmphi >= 0) list.agregar(x.key); 
	    if (cmphi > 0) keys(x.right, list, lo, hi); 
	}
	
	private void values(Node x, DoubleLinkedList<Value> list, Key lo, Key hi) {
		if (x == null) return; 
	    int cmplo = lo.compareTo(x.key); 
	    int cmphi = hi.compareTo(x.key); 
	    if (cmplo < 0) values(x.left, list, lo, hi); 
	    if (cmplo <= 0 && cmphi >= 0) list.agregar(x.key); 
	    if (cmphi > 0) values(x.right, list, lo, hi); 
	}
}

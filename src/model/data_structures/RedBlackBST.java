package model.data_structures;

import java.util.NoSuchElementException;

public class RedBlackBST <Key extends Comparable<Key>, Value>
{

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private NodoArbol root;


	public RedBlackBST() 
	{

	}

	NodoArbol rotateLeft(NodoArbol h)
	{
		NodoArbol x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	NodoArbol rotateRight(NodoArbol h)
	{
		NodoArbol x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	void flipColors(NodoArbol h)
	{
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	 /***************************************************************************
	    *  Node helper methods.
	    ***************************************************************************/
	    // is node x red; false if x is null ?
	    private boolean esRojo (NodoArbol x) {
	        if (x == null) return false;
	        return x.color == RED;
	    }

	    // number of node in subtree rooted at x; 0 if x is null
	    private int size(NodoArbol x) {
	        if (x == null) return 0;
	        return x.size;
	    } 


	    /**
	     * Returns the number of key-value pairs in this symbol table.
	     * @return the number of key-value pairs in this symbol table
	     */
	    public int size() {
	        return size(root);
	    }

	   /**
	     * Is this symbol table empty?
	     * @return {@code true} if this symbol table is empty and {@code false} otherwise
	     */
	    public boolean isEmpty() {
	        return root == null;
	    }


	   /***************************************************************************
	    *  Standard BST search.
	    ***************************************************************************/

	    /**
	     * Returns the value associated with the given key.
	     * @param key the key
	     * @return the value associated with the given key if the key is in the symbol table
	     *     and {@code null} if the key is not in the symbol table
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public Value get(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to get() is null");
	        return get(root, key);
	    }

	    // value associated with the given key in subtree rooted at x; null if no such key
	    private Value get(NodoArbol x, Key key) {
	    	
	    	while (x != null) {
	    		
	           	int cmp = key.compareTo((Key) x.key);
	            if      (cmp < 0) x = x.left;
	            else if (cmp > 0) x = x.right;
	            else              
	            	return (Value) x.val;
	        }
	        return null;
	    }
	    
	    public Value getPorNombre(String key) {
	       
	    	NodoArbol x = root;
	    	while (x != null) {
	    		System.out.println("La compania es " +key + "La que tento es"+ x.key.toString());
	            int cmp = key.compareTo(x.key.toString());
	            if      (cmp < 0) x = x.left;
	            else if (cmp > 0) x = x.right;
	            else              
	            	return (Value) x.val;
	        }
	        return null;
	    }

	    /**
	     * Does this symbol table contain the given key?
	     * @param key the key
	     * @return {@code true} if this symbol table contains {@code key} and
	     *     {@code false} otherwise
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public boolean contains(Key key) {
	        return get(key) != null;
	    }

	   /***************************************************************************
	    *  Red-black tree insertion.
	    ***************************************************************************/

	    /**
	     * Inserts the specified key-value pair into the symbol table, overwriting the old 
	     * value with the new value if the symbol table already contains the specified key.
	     * Deletes the specified key (and its associated value) from this symbol table
	     * if the specified value is {@code null}.
	     *
	     * @param key the key
	     * @param val the value
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public void put(Key key, Value val) {
	        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
	        if (val == null) {
	            delete(key);
	            return;
	        }

	        root = put(root, key, val);
	        root.color = BLACK;
	        // assert check();
	    }

	    // insert the key-value pair in the subtree rooted at h
	    private NodoArbol put(NodoArbol h, Key key, Value val) 
	    { 
	        if (h == null) return new NodoArbol(key, val, 1, RED);

	        int cmp = key.compareTo((Key) h.key);
	        if      (cmp < 0) h.left  = put(h.left,  key, val); 
	        else if (cmp > 0) h.right = put(h.right, key, val); 
	        else              h.val   = val;

	        // fix-up any right-leaning links
	        if (esRojo(h.right) && !esRojo(h.left))      h = rotateLeft(h);
	        if (esRojo(h.left)  &&  esRojo(h.left.left)) h = rotateRight(h);
	        if (esRojo(h.left)  &&  esRojo(h.right))     flipColors(h);
	        h.size = size(h.left) + size(h.right) + 1;

	        return h;
	    }

	   /***************************************************************************
	    *  Red-black tree deletion.
	    ***************************************************************************/

	    /**
	     * Removes the smallest key and associated value from the symbol table.
	     * @throws NoSuchElementException if the symbol table is empty
	     */
	    public void deleteMin() {
	        if (isEmpty()) throw new NoSuchElementException("BST underflow");

	        // if both children of root are black, set root to red
	        if (!esRojo(root.left) && !esRojo(root.right))
	            root.color = RED;

	        root = deleteMin(root);
	        if (!isEmpty()) root.color = BLACK;
	        // assert check();
	    }

	    // delete the key-value pair with the minimum key rooted at h
	    private NodoArbol deleteMin(NodoArbol h) { 
	        if (h.left == null)
	            return null;

	        if (!esRojo(h.left) && !esRojo(h.left.left))
	            h = moveRedLeft(h);

	        h.left = deleteMin(h.left);
	        return balance(h);
	    }


	    /**
	     * Removes the largest key and associated value from the symbol table.
	     * @throws NoSuchElementException if the symbol table is empty
	     */
	    public void deleteMax() {
	        if (isEmpty()) throw new NoSuchElementException("BST underflow");

	        // if both children of root are black, set root to red
	        if (!esRojo(root.left) && !esRojo(root.right))
	            root.color = RED;

	        root = deleteMax(root);
	        if (!isEmpty()) root.color = BLACK;
	        // assert check();
	    }

	    // delete the key-value pair with the maximum key rooted at h
	    private NodoArbol deleteMax(NodoArbol h) { 
	        if (esRojo(h.left))
	            h = rotateRight(h);

	        if (h.right == null)
	            return null;

	        if (!esRojo(h.right) && !esRojo(h.right.left))
	            h = moveRedRight(h);

	        h.right = deleteMax(h.right);

	        return balance(h);
	    }

	    /**
	     * Removes the specified key and its associated value from this symbol table     
	     * (if the key is in this symbol table).    
	     *
	     * @param  key the key
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public void delete(Key key) { 
	        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
	        if (!contains(key)) return;

	        // if both children of root are black, set root to red
	        if (!esRojo(root.left) && !esRojo(root.right))
	            root.color = RED;

	        root = delete(root, key);
	        if (!isEmpty()) root.color = BLACK;
	        // assert check();
	    }

	    // delete the key-value pair with the given key rooted at h
	    private NodoArbol delete(NodoArbol h, Key key) { 
	        // assert get(h, key) != null;

	        if (key.compareTo((Key) h.key) < 0)  {
	            if (!esRojo(h.left) && !esRojo(h.left.left))
	                h = moveRedLeft(h);
	            h.left = delete(h.left, key);
	        }
	        else {
	            if (esRojo(h.left))
	                h = rotateRight(h);
	            if (key.compareTo((Key) h.key) == 0 && (h.right == null))
	                return null;
	            if (!esRojo(h.right) && !esRojo(h.right.left))
	                h = moveRedRight(h);
	            if (key.compareTo((Key) h.key) == 0) {
	            	NodoArbol x = min(h.right);
	                h.key = x.key;
	                h.val = x.val;
	                // h.val = get(h.right, min(h.right).key);
	                // h.key = min(h.right).key;
	                h.right = deleteMin(h.right);
	            }
	            else h.right = delete(h.right, key);
	        }
	        return balance(h);
	    }
	    
	    private NodoArbol moveRedLeft(NodoArbol h) {
	        // assert (h != null);
	        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

	        flipColors(h);
	        if (esRojo(h.right.left)) { 
	            h.right = rotateRight(h.right);
	            h = rotateLeft(h);
	            flipColors(h);
	        }
	        return h;
	    }

	    // Assuming that h is red and both h.right and h.right.left
	    // are black, make h.right or one of its children red.
	    private NodoArbol moveRedRight(NodoArbol h) {
	        // assert (h != null);
	        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
	        flipColors(h);
	        if (esRojo(h.left.left)) { 
	            h = rotateRight(h);
	            flipColors(h);
	        }
	        return h;
	    }

	    // restore red-black tree invariant
	    private NodoArbol balance(NodoArbol h) {
	        // assert (h != null);

	        if (esRojo(h.right))                      h = rotateLeft(h);
	        if (esRojo(h.left) && esRojo(h.left.left)) h = rotateRight(h);
	        if (esRojo(h.left) && esRojo(h.right))     flipColors(h);

	        h.size = size(h.left) + size(h.right) + 1;
	        return h;
	    }


	   /***************************************************************************
	    *  Utility functions.
	    ***************************************************************************/

	    /**
	     * Returns the height of the BST (for debugging).
	     * @return the height of the BST (a 1-node tree has height 0)
	     */
	    public int height() {
	        return height(root);
	    }
	    private int height(NodoArbol x) {
	        if (x == null) return -1;
	        return 1 + Math.max(height(x.left), height(x.right));
	    }

	   /***************************************************************************
	    *  Ordered symbol table methods.
	    ***************************************************************************/

	    /**
	     * Returns the smallest key in the symbol table.
	     * @return the smallest key in the symbol table
	     * @throws NoSuchElementException if the symbol table is empty
	     */
	    public Key min() {
	        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
	        return (Key) min(root).key;
	    } 

	    // the smallest key in subtree rooted at x; null if no such key
	    private NodoArbol min(NodoArbol x) { 
	        // assert x != null;
	        if (x.left == null) return x; 
	        else                return min(x.left); 
	    } 

	    /**
	     * Returns the largest key in the symbol table.
	     * @return the largest key in the symbol table
	     * @throws NoSuchElementException if the symbol table is empty
	     */
	    public Key max() {
	        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
	        return (Key) max(root).key;
	    } 

	    // the largest key in the subtree rooted at x; null if no such key
	    private NodoArbol max(NodoArbol x) { 
	        // assert x != null;
	        if (x.right == null) return x; 
	        else                 return max(x.right); 
	    } 


	    /**
	     * Returns the largest key in the symbol table less than or equal to {@code key}.
	     * @param key the key
	     * @return the largest key in the symbol table less than or equal to {@code key}
	     * @throws NoSuchElementException if there is no such key
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public Key floor(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
	        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
	        NodoArbol x = floor(root, key);
	        if (x == null) return null;
	        else           return (Key) x.key;
	    }    

	    // the largest key in the subtree rooted at x less than or equal to the given key
	    private NodoArbol floor(NodoArbol x, Key key) {
	        if (x == null) return null;
	        int cmp = key.compareTo((Key) x.key);
	        if (cmp == 0) return x;
	        if (cmp < 0)  return floor(x.left, key);
	        NodoArbol t = floor(x.right, key);
	        if (t != null) return t; 
	        else           return x;
	    }

	    /**
	     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
	     * @param key the key
	     * @return the smallest key in the symbol table greater than or equal to {@code key}
	     * @throws NoSuchElementException if there is no such key
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public Key ceiling(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
	        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
	        NodoArbol x = ceiling(root, key);
	        if (x == null) return null;
	        else           return (Key) x.key;  
	    }

	    // the smallest key in the subtree rooted at x greater than or equal to the given key
	    private NodoArbol ceiling(NodoArbol x, Key key) {  
	        if (x == null) return null;
	        int cmp = key.compareTo((Key) x.key);
	        if (cmp == 0) return x;
	        if (cmp > 0)  return ceiling(x.right, key);
	        NodoArbol t = ceiling(x.left, key);
	        if (t != null) return t; 
	        else           return x;
	    }

	    /**
	     * Return the key in the symbol table whose rank is {@code k}.
	     * This is the (k+1)st smallest key in the symbol table. 
	     * @param  k the order statistic
	     * @return the key in the symbol table of rank {@code k}
	     * @throws IllegalArgumentException unless {@code k} is between 0 and
	     *        <em>n</em>–1
	     */
	    public Key select(int k) {
	        if (k < 0 || k >= size()) {
	            throw new IllegalArgumentException("argument to select() is invalid: " + k);
	        }
	        NodoArbol x = select(root, k);
	        return (Key) x.key;
	    }

	    // the key of rank k in the subtree rooted at x
	    private NodoArbol select(NodoArbol x, int k) {
	        // assert x != null;
	        // assert k >= 0 && k < size(x);
	        int t = size(x.left); 
	        if      (t > k) return select(x.left,  k); 
	        else if (t < k) return select(x.right, k-t-1); 
	        else            return x; 
	    } 

	    /**
	     * Return the number of keys in the symbol table strictly less than {@code key}.
	     * @param key the key
	     * @return the number of keys in the symbol table strictly less than {@code key}
	     * @throws IllegalArgumentException if {@code key} is {@code null}
	     */
	    public int rank(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
	        return rank(key, root);
	    } 

	    // number of keys less than key in the subtree rooted at x
	    private int rank(Key key, NodoArbol x) {
	        if (x == null) return 0; 
	        int cmp = key.compareTo((Key) x.key); 
	        if      (cmp < 0) return rank(key, x.left); 
	        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
	        else              return size(x.left); 
	    } 

	   /***************************************************************************
	    *  Range count and range search.
	    ***************************************************************************/

	    /**
	     * Returns all keys in the symbol table as an {@code Iterable}.
	     * To iterate over all of the keys in the symbol table named {@code st},
	     * use the foreach notation: {@code for (Key key : st.keys())}.
	     * @return all keys in the symbol table as an {@code Iterable}
	     */
	    public Iterable<Key> keys() {
	        if (isEmpty()) return (Iterable<Key>) new DoubleLinkedList<Key>();
	        return keys(min(), max());
	    }

	    /**
	     * Returns all keys in the symbol table in the given range,
	     * as an {@code Iterable}.
	     *
	     * @param  lo minimum endpoint
	     * @param  hi maximum endpoint
	     * @return all keys in the sybol table between {@code lo} 
	     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
	     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	     *    is {@code null}
	     */
	    public Iterable<Key> keys(Key lo, Key hi) {
	        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
	        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

	        DoubleLinkedList<Key> list = new DoubleLinkedList<Key>();
	        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
	        keys(root, list, lo, hi);
	        return (Iterable<Key>) list;
	    } 

	    // add the keys between lo and hi in the subtree rooted at x
	    // to the queue
	    private void keys(NodoArbol x, DoubleLinkedList<Key> dList, Key lo, Key hi) { 
	        if (x == null) return; 
	        int cmplo = lo.compareTo((Key) x.key); 
	        int cmphi = hi.compareTo((Key) x.key); 
	        if (cmplo < 0) keys(x.left, dList, lo, hi); 
	        if (cmplo <= 0 && cmphi >= 0) dList.agregar(x.key); 
	        if (cmphi > 0) keys(x.right, dList, lo, hi); 
	    } 

	    /**
	     * Returns the number of keys in the symbol table in the given range.
	     *
	     * @param  lo minimum endpoint
	     * @param  hi maximum endpoint
	     * @return the number of keys in the sybol table between {@code lo} 
	     *    (inclusive) and {@code hi} (inclusive)
	     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	     *    is {@code null}
	     */
	    public int size(Key lo, Key hi) {
	        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
	        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

	        if (lo.compareTo(hi) > 0) return 0;
	        if (contains(hi)) return rank(hi) - rank(lo) + 1;
	        else              return rank(hi) - rank(lo);
	    }

}

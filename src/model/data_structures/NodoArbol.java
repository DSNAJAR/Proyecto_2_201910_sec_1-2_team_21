package model.data_structures;

public class NodoArbol <Key extends Comparable<Key>, Value>
{
	
		Key key; // key
		Value val; // associated data
		NodoArbol left, right; // subtrees
		int size; // # nodes in this subtree
		boolean color; // color of parent link 
		
		NodoArbol (Key key, Value val, int N, boolean color)
		{
			this.key = key;
			this.val = val;
			this.size = N;
			this.color = color;
		}
	
}

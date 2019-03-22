package DataStructuresTest;

import junit.framework.TestCase;
import model.data_structures.DoubleLinkedList;

public class DoublyLinkedListTest extends TestCase{
	
	DoubleLinkedList<String> list;
	
	private void setupEscenario1() {
		list = new DoubleLinkedList<String>();
	}
	
	private void setupEscenario2() {
		list = new DoubleLinkedList<String>();
		list.agregar("String 1");
		list.agregar("String 2");
		list.agregar("String 3");
		list.agregar("String 4");
		list.agregar("String 5");
	}
	
	public void testGetItem1() {
		setupEscenario1();
		assertEquals(null, list.getItem(1));
	}
	
	public void testGetItem2() {
		setupEscenario2();
		assertEquals("String 1", list.getItem(1));
	}
	
	public void testAgregar1() {
		setupEscenario1();
		list.agregar("String 0");
		assertEquals(false, list.estaVacia());
	}
	
	public void testAgregar2() {
		setupEscenario2();
		list.agregar("String 0");
		assertEquals(6, list.getTamanho());
	}
	
	public void testAddBetween() {
		setupEscenario2();
		list.addBetween("String 0", list.getFirst(), list.getFirst().getSiguiente());
		assertEquals(6, list.getTamanho());
	}
	
	public void testEliminar() {
		setupEscenario2();
		list.eliminar("String 5");
		assertEquals(4, list.getTamanho());
	}
}

package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas para las colas de prioridad
 * @author Miguel Méndez Murias
 *
 */
class EDBinaryHeapTest {

	/**
	 * Pruebas para el método add()
	 */
	@Test
	void addTest() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(5);
		assertTrue(cola.add(1));
		assertEquals(cola.toString(),
				"1\n");
		assertTrue(cola.add(2));
		assertEquals(cola.toString(),
				"1\n"
				+ "	2\n");
		assertTrue(cola.add(3));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "	2\n");
		assertTrue(cola.add(4));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "	2\n"
				+ "		4\n");
		assertTrue(cola.add(5));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "		4\n");
		assertFalse(cola.add(6));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "		4\n");
	}
	/**
	 * Pruebas para el método poll()
	 */
	@Test
	void pollTest() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(5);
		assertTrue(cola.add(1));
		assertTrue(cola.add(2));
		assertTrue(cola.add(3));
		assertTrue(cola.add(4));
		assertTrue(cola.add(5));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "		4\n");
		assertEquals(cola.poll(),1);
		assertEquals(cola.toString(),
				"	3\n"
				+ "2\n"
				+ "	4\n"
				+ "		5\n");
		assertEquals(cola.poll(),2);
		assertEquals(cola.toString(),
				"	5\n"
				+ "3\n"
				+ "	4\n");
		assertEquals(cola.poll(),3);
		assertEquals(cola.toString(),
				"4\n"
				+ "	5\n");
		assertEquals(cola.poll(),4);
		assertEquals(cola.toString(),
				"5\n");
		assertEquals(cola.poll(),5);
		assertEquals(cola.toString(),
				"");
		assertEquals(cola.poll(),null);
	}
	/**
	 * Pruebas para el método remove()
	 */
	@Test
	void removeTest() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(9);
		assertFalse(cola.remove(0));
		assertTrue(cola.add(1));
		assertTrue(cola.add(2));
		assertTrue(cola.add(3));
		assertTrue(cola.add(4));
		assertTrue(cola.add(5));
		assertTrue(cola.add(6));
		assertTrue(cola.add(7));
		assertTrue(cola.add(8));
		assertTrue(cola.add(9));
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		6\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "			9\n"
				+ "		4\n"
				+ "			8\n");
		assertTrue(cola.remove(6));
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		9\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "		4\n"
				+ "			8\n");
		assertFalse(cola.remove(6));
		assertTrue(cola.remove(1));
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		9\n"
				+ "2\n"
				+ "		5\n"
				+ "	4\n"
				+ "		8\n");
	}
	
	/**
	 * Pruebas para comprobar el funcionamiento de los métodos isEmpty() y clear()
	 */
	@Test
	void isEmptyAndClearTest() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(9);
		assertTrue(cola.isEmpty());
		assertTrue(cola.add(1));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(2));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(3));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(4));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(5));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(6));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(7));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(8));
		assertFalse(cola.isEmpty());
		assertTrue(cola.add(9));
		assertFalse(cola.isEmpty());
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		6\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "			9\n"
				+ "		4\n"
				+ "			8\n");
		cola.clear();
		assertTrue(cola.isEmpty());
		assertEquals(cola.toString(),"");
	}
	
	/**
	 * Pruebas para comprobar el funcionamiento del filtrado ascendente
	 */
	@Test
	void filtradoAscendenteTest() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(9);
		assertTrue(cola.add(1));
		assertEquals(cola.toString(),
				"1\n");
		assertTrue(cola.add(2));
		assertEquals(cola.toString(),
				"1\n"
				+ "	2\n");
		assertTrue(cola.add(3));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "	2\n");
		assertTrue(cola.add(4));
		assertEquals(cola.toString(),
				"	3\n"
				+ "1\n"
				+ "	2\n"
				+ "		4\n");
		assertTrue(cola.add(0)); //Filtrado ascendente
		assertEquals(cola.toString(),
				"	3\n"
				+ "0\n"
				+ "		2\n"
				+ "	1\n"
				+ "		4\n");
		assertTrue(cola.add(6));
		assertEquals(cola.toString(),
				"	3\n"
				+ "		6\n"
				+ "0\n"
				+ "		2\n"
				+ "	1\n"
				+ "		4\n");
		assertTrue(cola.add(-4)); //filtrado ascendente
		assertEquals(cola.toString(),
				"		3\n"
				+ "	0\n"
				+ "		6\n"
				+ "-4\n"
				+ "		2\n"
				+ "	1\n"
				+ "		4\n");
		assertTrue(cola.add(5));
		assertEquals(cola.toString(),
				"		3\n"
				+ "	0\n"
				+ "		6\n"
				+ "-4\n"
				+ "		2\n"
				+ "	1\n"
				+ "		4\n"
				+ "			5\n");
		assertTrue(cola.add(-1));//filtrado ascendente
		assertEquals(cola.toString(),
				"		3\n"
				+ "	0\n"
				+ "		6\n"
				+ "-4\n"
				+ "		2\n"
				+ "	-1\n"
				+ "			4\n"
				+ "		1\n"
				+ "			5\n");
	}
	
	/**
	 * Pruebas para comprobar el funcionamiento del filtrado descendente
	 */
	@Test
	void filtradoDescendente() {
		EDBinaryHeap<Integer> cola=new EDBinaryHeap<>(9);
		assertTrue(cola.add(1));
		assertTrue(cola.add(2));
		assertTrue(cola.add(3));
		assertTrue(cola.add(4));
		assertTrue(cola.add(5));
		assertTrue(cola.add(6));
		assertTrue(cola.add(7));
		assertTrue(cola.add(8));
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		6\n"
				+ "1\n"
				+ "		5\n"
				+ "	2\n"
				+ "		4\n"
				+ "			8\n");
		assertEquals(cola.poll(),1); //filtrado descendente
		assertEquals(cola.toString(),
				"		7\n"
				+ "	3\n"
				+ "		6\n"
				+ "2\n"
				+ "		5\n"
				+ "	4\n"
				+ "		8\n");
		assertEquals(cola.poll(),2);
		assertEquals(cola.toString(),
				"	6\n"
				+ "		7\n"
				+ "3\n"
				+ "		5\n"
				+ "	4\n"
				+ "		8\n");
		assertTrue(cola.remove(4)); //filtrado descendente
		assertEquals(cola.toString(),
				"	6\n"
				+ "3\n"
				+ "		7\n"
				+ "	5\n"
				+ "		8\n");
		assertEquals(cola.poll(),3); //filtrado descendente
		assertEquals(cola.toString(),
				"	6\n"
				+ "5\n"
				+ "	7\n"
				+ "		8\n");
		assertEquals(cola.poll(),5); //filtrado descendente
		assertEquals(cola.toString(),
				"	8\n"
				+ "6\n"
				+ "	7\n");
	}
}

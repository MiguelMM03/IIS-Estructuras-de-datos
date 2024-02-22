package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas para la clase AVLTree
 * @author Miguel Méndez Murias
 *
 */
class AVLTreeTest {

	/**
	 * Pruebas de inserción y eliminación de AVL
	 * Se comprueba que funcione para todos los casos posibles las actualizaciones 
	 * de los factores de balance y los rebalanceos, ya que los métodos add y remove son
	 * iguales que los de BSTree (que funcionan correctamente), solo que con las características
	 * de los AVL (que son las que hay que comprobar realmente)
	 */
	@Test
	void testAddAndRemove() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(40));
		assertTrue(tree.addNode(30));
		assertTrue(tree.addNode(50));
		assertTrue(tree.addNode(20));
		assertTrue(tree.addNode(35));
		assertTrue(tree.addNode(45));
		assertTrue(tree.addNode(55));
		assertTrue(tree.addNode(10));
		assertTrue(tree.addNode(32));
		assertTrue(tree.addNode(25));
		assertTrue(tree.addNode(42));
		assertTrue(tree.addNode(5));
		assertFalse(tree.addNode(5));
		try {
			tree.addNode(null);
			fail();
		}catch (NullPointerException e) {
			assertEquals(e.getMessage(), "Element to insert is null.");
		}

		assertEquals(tree.toString(),
				"		55:BF=0\n"
				+ "	50:BF=-1\n"
				+ "		45:BF=-1\n"
				+ "			42:BF=0\n"
				+ "40:BF=-1\n"
				+ "		35:BF=-1\n"
				+ "			32:BF=0\n"
				+ "	30:BF=-1\n"
				+ "			25:BF=0\n"
				+ "		20:BF=-1\n"
				+ "			10:BF=-1\n"
				+ "				5:BF=0\n");
		try {
			tree.removeNode(null);
			fail();
		}catch (NullPointerException e) {
			assertEquals(e.getMessage(), "Element to remove is null.");
		}
		assertTrue(tree.removeNode(55));

		assertEquals(tree.toString(),
				"			50:BF=0\n"
				+ "		45:BF=0\n"
				+ "			42:BF=0\n"
				+ "	40:BF=0\n"
				+ "		35:BF=-1\n"
				+ "			32:BF=0\n"
				+ "30:BF=0\n"
				+ "		25:BF=0\n"
				+ "	20:BF=-1\n"
				+ "		10:BF=-1\n"
				+ "			5:BF=0\n");
		assertTrue(tree.removeNode(32));
		assertEquals(tree.toString(),
				"			50:BF=0\n"
				+ "		45:BF=0\n"
				+ "			42:BF=0\n"
				+ "	40:BF=1\n"
				+ "		35:BF=0\n"
				+ "30:BF=0\n"
				+ "		25:BF=0\n"
				+ "	20:BF=-1\n"
				+ "		10:BF=-1\n"
				+ "			5:BF=0\n");
		assertTrue(tree.removeNode(40));
		assertEquals(tree.toString(),  //falla aquí
				"		50:BF=0\n"
				+ "	45:BF=-1\n"
				+ "			42:BF=0\n"
				+ "		35:BF=1\n"
				+ "30:BF=0\n"
				+ "		25:BF=0\n"
				+ "	20:BF=-1\n"
				+ "		10:BF=-1\n"
				+ "			5:BF=0\n");
		assertTrue(tree.removeNode(30));
		assertEquals(tree.toString(),
				"		50:BF=0\n"
				+ "	45:BF=-1\n"
				+ "			42:BF=0\n"
				+ "		35:BF=1\n"
				+ "25:BF=1\n"
				+ "		20:BF=0\n"
				+ "	10:BF=0\n"
				+ "		5:BF=0\n");
		assertFalse(tree.removeNode(0));
	}
	/**
	 * Prueba con otro árbol diferente para comprobar el borrado
	 */
	@Test
	void testAddAndRemove2() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(-4));
		assertTrue(tree.addNode(-2));
		assertTrue(tree.addNode(-3));
		assertTrue(tree.addNode(-1));
		assertTrue(tree.addNode(0));
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(5));
		
		assertEquals(tree.toString(),
				"			5:BF=0\n"
				+ "		4:BF=1\n"
				+ "	3:BF=0\n"
				+ "			2:BF=0\n"
				+ "		1:BF=0\n"
				+ "			0:BF=0\n"
				+ "-1:BF=1\n"
				+ "		-2:BF=0\n"
				+ "	-3:BF=0\n"
				+ "		-4:BF=0\n");
		assertTrue(tree.removeNode(5));
		assertEquals(tree.toString(),
				 "		4:BF=0\n"
				+ "	3:BF=-1\n"
				+ "			2:BF=0\n"
				+ "		1:BF=0\n"
				+ "			0:BF=0\n"
				+ "-1:BF=1\n"
				+ "		-2:BF=0\n"
				+ "	-3:BF=0\n"
				+ "		-4:BF=0\n");
		assertTrue(tree.removeNode(-1));
		
		assertEquals(tree.toString(),
				 "		4:BF=0\n"
				 + "	3:BF=-1\n"
				 + "			2:BF=0\n"
				 + "		1:BF=0\n"
				 + "			0:BF=0\n"
				 + "-2:BF=1\n"
				 + "	-3:BF=-1\n"
				 + "		-4:BF=0\n");
	}
	
	/**
	 * Prueba para comprobar el funcionamiento de RSD
	 */
	@Test
	void singleRightRotationTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"	3:BF=0\n"
				+ "2:BF=0\n"
				+ "	1:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RSI
	 */
	@Test
	void singleLeftRotationTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(1));
		assertEquals(tree.toString(),
				"	3:BF=0\n"
				+ "2:BF=0\n"
				+ "	1:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RDD
	 */
	@Test
	void doubleRightRotationTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"	4:BF=-1\n"
				+ "		3:BF=0\n"
				+ "2:BF=1\n"
				+ "	1:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RDI
	 */
	@Test
	void doubleLeftRotationTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(5));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(1));
		assertEquals(tree.toString(),
				"	5:BF=0\n"
				+ "3:BF=-1\n"
				+ "	2:BF=-1\n"
				+ "		1:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RSI en borrado
	 */
	@Test
	void singleLeftRotationRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(5));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(1));
		assertEquals(tree.toString(),
				"	5:BF=0\n"
				+ "3:BF=-1\n"
				+ "	2:BF=-1\n"
				+ "		1:BF=0\n");
		assertTrue(tree.removeNode(5));
		assertEquals(tree.toString(),
				"	3:BF=0\n"
				+ "2:BF=0\n"
				+ "	1:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RSD en borrado
	 */
	@Test
	void singleRightRotationRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(4));
		assertEquals(tree.toString(),
				"		4:BF=0\n"
				+ "	3:BF=1\n"
				+ "2:BF=1\n"
				+ "	1:BF=0\n");
		assertTrue(tree.removeNode(1));
		assertEquals(tree.toString(),
				"	4:BF=0\n"
				+ "3:BF=0\n"
				+ "	2:BF=0\n");
		
	}
	/**
	 * Prueba para comprobar el funcionamiento de RDD en borrado
	 */
	@Test
	void doubleRightRotationRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"	4:BF=-1\n"
				+ "		3:BF=0\n"
				+ "2:BF=1\n"
				+ "	1:BF=0\n");
		assertTrue(tree.removeNode(1));
		assertEquals(tree.toString(),
				"	4:BF=0\n"
				+ "3:BF=0\n"
				+ "	2:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento de RDI en borrado
	 */
	@Test
	void doubleLeftRotationRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(5));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"	5:BF=0\n"
				+ "4:BF=-1\n"
				+ "		3:BF=0\n"
				+ "	2:BF=1\n");
		assertTrue(tree.removeNode(5));
		assertEquals(tree.toString(),
				"	4:BF=0\n"
				+ "3:BF=0\n"
				+ "	2:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento del borrado con solo hijo derecho
	 */
	@Test
	void HijoDerechoRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(5));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"	5:BF=0\n"
				+ "4:BF=-1\n"
				+ "		3:BF=0\n"
				+ "	2:BF=1\n");
		assertTrue(tree.removeNode(2));
		assertEquals(tree.toString(),
				"	5:BF=0\n"
				+ "4:BF=0\n"
				+ "	3:BF=0\n");
	}
	/**
	 * Prueba para comprobar el funcionamiento del borrado con solo hijo izquierdo
	 */
	@Test
	void HijoIzquierdoRemoveTest() {
		AVLTree<Integer> tree=new AVLTree<>();
		assertTrue(tree.addNode(5));
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(4));
		assertEquals(tree.toString(),
				"	5:BF=-1\n"
				+ "		4:BF=0\n"
				+ "3:BF=0\n"
				+ "		2:BF=0\n"
				+ "	1:BF=1\n");
		assertTrue(tree.removeNode(5));
		assertEquals(tree.toString(),
				"	4:BF=0\n"
				+ "3:BF=-1\n"
				+ "		2:BF=0\n"
				+ "	1:BF=1\n");
	}

}

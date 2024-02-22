package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas para la clase BSTree
 * @author Miguel Méndez Murias
 *
 */
class BSTreeTest {

	/**
	 * Pruebas para el método addNode()
	 */
	@Test
	void testAddNode() {
		BSTree<Integer> tree=new BSTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertEquals(tree.toString(),
				"		3\n"
				+ "	2\n"
				+ "1\n");
		System.out.println(tree.toString());
		try {
			tree.addNode(null);
			fail();
		}catch(NullPointerException e) {}
	}
	/**
	 * Pruebas para el método searchNode()
	 */
	@Test
	void testSearch() {
		BSTree<Integer> tree=new BSTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(12));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(94));
		assertTrue(tree.addNode(93));
		assertEquals(4,tree.searchNode(4));
		assertEquals(2,tree.searchNode(2));
		assertEquals(1,tree.searchNode(1));
		assertEquals(3,tree.searchNode(3));
		assertEquals(12,tree.searchNode(12));
		assertEquals(94,tree.searchNode(94));
		assertEquals(93,tree.searchNode(93));
		assertEquals(null,tree.searchNode(37));
		assertNull(tree.searchNode(null));
	}
	
	/**
	 * Pruebas para el método removeNode()
	 */
	@Test
	void testRemove() {
		BSTree<Integer> tree=new BSTree<>();
		assertTrue(tree.addNode(-8));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(-88));
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(-1));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(12));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(94));
		assertTrue(tree.addNode(93));
		assertFalse(tree.removeNode(1111));
		assertEquals(tree.toString(),
				    "				94\n"
				  + "					93\n"
				  + "			12\n"
				  + "				4\n"
				  + "		3\n"
				  + "	2\n"
				  + "		1\n"
				  + "			-1\n"
				  + "-8\n"
				  + "	-88\n");
		assertTrue(tree.removeNode(93));
		assertEquals(tree.toString(),
			"				94\n"
			+ "			12\n"
			+ "				4\n"
			+ "		3\n"
			+ "	2\n"
			+ "		1\n"
			+ "			-1\n"
			+ "-8\n"
			+ "	-88\n");

		assertTrue(tree.removeNode(3));
		assertEquals(tree.toString(),
				"			94\n"
				+ "		12\n"
				+ "			4\n"
				+ "	2\n"
				+ "		1\n"
				+ "			-1\n"
				+ "-8\n"
				+ "	-88\n");
		assertTrue(tree.removeNode(12));
		assertEquals(tree.toString(),
				"			94\n"
				+ "		4\n"
				+ "	2\n"
				+ "		1\n"
				+ "			-1\n"
				+ "-8\n"
				+ "	-88\n");
		assertTrue(tree.removeNode(2));
		assertEquals(tree.toString(),
				"			94\n"
				+ "		4\n"
				+ "	1\n"
				+ "		-1\n"
				+ "-8\n"
				+ "	-88\n");
		assertTrue(tree.removeNode(-8));
		assertEquals(tree.toString(),
				"			94\n"
				+ "		4\n"
				+ "	1\n"
				+ "		-1\n"
				+ "-88\n");
		try {
			tree.removeNode(null);
			fail();
		}catch(NullPointerException e) {}
	}
	
	/**
	 * Pruebas para los recorridos
	 */
	@Test
	void testRecorridos() {
		BSTree<Integer> tree=new BSTree<>();
		assertTrue(tree.addNode(1));
		assertTrue(tree.addNode(2));
		assertTrue(tree.addNode(3));
		assertTrue(tree.addNode(12));
		assertTrue(tree.addNode(4));
		assertTrue(tree.addNode(94));
		assertTrue(tree.addNode(93));
		assertEquals(tree.preOrder(),"1\t2\t3\t12\t4\t94\t93\t");
		assertEquals(tree.postOrder(),"4\t93\t94\t12\t3\t2\t1\t");
		assertEquals(tree.inOrder(),"1\t2\t3\t4\t12\t93\t94\t");
	}


}

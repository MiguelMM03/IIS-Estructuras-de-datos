package p2Grafos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas para la clase Graph
 * @author Miguel Méndez Murias
 *
 */
class GraphTest {

	/**
	 * Pruebas para los grafos:
	 * 	1-Añadir nodos que cogen en el grafo. Devuelve True
	 * 	2-Añadir un nodo null. Lanza un NullPointerException
	 * 	3-Añadir un nodo que no coge en el grafo. Lanza una excepción FullStructureException
	 * 	4-Añadir nodos que ya existen. Devuelve False
	 * 	5-Comprobar posición de los nodos añadidos
	 * 	6-Comprobar que no existen los nodos que no se pudieron añadir
	 * 	7-Añadir aristas entre nodos que existen
	 * 	8-Añadir arista cuando no existe el nodo fuente
	 * 	9-Añadir arista cuando no existe el nodo destino
	 * 	10-Añadir aristas entre nodos que ya tienen una arista
	 * 	11-Obtener el peso de una arista existente
	 * 	12-Obtener el peso de una arista inexistente
	 * 	13-Obtener el peso cuando no existe un nodo de los dados
	 * 	14-Eliminar una arista existente
	 * 	15-Eliminar una arista inexistente
	 * 	16-Intentar obtener una arista que se ha borrado. Devuelve -1
	 * 	17-Comprueba la existencia de una arista que existe
	 * 	18-Comprueba la existencia de una arista que no existe
	 */
	@Test
	void testGraph() {
		Graph<Integer> g = new Graph<Integer>(3);
		assertTrue(g.addNode(1));
		assertTrue(g.addNode(2));
		assertTrue(g.addNode(-1));
		
		assertThrows(NullPointerException.class, () -> g.addNode(null));
		assertThrows(FullStructureException.class, () -> g.addNode(3));
		
		assertFalse(g.addNode(1));
		assertFalse(g.addNode(2));
		assertFalse(g.addNode(-1));
		
		assertEquals(g.getNode(1),0);
		assertEquals(g.getNode(2),1);
		assertEquals(g.getNode(-1),2);
		assertEquals(g.getNode(3),-1);
		assertFalse(g.existsNode(3));
		assertTrue(g.existsNode(1));
		
		assertTrue(g.addEdge(1, 2, 10));
		assertThrows(ElementNotPresentException.class, () -> g.addEdge(43, 2, 16));
		assertThrows(ElementNotPresentException.class, () -> g.addEdge(2, 42, 16));
		assertFalse(g.addEdge(1, 2, 4));
		
		assertEquals(g.getEdge(1, 2),10);
		assertEquals(g.getEdge(1, -1), -1);
		assertThrows(ElementNotPresentException.class, () -> g.getEdge(23, 2));
		
		assertTrue(g.removeEdge(1, 2));
		assertFalse(g.removeEdge(1, 2));
		assertEquals(g.getEdge(1, 2),-1);
		assertTrue(g.addEdge(1, 2, 3));
		assertTrue(g.existsEdge(1, 2));
		assertTrue(g.removeEdge(1, 2));
		assertFalse(g.existsEdge(1, 2));
		
	}

	/**
	 * Pruebas para el método removeNode()
	 */
	@Test
    void testRemoveNode() {
        Graph<Integer> g=new Graph<Integer>(5);
        int node=1;
        int node2=2;
        int node3=3;
        int node4=4;
        int node5=5;
        assertTrue(g.addNode(node));
        assertTrue(g.addNode(node2));
        assertTrue(g.addNode(node3));
        assertTrue(g.addNode(node4));
        assertTrue(g.addEdge(node,node2,12.0));
        assertTrue(g.addEdge(node2,node4,24.0));
        assertTrue(g.addEdge(node, node4, 14.0));
        assertTrue(g.addEdge(node2,node3,23.0));
        assertTrue(g.addEdge(node3,node,31.0));
        assertTrue(g.addEdge(node4,node,41.0));
        assertTrue(g.addEdge(node,node,11.0));
        assertTrue(g.addEdge(node2,node,21.0));
        assertTrue(g.addEdge(node2,node2,22.0));
        assertTrue(g.addEdge(node3,node3,33.0));
        assertTrue(g.addEdge(node,node3,13.0));
        assertTrue(g.addEdge(node3,node2,32.0));
        assertTrue(g.addEdge(node3,node4,34.0));
        assertTrue(g.addEdge(node4,node2,42.0));
        assertTrue(g.addEdge(node4,node3,43.0));
        assertTrue(g.addEdge(node4, node4, 44.0));
        assertThrows(NullPointerException.class, () -> g.removeNode(null));
        System.out.println(g.toString());
        assertTrue(g.removeNode(node));
        System.out.println(g.toString());
        assertTrue(g.addNode(node5));
        System.out.println(g.toString());
    }
	
	/**
	 * Pruebas para dijkstra()
	 */
	@Test
	public void testDijkstra() { //se compara el resultado con un ejercicio resuelto en clase
		 Graph<Integer> g=new Graph<Integer>(6);
	        int node=1;
	        int node2=2;
	        int node3=3;
	        int node4=4;
	        int node5=5;
	        int node6=6;
	        assertTrue(g.addNode(node));
	        assertTrue(g.addNode(node2));
	        assertTrue(g.addNode(node3));
	        assertTrue(g.addNode(node4));
	        assertTrue(g.addNode(node5));
	        assertTrue(g.addNode(node6));
	        
	        assertTrue(g.addEdge(node,node2,4.0));
	        assertTrue(g.addEdge(node2,node4,5.0));
	        assertTrue(g.addEdge(node, node3, 2.0));
	        assertTrue(g.addEdge(node3,node2,1.0));
	        assertTrue(g.addEdge(node3,node4,8.0));
	        assertTrue(g.addEdge(node4,node5,2.0));
	        
	        assertTrue(g.addEdge(node4,node6,6.0));
	        assertTrue(g.addEdge(node5,node6,2.0));
	        assertTrue(g.addEdge(node3, node5, 10.0));
	        DijkstraDataClass d=g.dijkstra(node);
	        assertArrayEquals(d.pDijkstra,new int[] {-1, 2, 0, 1, 3, 4});
	        assertArrayEquals(d.dDijkstra,new double[] {0.0, 3.0, 2.0, 8.0, 10.0, 12.0});
	        System.out.println(d.toString());
	}
	
	/**
	 * Pruebas para el método floyd()
	 */
	@Test
	public void testFloyd() {
		Graph<Integer> g=new Graph<Integer>(4);
		
		assertFalse(g.floyd()); //No hay nodos en el grafo
		
		assertTrue(g.addNode(1));
		assertTrue(g.addNode(2));
		assertTrue(g.addNode(3));
		assertTrue(g.addNode(4));
		assertTrue(g.floyd()); //Hay nodos aunque no aristas en el grafo
		
		/*la matriz A de floyd tiene que tener todo a infinito excepto la diagonal, que tiene que estar a 0,
		 * y la matriz P de floyd tiene que tener todos los valores a -1 (o a un número negativo), lo que en 
		 * el toString se representa como "-"
		 */
		System.out.println(g.toString()+"\n------------\n"); 
		assertTrue(g.removeNode(1));
		assertTrue(g.removeNode(2));
		assertTrue(g.removeNode(3));
		assertTrue(g.removeNode(4));
		
 
        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addEdge(1,2,2.0));
        assertTrue(g.addEdge(2,3,3.0));
        assertTrue(g.addEdge(1, 3, 8.0));
        assertTrue(g.addEdge(2,4,6.0));
        assertTrue(g.addEdge(3,4,1.0));
        assertTrue(g.addEdge(4,2,2.0));
        assertTrue(g.floyd()); //Hay nodos y aristas en el grafo
        System.out.println(g.toString()); //Se comprueba que coincida el resultado con un ejercicio resuelto aplicando Floyd
	}
	
	
	/**
	 * Pruebas para el método pathIterativo().
	 * Tiene que ejecutar floyd en la primera llamada a pathIterativo(), ya que no se ha ejecutado nunca
	 */
	@Test
	public void testPathIterativo() {
		Graph<Integer> g=new Graph<Integer>(5);
        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addNode(5));
        assertTrue(g.addEdge(1,2,2.0));
        assertTrue(g.addEdge(2,3,3.0));
        assertTrue(g.addEdge(1, 3, 8.0));
        assertTrue(g.addEdge(2,4,6.0));
        assertTrue(g.addEdge(3,4,1.0));
        assertTrue(g.addEdge(4,2,2.0));
        assertTrue(g.addEdge(4, 5, 4.5));
      //Se comprueba comparando con un ejercicio resuelto
        System.out.println(g.toString()); //se muestra en pantalla el grafo sin las matrices de floyd
        assertNull(g.aFloyd);
        assertEquals("1\t(2.0)\t2\t(3.0)\t3\t(1.0)\t4\t",g.pathIterativo(1, 4));
        assertNotNull(g.aFloyd);
        System.out.println("\n-------\n"+g.toString()); //se muestra en pantalla el grafo con las matrices de floyd
        assertEquals("2\t(3.0)\t3\t(1.0)\t4\t",g.pathIterativo(2, 4));
        assertEquals("2\t(3.0)\t3\t(1.0)\t4\t(4.5)\t5\t",g.pathIterativo(2, 5));
        assertEquals("4\t",g.pathIterativo(4, 4));
        assertEquals("4\t(Infinity)\t1\t",g.pathIterativo(4, 1));
        assertEquals("",g.pathIterativo(null, 4));
        assertEquals("",g.pathIterativo(1, null));
        assertEquals("",g.pathIterativo(null, null));
        //-----------------------------
        assertTrue(g.removeNode(1));
        assertTrue(g.removeNode(2));
        assertTrue(g.removeNode(3));
        assertTrue(g.removeNode(4));
        assertTrue(g.removeNode(5));
        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addNode(5));
        assertTrue(g.addEdge(1,2,1.0));
        assertTrue(g.addEdge(2,3,2.0));
        assertTrue(g.addEdge(3, 4, 3.0));
        assertTrue(g.addEdge(4,5,4));
      //Se comprueba comparando con un ejercicio resuelto
        System.out.println(g.toString()); //se muestra en pantalla el grafo sin las matrices de floyd
        assertNull(g.aFloyd);
        assertEquals("1\t(1.0)\t2\t(2.0)\t3\t(3.0)\t4\t",g.pathIterativo(1, 4));
        assertEquals("1\t(1.0)\t2\t(2.0)\t3\t(3.0)\t4\t(4.0)\t5\t",g.pathIterativo(1, 5));
        assertNotNull(g.aFloyd);
        System.out.println("\n-------\n"+g.toString()); //se muestra en pantalla el grafo con las matrices de floyd
        assertEquals("4\t(Infinity)\t1\t",g.pathIterativo(4, 1));
        assertEquals("5\t(Infinity)\t4\t",g.pathIterativo(5, 4));
        assertEquals("",g.pathIterativo(null, 4));
        assertEquals("",g.pathIterativo(1, null));
        assertEquals("",g.pathIterativo(null, null));
	}
	/**
	 * Pruebas para el método path().
	 * Tiene que ejecutar floyd en la primera llamada a path(), ya que no se ha ejecutado nunca
	 */
	@Test
	public void testPathRecursivo() { 
		Graph<Integer> g=new Graph<Integer>(5);
        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addNode(5));
        assertTrue(g.addEdge(1,2,2.0));
        assertTrue(g.addEdge(2,3,3.0));
        assertTrue(g.addEdge(1, 3, 8.0));
        assertTrue(g.addEdge(2,4,6.0));
        assertTrue(g.addEdge(3,4,1.0));
        assertTrue(g.addEdge(4,2,2.0));
        assertTrue(g.addEdge(4, 5, 4.5));
      //Se comprueba comparando con un ejercicio resuelto
        System.out.println(g.toString()); //se muestra en pantalla el grafo sin las matrices de floyd
        assertNull(g.aFloyd);
        assertEquals("1\t(2.0)\t2\t(3.0)\t3\t(1.0)\t4\t",g.path(1, 4));
        assertNotNull(g.aFloyd);
        System.out.println("\n-------\n"+g.toString()); //se muestra en pantalla el grafo con las matrices de floyd
        assertEquals("2\t(3.0)\t3\t(1.0)\t4\t",g.path(2, 4));
        assertEquals("2\t(3.0)\t3\t(1.0)\t4\t(4.5)\t5\t",g.path(2, 5));
        assertEquals("4\t",g.path(4, 4));
        assertEquals("4\t(Infinity)\t1\t",g.path(4, 1));
        assertEquals("",g.path(null, 4));
        assertEquals("",g.path(1, null));
        assertEquals("",g.path(null, null));
        //------------------------
        assertTrue(g.removeNode(1));
        assertTrue(g.removeNode(2));
        assertTrue(g.removeNode(3));
        assertTrue(g.removeNode(4));
        assertTrue(g.removeNode(5));
        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addNode(5));
        assertTrue(g.addEdge(1,2,1.0));
        assertTrue(g.addEdge(2,3,2.0));
        assertTrue(g.addEdge(3, 4, 3.0));
        assertTrue(g.addEdge(4,5,4));
      //Se comprueba comparando con un ejercicio resuelto
        System.out.println(g.toString()); //se muestra en pantalla el grafo sin las matrices de floyd
        assertNull(g.aFloyd);
        assertEquals("1\t(1.0)\t2\t(2.0)\t3\t(3.0)\t4\t",g.path(1, 4));
        assertEquals("1\t(1.0)\t2\t(2.0)\t3\t(3.0)\t4\t(4.0)\t5\t",g.path(1, 5));
        assertNotNull(g.aFloyd);
        System.out.println("\n-------\n"+g.toString()); //se muestra en pantalla el grafo con las matrices de floyd
        assertEquals("4\t(Infinity)\t1\t",g.path(4, 1));
        assertEquals("5\t(Infinity)\t4\t",g.path(5, 4));
        assertEquals("",g.path(null, 4));
        assertEquals("",g.path(1, null));
        assertEquals("",g.path(null, null));
	}
	
	
	/**
	 * Test para el método recorridoProfundidad()
	 */
	@Test
	public void testRecorridoProfundidad() {
		Graph<Integer> g=new Graph<Integer>(10);

        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
        assertTrue(g.addNode(5));
        assertTrue(g.addNode(6));
        assertTrue(g.addNode(7));
        assertTrue(g.addNode(8));
        assertTrue(g.addNode(9));
        assertTrue(g.addNode(10));
        assertTrue(g.addEdge(1,2,2.0));
        assertTrue(g.addEdge(2,8,3.0));
        assertTrue(g.addEdge(2, 3, 8.0));
        assertTrue(g.addEdge(2,4,6.0));
        assertTrue(g.addEdge(2,7,1.0));
        assertTrue(g.addEdge(7,8,2.0));
        assertTrue(g.addEdge(3,6,2.0));
        assertTrue(g.addEdge(3,5,3.0));
        assertTrue(g.addEdge(4, 9, 8.0));
        assertTrue(g.addEdge(9,10,6.0));
        assertTrue(g.addEdge(9,6,1.0));
        assertTrue(g.addEdge(5,6,2.0));
        assertTrue(g.addEdge(5,4,2.0));
      //Se comprueba comparando con un ejercicio resuelto
        assertEquals(g.recorridoProfundidad(4),"4\t9\t6\t10\t");
        System.out.println(g.recorridoProfundidad(4)); //no haría falta esto ya que se comprueba en el assertEquals de la línea anterior.
        //Es solo para ver en pantalla el resultado por si hubiera alguna diferencia
	}
	
	/**
	 * Test para el método minCosPath()
	 * Casos de uso:
	 * 	1-En un grafo con nodos pero sin aristas. El coste mínimo es infinito
	 * 	2-En un grafo con nodos y aristas
	 */
	@Test
	public void testMinCosPath() {
		Graph<Integer> g=new Graph<Integer>(4);
		
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		assertEquals(g.minCostPath(1, 2), Double.POSITIVE_INFINITY);
		g.removeNode(1);
		g.removeNode(2);
		g.removeNode(3);
		g.removeNode(4);

        assertTrue(g.addNode(1));
        assertTrue(g.addNode(2));
        assertTrue(g.addNode(3));
        assertTrue(g.addNode(4));
       
        assertTrue(g.addEdge(1,2,2.0));
        assertTrue(g.addEdge(2,3,3.0));
        assertTrue(g.addEdge(1, 3, 8.0));
        assertTrue(g.addEdge(2,4,6.0));
        assertTrue(g.addEdge(3,4,1.0));
        assertTrue(g.addEdge(4,2,2.0));
        
        assertEquals(g.minCostPath(1, 4),6);
        assertEquals(g.minCostPath(1, 2),2);
        assertEquals(g.minCostPath(1, 3),5);
        assertEquals(g.minCostPath(2, 4),4);
        assertEquals(g.minCostPath(3, 4),1);
        assertEquals(g.minCostPath(2, 3),3);
        assertEquals(g.minCostPath(2, 1),Double.POSITIVE_INFINITY);
	}

}

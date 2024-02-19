package p2Grafos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Graph_Simulacro_2022Test {

	/**
	 * Pruebas para el método floyd()
	 */
	@Test
	public void testFloyd() {
		Graph_Simulacro_2022<Integer> g=new Graph_Simulacro_2022<Integer>(4);
		
		assertFalse(g.floydPivots(new Integer[0])); //No hay nodos en el grafo
		
		assertTrue(g.addNode(0));
		assertTrue(g.addNode(1));
		assertTrue(g.addNode(2));
		assertTrue(g.addNode(3));
		assertTrue(g.floydPivots(new Integer[] {1})); //Hay nodos aunque no aristas en el grafo
		
		/*la matriz A de floyd tiene que tener todo a infinito excepto la diagonal, que tiene que estar a 0,
		 * y la matriz P de floyd tiene que tener todos los valores a -1 (o a un número negativo), lo que en 
		 * el toString se representa como "-"
		 */
		System.out.println(g.toString()+"\n------------\n"); 
		assertTrue(g.removeNode(1));
		assertTrue(g.removeNode(2));
		assertTrue(g.removeNode(3));
		assertTrue(g.removeNode(0));
		
		//no es necesaria la creación de siguientes variables. Es solo para llamarlas igual que en el ejercicio resuelto
        int nodeA=0;
        int nodeB=1;
        int nodeC=2;
        int nodeD=3;
        assertTrue(g.addNode(nodeA));
        assertTrue(g.addNode(nodeB));
        assertTrue(g.addNode(nodeC));
        assertTrue(g.addNode(nodeD));
        assertTrue(g.addEdge(nodeA,nodeB,2.0));
        assertTrue(g.addEdge(nodeB,nodeC,3.0));
        assertTrue(g.addEdge(nodeA, nodeC, 8.0));
        assertTrue(g.addEdge(nodeB,nodeD,6.0));
        assertTrue(g.addEdge(nodeC,nodeD,1.0));
        assertTrue(g.addEdge(nodeD,nodeB,2.0));
        assertTrue(g.floydPivots(new Integer[] {1})); //Hay nodos y aristas en el grafo
        System.out.println(g.toString()); //Se comprueba que coincida el resultado con un ejercicio resuelto aplicando Floyd (usando solo como nodo intermedio el 1}
	}
}

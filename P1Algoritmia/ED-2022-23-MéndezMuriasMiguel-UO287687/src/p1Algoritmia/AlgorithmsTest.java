package p1Algoritmia;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Clase para comprobar el buen funcionamiento de los algoritmos
 * @author Miguel Méndez Murias
 *
 */
class AlgorithmsTest {

	
	
	/**
	 * Pruebas para el factorial
	 * 	1-Caso base
	 * 	2-Número negativo
	 * 	3-Número positivo
	 * 	4-StackOverflowError
	 */
	@Test
	void testFactorial() {
		Algorithms a =new Algorithms();
		assertEquals(a.factorial(0), 1);
		try {
			a.factorial(-1);
			fail();
		}	
		catch(IllegalArgumentException e){
			assertEquals(e.getMessage(),"No permitidos parámetros negativos como -1");
			
		}
		//assertThrows(IllegalArgumentException.class, () -> {a.factorial(-1);}); 
		assertEquals(a.factorial(1), 1);
		assertEquals(a.factorial(5), 120);
		assertThrows(StackOverflowError.class, () -> {a.fibonacci(35000);});
	}
	
	/**
	 * Pruebas para fibonacci
	 * 	1-Casos base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-StackOverflowError
	 */

	@Test
	void testFibonacci() {
		Algorithms a =new Algorithms();
		assertEquals(a.fibonacci(0), 0);
		assertEquals(a.fibonacci(1), 1);
		assertEquals(a.fibonacci(2), 1);
		assertEquals(a.fibonacci(11), 89);
		assertThrows(IllegalArgumentException.class, () -> {a.fibonacci(-1);});
		assertThrows(StackOverflowError.class, () -> {a.fibonacci(35000);});
	}
	
	
	/**
	 * Pruebas potenciaRec(int a, int b)
	 * 	1-a negativo
	 * 	2-b negativo
	 * 	3-a=0
	 * 	4-b=0
	 * 	5-a y b valores válidos
	 */
	@Test
	void testPotenciaRec() {
		Algorithms a = new Algorithms();
		try {
			a.potenciaRec(-1, 1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.potenciaRec(1, -1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.potenciaRec(0, 45);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		assertEquals(a.potenciaRec(3, 0),1);
		assertEquals(a.potenciaRec(4, 5),1024);
		//assertThrows(StackOverflowError.class, () -> a.potenciaRec(2, 65));
	}
	
	/**
	 * Pruebas restoDivRec(int a, int b)
	 * 	1-a negativo
	 * 	2-b negativo
	 * 	3-b=0
	 * 	4-a<b
	 * 	5-a=b
	 * 	6-a>b
	 */
	@Test
	void testRestoDivRec(){
		Algorithms a = new Algorithms();
		try {
			a.restoDivRec(-1, 1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.restoDivRec(1, -1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.restoDivRec(1, 0);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		assertEquals(a.restoDivRec(5, 6),5);
		assertEquals(a.restoDivRec(6, 6),0);
		
		assertEquals(a.restoDivRec(8, 6),2);
		assertEquals(a.restoDivRec(11, 6),5);
	}
	/**
	 * Pruebas divEntRec(int a, int b)
	 * 	1-a negativo
	 * 	2-b negativo
	 * 	3-b=0
	 * 	4-a<b
	 * 	5-a=b
	 * 	6-a>b
	 */
	@Test
	void testDivEntRec(){
		Algorithms a = new Algorithms();
		try {
			a.divEntRec(-1, 1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.divEntRec(1, -1);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		try {
			a.divEntRec(1, 0);
		}catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No permitido alguno de los parámetros introducido");
		}
		assertEquals(a.divEntRec(5, 6),0);
		assertEquals(a.divEntRec(6, 6),1);
		
		assertEquals(a.divEntRec(8, 6),1);
		assertEquals(a.divEntRec(110, 6),18);
	}
	
	/**
	 * Pruebas de potencia de 2 recursivo 1
	 * 	1-Caso base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-Valor que supera el rango long
	 */
	
	@Test
	void testPow2Rec1() {
		Algorithms a =new Algorithms();
		
		assertEquals(a.pow2Rec1(0), 1);
		
		assertEquals(a.pow2Rec1(1), 2);
		assertEquals(a.pow2Rec1(2), 4);
		assertEquals(a.pow2Rec1(16), 65536);
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec1(-1);});
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec1(64);});
	}
	
	/**
	 * Pruebas de potencia de 2 recursivo 2
	 * 	1-Caso base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-Valor que supera el rango long
	 */
	
	@Test
	void testPow2Rec2() {
		Algorithms a =new Algorithms();
		
		assertEquals(a.pow2Rec2(0), 1);
		assertEquals(a.pow2Rec2(1), 2);
		
		assertEquals(a.pow2Rec2(2), 4);
		assertEquals(a.pow2Rec2(7), 128);
		assertEquals(a.pow2Rec2(12), 4096);
		assertEquals(a.pow2Rec2(15), 32768);
		assertEquals(a.pow2Rec2(16), 65536);
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec2(-1);});
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec2(64);});
	}
	/**
	 * Pruebas de potencia de 2 recursivo 3
	 * 	1-Caso base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-Valor que supera el rango long
	 */
	
	@Test
	void testpow2Rec3() {
		Algorithms a =new Algorithms();
		
		assertEquals(a.pow2Rec3(0), 1);
		
		assertEquals(a.pow2Rec3(1), 2);
		assertEquals(a.pow2Rec3(2), 4);
		assertEquals(a.pow2Rec3(16), 65536);
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec3(-1);});
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec3(64);});
	}
	/**
	 * Pruebas de potencia de 2 recursivo 4
	 * 	1-Caso base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-Valor que supera el rango long
	 */
	
	@Test
	void testpow2Rec4() {
		Algorithms a =new Algorithms();
		
		assertEquals(a.pow2Rec4(0), 1);
		
		assertEquals(a.pow2Rec4(1), 2);
		assertEquals(a.pow2Rec4(2), 4);
		assertEquals(a.pow2Rec4(16), 65536);
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec4(-1);});
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Rec4(64);});
	}
	/**
	 * Pruebas de potencia de 2 iterativa
	 * 	1-Caso base
	 * 	2-Caso válido
	 * 	3-Número negativo
	 * 	4-Valor que supera el rango long
	 */
	
	@Test
	void testpow2Iter() {
		Algorithms a =new Algorithms();
		
		assertEquals(a.pow2Iter(0), 1);
		
		assertEquals(a.pow2Iter(1), 2);
		assertEquals(a.pow2Iter(2), 4);
		assertEquals(a.pow2Iter(16), 65536);
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Iter(-1);});
		
		assertThrows(IllegalArgumentException.class, () -> {a.pow2Iter(64);});
	}
}

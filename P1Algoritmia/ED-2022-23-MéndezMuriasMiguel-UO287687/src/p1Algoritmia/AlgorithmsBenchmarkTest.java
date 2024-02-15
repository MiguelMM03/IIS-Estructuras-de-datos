package p1Algoritmia;

import org.junit.jupiter.api.Test;

/**
 * @version 2022-23
 */

class AlgorithmsBenchmarkTest {

	/**
	 * Test para el calculo del tiempo de ejecucion del algoritmo implementado en el metodo "miPrueba" de la clase "Algorithms"
	 */
	@Test
	void testLinearBenchmark() {
		AlgorithmsBenchmark ab = new AlgorithmsBenchmark();
		ab.testFinal("miPruebaLinear.txt", 0, 100, 5, "p1Algoritmia.Algorithms", "linear");
		ab.testFinal("miPruebaCuadratica.txt", 0, 30, 5, "p1Algoritmia.Algorithms", "quadratic");
		ab.testFinal("miPruebaCubica.txt", 0, 15, 5, "p1Algoritmia.Algorithms", "cubic");
		ab.testFinal("miPruebaPow2Iter.txt", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Iter");
		ab.testFinal("miPruebaPow2Rec1.txt", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec1");
		ab.testFinal("miPruebaPow2Rec2.txt", 0, 15, 5, "p1Algoritmia.Algorithms", "pow2Rec2");
		ab.testFinal("miPruebaPow2Rec3.txt", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec3");
		ab.testFinal("miPruebaPow2Rec4.txt", 0, 62, 5, "p1Algoritmia.Algorithms", "pow2Rec4");
		ab.testFinal("miPruebaFibonacci.txt", 0, 21, 5, "p1Algoritmia.Algorithms", "fibonacci");
		ab.testFinal("miPruebaFactorial.txt", 0, 100, 5, "p1Algoritmia.Algorithms", "factorial");
		ab.testFinal("miPruebaLogaritmica.txt", 0, 100, 5, "p1Algoritmia.Algorithms", "logarithmic");
		
	}

}

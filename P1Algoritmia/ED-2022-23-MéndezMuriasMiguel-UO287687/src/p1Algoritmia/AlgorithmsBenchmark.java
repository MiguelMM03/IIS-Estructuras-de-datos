package p1Algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Nestor
 * @version 2022-23
 */
public class AlgorithmsBenchmark {

	public long testAlgorithm(String name, String methodName, int n) {
		long tInicial = 0, tFinal = 0;

		Class<?> cl;
		try {
			cl = Class.forName(name);
			Object o = cl.getDeclaredConstructor().newInstance();
			Method m = cl.getMethod(methodName, int.class);

			tInicial = System.currentTimeMillis();
			m.invoke(o, n);
			tFinal = System.currentTimeMillis();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tFinal - tInicial;
	}

	/**
	 * @param output       Nombre del fichero de salida
	 * @param startN       Valor inicial del parametro en las pruebas
	 * @param endN         Valor final del parametro en la pruebas
	 * @param times        Numero de veces que se ejecutara la prueba del metodo
	 *                     para cada valor del parametro
	 * @param nombreClase  Clase que tiene el metodo que queremos medir
	 * @param nombreMetodo Metodo que queremos medir
	 */
	public void testFinal(String output, int startN, int endN, int times, String nombreClase, String nombreMetodo) {
		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int n = startN; n <= endN; n++) {
				long millis = 0;
				for (int j = 0; j < times; j++) {
					millis += testAlgorithm(nombreClase, nombreMetodo, n);
				}
				pw.println(n + ", " + (millis / times));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}

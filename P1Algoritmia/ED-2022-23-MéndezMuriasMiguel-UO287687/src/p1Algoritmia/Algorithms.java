package p1Algoritmia;


/**
 * Clase que contiene algoritmos
 * @author Miguel Méndez Murias
 *
 */
public class Algorithms {
	/**Indica el tiempo de espera al ejecutar el método doNothing()*/
	private static final long SLEEP_TIME = 1;

	/**
	 * Método que espera un tiempo predifinido, pero no ejecuta ninguna otra tarea
	 */
	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calcula el factorial de un número no negativo de forma negativa
	 * @param n El número del que se quiere calcular el factorial
	 * @return El factorial de ese número, calculado de forma recursiva
	 */

	public long factorial (int n) { // Calcula el factorial de n>=0 de forma recursiva 

		if (n<0) 
			throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n);
		else if(n==0)
			return 1;
		else {
			doNothing();
			return n*factorial(n-1);
		} 

	} 
	
	/**
	 * Calcula el término n-ésimo de la serie de fibonacci de forma recursiva
	 * @param n El n-ésimo
	 * @return El valor que ocupa esa posición
	 */
	public int fibonacci (int n) { // Calcula el termino n-esimo teniendo en cuenta que el primer término se considera fib(0)=0; y fib(1)=1 

		if (n<0) 
			throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
		else if(n==0)
			return 0;
		else if(n==1) {
			doNothing();
			return 1;
		}
		else {
			doNothing();
			return (fibonacci(n-1)+fibonacci(n-2));
		}
	} 
	
	/**
	 * Calcula la potencia 2^n de forma recursiva
	 * @param n El valor al que se va a elevar el 2. No puede ser mayor que 62
	 * @return El resultado de la operación.
	 */
	public long pow2Rec1(int n) {  // calcula 2 elevado a n de forma recursiva 2^n = 2*2^(n-1) 

		if (n<0) 
			throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 

		else if(n==0) {
			return 1;
		}
		else if(n>63) { //con 2^63 también da error porque es un número negativo
			throw new IllegalArgumentException("No permitidos valores mayores que 63");
		}
		else {
			doNothing();
			return 2*(pow2Rec1(n-1));
		}

	} 
	
	/**
	 * Calcula el primer valor dado como paramétro elevado a el segundo valor dado como parámetro
	 * @param a El primer valor
	 * @param b El exponente del valor anterior
	 * @return El resultado de esa potencia. No da resultados superiores a 2^63-1
	 */
	public long potenciaRec(int a, int b) { // Calcula a elevado a b de forma recursiva por potencias sucesivas 

		if (a<=0 || b<0) 
			throw new IllegalArgumentException("No permitido alguno de los parámetros introducido"); 
		else if(b==0) {
			return 1;
		}
		else if(b==1) {
			return a;
		}
		else {
			return a*potenciaRec(a,b-1);
		} 

	} 

		 
	/**
	 * Calcula el resto de la división de los parámetros dado
	 * @param a El dividendo
	 * @param b El divisor
	 * @return El resto de la división
	 */
	public int restoDivRec (int a, int b) {  // calcula el resto de la división a/b de forma recursiva 

		if (a<0 || b<=0) 
			throw new IllegalArgumentException("No permitido alguno de los parámetros introducido"); 
		else if(a<b) {
			return a;
		}
		else {
			return restoDivRec(a-b,b);
		}
	} 

	/**
	 * Calcula la división entera de los parámetros dados de forma recursiva
	 * @param a El dividendo
	 * @param b El divisor
	 * @return El resultado de la división
	 */
	public int divEntRec (int a, int b) {  // calcula la división entera a/b de forma recursiva 

		if (a<0 || b<=0) 
			throw new IllegalArgumentException("No permitido alguno de los parámetros introducido"); 
		else if(a<b) {
			return 0;
		}
		else {
			return 1+divEntRec(a-b,b);
		}
	} 

	/*
		public int invertirEnteroRec(int n) {  // Invierte un entero invertirEnteroRec(1234)=4321 de forma recursiva 

		return 0; 

		  

		} 

		public String invertirStringRec(String l) { // Invierte un string de forma recursiva invertirStringRec("abc")="cba" 

		return "POR HACER"; 

		  

		} 

		public boolean esSimetrica(int[][] m) { // Indica si es simétrica una matriz cuadrada de forma recursiva 

		return false; 

		} 
		*/
		
		/**
		 * Algoritmo con una complejidad temporal lineal 
		 * @param n a mayor n, más tiempo tarda en ejecutarse, pero sigue una relación lineal
		 */
		public void linear (int n) {

			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			
			while(n>0) {
				n--;
				doNothing();
			}
		} 

			 

		/**
		 * Algoritmo con una complejidad temporal cuadrática 
		 * @param n a mayor n, más tiempo tarda en ejecutarse, pero sigue una relación cuadrática
		 */
		public void quadratic(int n) {

			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			while(n>0) {
				for(int i=n;i>0;i--) {
					doNothing();
				}
				n--;
			}
		} 

		  
		/**
		 * Algoritmo con una complejidad temporal cúbica 
		 * @param n a mayor n, más tiempo tarda en ejecutarse, pero sigue una relación cúbica
		 */
		public void cubic(int n) { 

			if (n<0) 
					throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			while(n>0) {
				for(int i=n;i>0;i--) {
					for(int j=n;j>0;j--) {
						doNothing();
					}
				}
				n--;
			}

		} 

		 

		/**
		 * Algoritmo con una complejidad temporal logarítmica en cualquier base 

		 * @param n a mayor n, más tiempo tarda en ejecutarse, pero sigue una relación logarítmica
		 */
		public void logarithmic(int n) {
			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			else if(n<1) {
				
			}
			else {
				doNothing();
				logarithmic(n/2);
			}

		} 

		 
		/**
		 * Calcula 2 elevado a n de forma iterativa 
		 * @param n El exponente
		 * @return el resultado de la potencia
		 */
		public long pow2Iter (int n) {  

		    if (n<0) 
		    	throw new IllegalArgumentException("No permitidos exponentes negativos como "+ n);
		    else if(n>63) { //con 2^63 también da error porque es un número negativo
				throw new IllegalArgumentException("No permitidos valores mayores que 63");
			}
		    long result=1;
		    for(;n>0;n--) {
		    	result=2*result;
		    	doNothing();
		    }
		    return result; 
		} 

		  
		/**
		 * calcula 2 elevado a n de forma recursiva 2^n = 2^(n-1)+2^(n-1) 
		 * @param n El exponente
		 * @return el resultado de la potencia
		 */
		public long pow2Rec2 (int n) {
			
			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			else if(n>63) { //con 2^63 también da error porque es un número negativo
				throw new IllegalArgumentException("No permitidos valores mayores que 63");
			}
			else if(n==0) {
				return 1;
			}
			else if(n==1) {
				return 2;
			}
			else {
				doNothing();
				return pow2Rec2(n-1)+pow2Rec2(n-1);
			} 

		} 

		/**
		 * calcula 2 elevado a n de forma recursiva 2^n = 2^(n/2)*2^(n/2) si n par y *2 si es impar 
		 * @param n El exponente
		 * @return el resultado de la potencia
		 */
		public long pow2Rec3 (int n) {

			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			else if(n>63) { //con 2^63 también da error porque es un número negativo
				throw new IllegalArgumentException("No permitidos valores mayores que 63");
			}
			else if(n==0) {
				return 1;
			}
			else if(n%2==0) {
				doNothing();
				return pow2Rec3(n/2)*pow2Rec3(n/2);
			}
			else{
				doNothing();
				return 2*pow2Rec3(n/2)*pow2Rec3(n/2);
			}

		} 

		/**
		 * calcula 2 elevado a n de forma recursiva 2^n = como pow2Rec3 pero sin repetir llamada 
		 * @param n El exponente
		 * @return el resultado de la potencia
		 */
		public long pow2Rec4 (int n) {

			if (n<0) 
				throw new IllegalArgumentException("No permitidos parámetros negativos como "+ n); 
			else if(n>63) { //con 2^63 también da error porque es un número negativo
				throw new IllegalArgumentException("No permitidos valores mayores que 63");
			}
			else if(n==0) {
				return 1;
			}
			else if(n%2==0) {
				doNothing();
				return (long) Math.pow(pow2Rec4(n/2),2);
			}
			else{
				doNothing();
				return (long) (2*Math.pow(pow2Rec4(n/2),2));
			}

		} 
	
	
}

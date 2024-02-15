package p1Algoritmia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import evalNestor.Alumno;

class EvContinuaSesion2Inicial {

	@Test
	void testAFichero() {
		int[] param= {0,20,100,
				0,1,5,25,
				0,1,5,10,20,
				0,1,5,10,20,
				0,1,10,15,20,27,
				0,1,10,15,103,110};
		Algorithms alg=new Algorithms();
		int i=0;
		FileWriter file = null;
		PrintWriter pw;
		Alumno al= new Alumno();
		
		try {
			file = new FileWriter(al.getNombreFicheroAlumno()+".log");
			pw = new PrintWriter(file);
			LocalDateTime ahora= LocalDateTime.now();
			pw.println("Alumno:"+al.getNombreFicheroAlumno());
			pw.println("TimeStamp: "+InetAddress.getLocalHost()+" "+ahora);

			pw.println("\nFactoriales:\nfactorial("+param[i]+"): "+alg.factorial(param[i++]));
			pw.println("factorial("+param[i]+"): "+alg.factorial(param[i++]));
			pw.println("factorial("+param[i]+"): "+alg.factorial(param[i++]));
	
			pw.println("\nFibonacci:\nfibonacci("+param[i]+"): "+alg.fibonacci(param[i++]));
			pw.println("fibonacci("+param[i]+"): "+alg.fibonacci(param[i++]));
			pw.println("fibonacci("+param[i]+"): "+alg.fibonacci(param[i++]));
			pw.println("fibonacci("+param[i]+"): "+alg.fibonacci(param[i++]));
			
			pw.println("\nPotencias de 2 (Rec1):\npow2Rec1("+param[i]+"): "+alg.pow2Rec1(param[i++]));
			pw.println("pow2Rec1("+param[i]+"): "+alg.pow2Rec1(param[i++]));
			pw.println("pow2Rec1("+param[i]+"): "+alg.pow2Rec1(param[i++]));
			pw.println("pow2Rec1("+param[i]+"): "+alg.pow2Rec1(param[i++]));
			pw.println("pow2Rec1("+param[i]+"): "+alg.pow2Rec1(param[i++]));
			
			pw.println("\nPotencias:\npotenciaRec(2,"+param[i]+"): "+alg.potenciaRec(2,param[i++]));
			pw.println("potenciaRec(2,"+param[i]+"): "+alg.potenciaRec(2,param[i++]));
			pw.println("potenciaRec(2,"+param[i]+"): "+alg.potenciaRec(2,param[i++]));
			pw.println("potenciaRec(2,"+param[i]+"): "+alg.potenciaRec(2,param[i++]));
			pw.println("potenciaRec(2,"+param[i]+"): "+alg.potenciaRec(2,param[i++]));
	
			pw.println("\nRestos:\nrestoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
			pw.println("restoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
			pw.println("restoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
			pw.println("restoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
			pw.println("restoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
			pw.println("restoDivRec("+param[i]+",10): "+alg.restoDivRec(param[i++],10));
				
			pw.println("\nDivisiones enteras:\ndivEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
			pw.println("divEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
			pw.println("divEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
			pw.println("divEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
			pw.println("divEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
			pw.println("divEntRec("+param[i]+",10): "+alg.divEntRec(param[i++],10));
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

	@Test
	void testNegativos() {
		Algorithms alg=new Algorithms();
		assertThrows(IllegalArgumentException.class, () -> {alg.factorial(-1);});
		assertThrows(IllegalArgumentException.class, () -> {alg.fibonacci(-2);});
		assertThrows(IllegalArgumentException.class, () -> {alg.pow2Rec1(-3);});
		assertThrows(IllegalArgumentException.class, () -> {alg.potenciaRec(0,0);});
		assertThrows(IllegalArgumentException.class, () -> {alg.potenciaRec(0,-1);});
		assertThrows(IllegalArgumentException.class, () -> {alg.potenciaRec(-1,1);});
		assertThrows(IllegalArgumentException.class, () -> {alg.restoDivRec(5,0);});
		assertThrows(IllegalArgumentException.class, () -> {alg.divEntRec(6,0);});
	}
	
	@Test
	void testLimites() {
		Algorithms alg=new Algorithms();
		assertEquals(2432902008176640000L,alg.factorial(20)); // No hay overflow en factorial(20)
		assertTrue(alg.factorial(21)<0); // overflow en factorial(21)
		assertEquals(1836311903,alg.fibonacci(46)); // No hay overflow en fibonacci(46)
		assertTrue(alg.fibonacci(47)<1836311903); // Hay overflow en fibonacci(47) que es menor que fibonacci(46)
		assertEquals(4611686018427387904L,alg.pow2Rec1(62)); // No hay overflow en pow2Rec1(62)
		assertTrue(alg.pow2Rec1(63)<4611686018427387904L); // Hay overflow en pow2Rec1(63) que es menor que pow2Rec1(62)

	}
	
	
}

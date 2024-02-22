package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import evalNestor.Alumno;

class AVLTreeTestSesion9_SLR {
	
	LocalDateTime localTime = LocalDateTime.now();
	int hora  = localTime.getHour();
	
	@Test
	void testSesion9() {
		Alumno al = new Alumno();

		FileWriter file = null;
		PrintWriter pw;
		try
		{
			file = new FileWriter(al.getNombreFicheroAlumno()+"-AVLTree-ADD-SLR.txt",true);
			pw = new PrintWriter(file);
			
			pw.println("Fecha y hora: "+localTime.toString());
			
			sesion9(pw);
			
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


 
	void sesion9(PrintWriter pw) { 
		Integer[] nodos=new Integer[]{100,90,80,70,60,50,40,30,20,10}; 
		AVLTree<Integer> t; 

		t = new AVLTree<Integer> (); 

		for (int i=0; i<nodos.length;i++) { 
			assertTrue(t.addNode(nodos[i]),"Falla addNode("+nodos[i]+")"); 
			pw.println("Arbol:"+(i+1)+"\n"+t);
			System.out.println("Arbol:"+(i+1)+"\n"+t); 
		} 
	} 
}

package p2Grafos;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import evalNestor.Alumno;

public class PruebaBasicaGrafos_2022_23_Retroalimentacion {

	@Test
	public void mainTest() {
		Alumno al = new Alumno();

		FileWriter file = null;
		PrintWriter pw;

		try
		{
			file = new FileWriter(al.getNombreFicheroAlumno()+"-BasicaGrafos-1.txt",true);
			pw = new PrintWriter(file);
			try {
				pruebaBasicaGrafos_Nodes(pw);
			} catch (Exception e) {
				e.printStackTrace();
				pw.println(e.getMessage());
				pw.println("Termina la prueba basica de nodos con algún error...\n");
			}
			try {
				pruebaBasicaGrafos_Edges(pw);
			} catch (Exception e) {
				e.printStackTrace();
				pw.println(e.getMessage());
				pw.println("Termina la prueba basica de aristas con algún error...\n");
			}
			try {
				pruebaBasicaGrafos_Evolucion(pw);
			} catch (Exception e) {
				e.printStackTrace();
				pw.println(e.getMessage());
				pw.println("Termina la prueba basica de evolucion con algún errro...");
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

	static public void pruebaBasicaGrafos_Nodes(PrintWriter pw){
		Graph<Integer> g=new Graph<Integer>(3);
		int linea=1;
		pw.println("Empieza la prueba basica de nodos: ");		
		pw.println("g=new Graph<Integer>(3): ");		

		pw.println((linea++)+" g.existsNode(1): "+g.existsNode(1)); // No existe    1
		pw.println((linea++)+" g.existsNode(2): "+g.existsNode(2));	// No existe	2
		pw.println((linea++)+" g.addNode(1): "+g.addNode(1));	// 3
		pw.println((linea++)+" g.addNode(2): "+g.addNode(2));	// 4
		pw.println((linea++)+" g.existsNode(1): "+g.existsNode(1));	// 5
		pw.println((linea++)+" g.existsNode(2): "+g.existsNode(2)); // 6
		pw.println((linea++)+" g.addNode(1): "+g.addNode(1));  // Ya existe   7
		pw.println((linea++)+" g.addNode(2): "+g.addNode(2));	// Ya existe   8
		pw.println((linea++)+" g.addNode(3): "+g.addNode(3));	//	9
		try {
			g.addNode(4);
		} catch (Exception e) {
			pw.println((linea++)+" g.addNode(4): "+e.getMessage());  // no cabe  // 10
		}
		pw.println((linea++)+" g.existsNode(4): "+g.existsNode(4));	// 11
		pw.println((linea++)+" g.existsNode(5): "+g.existsNode(5)); // 12

		pw.println("Termina la prueba basica de nodos.\n");

	}
	
	static public void pruebaBasicaGrafos_Edges(PrintWriter pw){
		Graph<Integer> g=new Graph<Integer>(3);
		int linea=1;
		pw.println("Empieza la prueba basica de aristas: ");		
		pw.println("g=new Graph<Integer>(3): ");		

		pw.println((linea++)+" g.existsEdge(1,2): "+g.existsEdge(1,2)); // No existe   
		try {
			g.getEdge(1,2);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(1,2) --> "+e.getMessage()); // No existe   
		}
		pw.println((linea++)+" g.addNode(1): "+g.addNode(1));	// 0
		pw.println((linea++)+" g.existsEdge(1,2): "+g.existsEdge(1,2)); // No existe   
		try {
			g.getEdge(1,2);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(1,2) --> "+e.getMessage()); // No existe   
		}
		pw.println((linea++)+" g.existsEdge(2,1): "+g.existsEdge(2,1)); // No existe   
		try {
			g.getEdge(2,1);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(2,1) --> "+e.getMessage()); // No existe   
		}
		pw.println((linea++)+" g.addNode(2): "+g.addNode(2));	// 0
		pw.println((linea++)+" g.existsEdge(1,2): "+g.existsEdge(1,2));	// -4
		pw.println((linea++)+" g.getEdge(1,2) --> "+g.getEdge(1,2));	// -4
		pw.println((linea++)+" g.addEdge(1,2,12.12): "+g.addEdge(1,2,12.12));  // 0
		pw.println((linea++)+" g.addEdge(1,2,12.1212): "+g.addEdge(1,2,12.1212));  // -4
		pw.println((linea++)+" g.addNode(3): "+g.addNode(3));	//	0
		pw.println((linea++)+" g.existsEdge(1,3): "+g.existsEdge(1,3));	// -2
		pw.println((linea++)+" g.existsEdge(3,2): "+g.existsEdge(3,2));	// -1
		pw.println((linea++)+" g.getEdge(1,3) --> "+g.getEdge(1,3));	// -4
		pw.println((linea++)+" g.getEdge(3,2) --> "+g.getEdge(3,2));	// -4
		for (int i=1; i<=3;i++)
			for (int j=1; j<=3; j++) {
				double peso=(10*i+j+(i/10d)+(j/100d));
				pw.println((linea++)+" g.addEdge("+i+","+j+","+i+j+"."+i+j+"): "+g.addEdge(i,j,peso)); 
			}
		for (int i=1; i<=3;i++)
			for (int j=1; j<=3; j++) {
				pw.println((linea++)+" g.addEdge("+i+","+j+","+i+j+"."+i+j+"): "+g.addEdge(i,j,(10*i+j))); 
			}

		for (int i=1; i<=3;i++)
			for (int j=1; j<=3; j++) {
				pw.println((linea++)+" g.getEdge("+i+","+j+") --> "+g.getEdge(i,j)); 
			}

		pw.println("Termina la prueba basica de aristas.\n");

	}

	
	static public void pruebaBasicaGrafos_Evolucion(PrintWriter pw){
		Graph<Integer> g=new Graph<Integer>(3);
		int linea=1;
		pw.println("Empieza la prueba basica de evolucion: ");		
		pw.println((linea++)+" g.existsNode(1): "+g.existsNode(1)); // No existe    1
		pw.println((linea++)+" g.existsNode(2): "+g.existsNode(2));	// No existe	2
		pw.println((linea++)+" g.addNode(1): "+g.addNode(1));	// 3
		pw.println((linea++)+" g.addNode(2): "+g.addNode(2));	// 4
		pw.println((linea++)+" g.existsNode(1): "+g.existsNode(1)); // 5
		pw.println((linea++)+" g.existsNode(2): "+g.existsNode(2)); // 6
		pw.println((linea++)+" g.addNode(1): "+g.addNode(1));  // Ya existe   7
		pw.println((linea++)+" g.addNode(2): "+g.addNode(2));	// Ya existe   8
		pw.println((linea++)+" g.addEdge(1,2, 1.2): "+g.addEdge(1,2, 1.2));
		pw.println((linea++)+" g.addEdge(2,1, 2.1): "+g.addEdge(2,1, 2.1));
		pw.println((linea++)+" g.addEdge(1,1, 1.1): "+g.addEdge(1,1, 1.1));  // 11
		
		pw.println((linea++)+" g.getEdge(1,2) --> "+g.getEdge(1,2));   // 12
		pw.println((linea++)+" g.getEdge(2,1) --> "+g.getEdge(2,1));  // 13
		pw.println((linea++)+" g.getEdge(1,1) --> "+g.getEdge(1,1)); // 14
		try {
			g.getEdge(2,3);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(2,3) --> "+e.getMessage()); // No existe  // 15   
		}
		
		pw.println((linea++)+" g.existsEdge(1,2): "+g.existsEdge(1,2));   // 16
		pw.println((linea++)+" g.existsEdge(2,1): "+g.existsEdge(2,1));
		pw.println((linea++)+" g.existsEdge(1,1): "+g.existsEdge(1, 1));
		pw.println((linea++)+" g.existsEdge(2,2): "+g.existsEdge(2,2));  // No existe   // 19
		
		pw.println((linea++)+" g.removeEdge(2,2): "+g.removeEdge(2,2));  // No existe  // 20
		pw.println((linea++)+" g.addEdge(2,2,2.2): "+g.addEdge(2,2,2.2));  
		pw.println((linea++)+" g.getEdge(2,2) --> "+g.getEdge(2,2));
		pw.println((linea++)+" g.existsEdge(2,2): "+g.existsEdge(2,2));
		pw.println((linea++)+" g.removeEdge(2,2): "+g.removeEdge(2,2));  // 24
		
		pw.println((linea++)+" g.removeNode(3): "+g.removeNode(3));   // No existe   // 25
		pw.println((linea++)+" g.addNode(3): "+g.addNode(3));
		try {
			g.addNode(4);
		} catch (Exception e) {
			pw.println((linea++)+" g.addNode(4): "+e.getMessage());  // no cabe  // 27
		}
		
		pw.println((linea++)+" g.getEdge(1, 3) --> "+g.getEdge(1, 3));  // No existe la arista  // 28
		pw.println((linea++)+" g.addEdge(1,3, 1.3): "+g.addEdge(1,3, 1.3));
		pw.println((linea++)+" g.existsEdge(1, 3): "+g.existsEdge(1, 3));
		pw.println((linea++)+" g.getEdge(1, 3) --> "+g.getEdge(1, 3));   // 31
		
		try {
			g.getEdge(1,4);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(1,4) --> "+e.getMessage()); // No existe  // 32   
		}
		try {
			g.getEdge(5,1);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(5,1) --> "+e.getMessage()); // No existe  // 33   
		}
		try {
			g.getEdge(5,4);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(5,4) --> "+e.getMessage()); // No existe  // 34   
		}
	
		pw.println((linea++)+" g.removeNode(3): "+g.removeNode(3));   // 35
		pw.println((linea++)+" g.removeNode(3): "+g.removeNode(3));  // No existe, recien borrado
		pw.println((linea++)+" g.existsEdge(1, 3): "+g.existsEdge(1, 3));  // No existe
		try {
			g.getEdge(1,3);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(1,3) --> "+e.getMessage()); // No existe  // 38   
		}

		pw.println((linea++)+" g.removeNode(2): "+g.removeNode(2));   // 39
		pw.println((linea++)+" g.removeNode(2): "+g.removeNode(2));  // No existe, recien borrado
		pw.println((linea++)+" g.existsEdge(1, 1): "+g.existsEdge(1, 1));   // 41
		
		pw.println((linea++)+" g.removeNode(1): "+g.removeNode(1));  // 42
		pw.println((linea++)+" g.existsNode(1): "+g.existsNode(1));
		pw.println((linea++)+" g.existsNode(2): "+g.existsNode(2));
		pw.println((linea++)+" g.existsNode(3): "+g.existsNode(3));   // 45  
		
		pw.println((linea++)+" g.existsNode(4): "+g.existsNode(4));  // 46
		pw.println((linea++)+" g.addNode(4): "+g.addNode(4));
		pw.println((linea++)+" g.existsNode(4): "+g.existsNode(4));  // 48

		pw.println((linea++)+" g.addEdge(4,4, 4.4): "+g.addEdge(4,4, 4.4));  // 49
		pw.println((linea++)+" g.existsEdge(4, 4): "+g.existsEdge(4, 4));
		pw.println((linea++)+" g.getEdge(4, 4) --> "+g.getEdge(4, 4));    // 51

		pw.println((linea++)+" g.removeNode(4): "+g.removeNode(4));   // 52
		pw.println((linea++)+" g.removeNode(4): "+g.removeNode(4));  // No existe, recien borrado
		pw.println((linea++)+" g.existsEdge(4, 4): "+g.existsEdge(4, 4));   //54
		
		pw.println((linea++)+" g.addNode(7): "+g.addNode(7));	//55

		pw.println((linea++)+" g.removeNode(2): "+g.removeNode(2));   // 56
		pw.println((linea++)+" g.removeNode(3): "+g.removeNode(3));   // 57
		pw.println((linea++)+" g.removeNode(4): "+g.removeNode(4));   // 58
		
		pw.println((linea++)+" g.existsEdge(7, 7): "+g.existsEdge(7, 7));	// 59
		pw.println((linea++)+" g.addEdge(7,7, 7.7): "+g.addEdge(7,7, 7.7));  // 60
		pw.println((linea++)+" g.addEdge(7,7, 17.17): "+g.addEdge(7,7, 17.17));  // 61  
		pw.println((linea++)+" g.getEdge(7, 7) --> "+g.getEdge(7, 7));    // 62
		
		pw.println((linea++)+" g.addNode(8): "+g.addNode(8));
		pw.println((linea++)+" g.addNode(9): "+g.addNode(9));

		pw.println((linea++)+" g.existsEdge(7, 8): "+g.existsEdge(7, 8));	// 65
		pw.println((linea++)+" g.existsEdge(8, 7): "+g.existsEdge(8, 7));	// 66
		pw.println((linea++)+" g.existsEdge(8, 8): "+g.existsEdge(8, 8));	// 67
		pw.println((linea++)+" g.existsEdge(8, 9): "+g.existsEdge(8, 9));	// 68
		pw.println((linea++)+" g.existsEdge(9, 8): "+g.existsEdge(9, 8));	// 69
		pw.println((linea++)+" g.existsEdge(9, 9): "+g.existsEdge(9, 9));	// 70
		
		pw.println((linea++)+" g.addEdge(7,8, 7.8): "+g.addEdge(7,8, 7.8));  // 71
		pw.println((linea++)+" g.addEdge(8,7, 8.7): "+g.addEdge(8,7, 8.7)); // 72
		pw.println((linea++)+" g.addEdge(8,8, 8.8): "+g.addEdge(8,8, 8.8)); // 73
		
		pw.println((linea++)+" g.addEdge(8,9, 8.9): "+g.addEdge(8,9, 8.9));  
		pw.println((linea++)+" g.addEdge(9,8, 9.8): "+g.addEdge(9,8, 9.8));
		pw.println((linea++)+" g.addEdge(9,9, 9.9): "+g.addEdge(9,9, 9.9));
		
		pw.println((linea++)+" g.addEdge(7,9, 7.9): "+g.addEdge(7,9, 7.9));
		pw.println((linea++)+" g.addEdge(9,7, 9.7): "+g.addEdge(9,7, 9.7)); // 78
		
		for (int i=7;i<=9;i++)
			for (int j=7; j<=9;j++)
				pw.println((linea++)+" g.getEdge("+i+", "+j+") --> "+g.getEdge(i, j)); // 79 al 87
		
		pw.println((linea++)+" g.removeNode(7): "+g.removeNode(7));  // 88
		pw.println((linea++)+" g.removeNode(7): "+g.removeNode(7));  // 89

		for (int i=7;i<=9;i++)
			for (int j=7; j<=9;j++)
				try {
					g.getEdge(i,j);
				} catch (Exception e) {
					pw.println((linea++)+" g.getEdge("+i+", "+j+") --> "+e.getMessage()); // 90 al 98  
				}
		
		try {
			g.removeNode(null);
		} catch (Exception e) {
			pw.println((linea++)+" g.removeNode(null): "+e.getMessage()); // 99  
		}
		try {
			g.addNode(null);
		} catch (Exception e) {
			pw.println((linea++)+" g.addNode(null): "+e.getMessage()); // 100  
		}
		pw.println((linea++)+" g.addNode(10): "+g.addNode(10));  // 101
		pw.println((linea++)+" g.getEdge("+8+", "+10+") --> "+g.getEdge(8, 10)); // 102
		pw.println((linea++)+" g.getEdge("+10+", "+9+") --> "+g.getEdge(10, 9)); // 103
		
		try {
			g.addNode(null);
		} catch (Exception e) {
			pw.println((linea++)+" g.addNode(null): "+e.getMessage()); // 104  
		}
		try {
			g.addEdge(8,8,-8.8);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(8,8,-8.8): "+e.getMessage()); // 105  
		}
		try {
			g.addEdge(7,8,-7.8);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(7,8,-7.8): "+e.getMessage()); // 106  
		}
		try {
			g.addEdge(8,7,-8.7);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(8,7,-8.7): "+e.getMessage()); // 107
		}
		try {
			g.addEdge(7,7,-7.7);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(7,7,-7.7): "+e.getMessage()); // 108
		}
		
		try {
			g.addEdge(null,8,0.8);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(null,8,0.8): "+e.getMessage()); // 109
		}
		try {
			g.addEdge(8,null,0.8);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(8,null,0.8): "+e.getMessage()); // 110
		}
		try {
			g.addEdge(null,null,0.8);
		} catch (Exception e) {
			pw.println((linea++)+" g.addEdge(null,null,0.8): "+e.getMessage()); // 111
		}
		try {
			g.getEdge(null,10);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(null,10) --> "+e.getMessage()); // 112
		}
		try {
			g.getEdge(10,null);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(10,null) --> "+e.getMessage()); // 113
		}
		try {
			g.getEdge(null,null);
		} catch (Exception e) {
			pw.println((linea++)+" g.getEdge(null,null) --> "+e.getMessage()); // 114
		}
		pw.println((linea++)+" g.existsNode(null): "+g.existsNode(null));  // 115
		pw.println((linea++)+" g.existsEdge(null,null): "+g.existsEdge(null,null));  // 116

		pw.println("Termina la prueba basica de evolucion.");
	}
	
}

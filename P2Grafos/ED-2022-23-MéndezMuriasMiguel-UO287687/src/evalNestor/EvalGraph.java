//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame asi: "evalNestor"
//  y meted en el esta clase
package evalNestor;

import p2Grafos.Graph;

public class EvalGraph<T> extends Graph<T> {
	
	Alumno alum=new Alumno();

	public EvalGraph(int tam) {
		// Llama al constructor original
		super(tam);
	}

	public EvalGraph(int tam,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights){
		super(tam,initialNodes,initialEdges,initialWeights); 
	}

	// nuevo constructor en EvalGraph 
    public EvalGraph(int tam,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd){ 
        super(tam,initialNodes,initialEdges,initialWeights,initialAfloyd,initialPfloyd); 
    } 

	public String getNombreFicheroAlumno(){
		return alum.getNombreFicheroAlumno();
	}
}


/*  

	//  deben estar incluidos estos constructores en la clase Graph  (ya estaba de la sesion anterior...)
	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		super(tam,initialNodes,initialEdges,initialWeights);
	} 
	
	// Nuevo constructor en vuestro Graph, hay que mantener tambien los anteriores... 
    public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd) { 
         super(tam, initialNodes,initialEdges,initialWeights,initialAfloyd,initialPfloyd);  
    } 

 */

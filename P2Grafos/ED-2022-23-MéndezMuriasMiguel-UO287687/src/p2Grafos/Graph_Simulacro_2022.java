package p2Grafos;


/**
 * @author Miguel Méndez Murias
 * @version 2022-23 Prácticas: Estructuras de datos
 */
public class Graph_Simulacro_2022<T> extends GraphAbstract<T> {

	/**
	 * Constructor con parámetros de la clase Graph
	 * @param tam El número máximo de nodos del grafo
	 * @param initialNodes Los nodos iniciales
	 * @param initialEdges Las aristas iniciales
	 * @param initialWeights Los pesos iniciales
	 */
	public Graph_Simulacro_2022 (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		super(tam,initialNodes,initialEdges,initialWeights);
	}
	/**
	 * 
	 * Se le pasa el número máximo de nodos del grafo
	 * @param tam El número máximo de nodos del grafo
	 */
	public Graph_Simulacro_2022(int tam) {
		super(tam);
		super.edges=new boolean[tam][tam];
		super.weights=new double[tam][tam];
		
	}
	/**
	 * Constructor con parámetros de la clase Graph
	 * @param tam El número máximo de nodos del grafo
	 * @param initialNodes Los nodos iniciales
	 * @param initialEdges Las aristas iniciales
	 * @param initialWeights Los pesos iniciales
	 * @param initialAfloyd La matriz A de Floyd inicial
	 * @param initialPfloyd La matriz P de Floyd inicial
	 */
	public Graph_Simulacro_2022(int tam,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd){ 
        super(tam,initialNodes,initialEdges,initialWeights,initialAfloyd,initialPfloyd); 
    }

	/**
	 * Obtiene el indice de un nodo en el vector de nodos
	 * Se le pasa el nodo y devuelve la posicion en el vector de nodos o -1 si no existe
	 * @param node El nodo
	 * @return la posición del nodo o -1 si no existe
	 */
	protected int getNode(T node) {
		if(node==null) {
			return -1;
		}
		for(int i=0;i<super.numNodes;i++) {
			if(node.equals(super.nodes[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * Inserta un nuevo nodo que se le pasa como parametro.
	 * Si ya existe, no lo inserta y devuelve false (implementado mas tarde). 
	 * Si recibe un nodo nulo, no lo inserta y lanza una NullPointerException
	 * Si no cabe, no lo inserta y lanza una FullStructureException.
	 * @param node El nodo
	 * @return true si se ha añadido el nodo, false en caso contrario
	 * @throws NullPointerException si el nodo es null
	 * @throws FullStructureException si el tamaño del grafo ya es máximo
	 * 
	 */
	public boolean addNode(T node) {
		if(node==null) {
			throw new NullPointerException("Element to insert is null.");
		}
		else if(existsNode(node)) {
			return false;
		}
		else if(super.numNodes==super.nodes.length) {
			throw new FullStructureException(node);
		}
		
		super.nodes[super.numNodes]=node;
		super.numNodes++;
		return true;
	}

	/**
	 * Si existe, borra el nodo deseado del vector de nodos asi como las aristas en
	 * las que forma parte y duvuelve true. 
	 * Si el nodo no existe devuelve false.
	 * Si recibe un nodo nulo, lanza una NullPointerException
	 * @param node El nodo
	 * @return true si se ha borrado el nodo, false en caso contrario
	 * @throws NullPointerException si el nodo es null
	 * 
	 */
	public boolean removeNode(T node) {
		if(node==null) {
			throw new NullPointerException("Element to remove is null.");
		}
		int indexNode=getNode(node);
		if(indexNode==-1) {
			return false;
		}
		int indexUltimo=numNodes-1;
		super.edges[indexNode][indexNode]=super.edges[indexUltimo][indexUltimo];
		super.edges[indexUltimo][indexUltimo]=false;
		super.weights[indexNode][indexNode]=super.weights[indexUltimo][indexUltimo];
		super.edges[indexUltimo][indexNode]=false;
		super.edges[indexNode][indexUltimo]=false;
		for(int i=0;i<super.edges.length;i++) {
			if(i==indexNode) {
				continue;
			}
			super.edges[indexNode][i]=super.edges[indexUltimo][i];
			super.edges[i][indexNode]=super.edges[i][indexUltimo];
			super.weights[indexNode][i]=super.weights[indexUltimo][i];
			super.weights[i][indexNode]=super.weights[i][indexUltimo];
			super.edges[indexUltimo][i]=false;
			super.edges[i][indexUltimo]=false;
		}
		
		super.nodes[indexNode]=super.nodes[indexUltimo];
		numNodes--;
		return true;
	}

	/**
	 * Si existe devuelve true
	 * Si no existe devuelve false
	 * @param node El nodo
	 * @return true si existe el nodo, false en caso contrario
	 */
	public boolean existsNode(T node) {
		if(node==null)
			return false;
		if(getNode(node)!=-1) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parametro
	 * 
	 * Devuelve true si existe la arista entre los dos nodos que se le pasan
	 * Devuelve false si no existe la arista o no existen ambos nodos
	 * @param source El nodo origen
	 * @param target El nodo destino
	 * @return true si existe la arista, false en caso contrario
	 */
	public boolean existsEdge(T source, T target) {
		if(source==null || target==null || !existsNode(source) || !existsNode(target)) {
			return false;
		}
		if(getEdge(source, target)!=-1) {
			return true;
		}
		return false;
	}

	/**
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. 
	 * Devuelve true si la inserta
	 * Devuelve falso si ya existe el arco (arista)
	 * Lanza una excepcion ElementNotPresentException si no existe el nodo origen o destino
	 * Lanza una IllegalArgumentException si el peso es invalido.
	 * @param source El nodo origen
	 * @param target El nodo destino
	 * @param edgeWeight El peso de la arista
	 * @return true si se ha añadido la arista, false en caso contrario
	 * @throws ElementNotPresentException si el nodo fuente o destino no existe
	 * @throws IllegalArgumentException si el peso es menor o igual que 0
	 * 
	 */
	public boolean addEdge(T source, T target, double edgeWeight) {
		int indexSource=getNode(source);
		int indexTarget=getNode(target);
		if(indexSource==-1) {
			throw new ElementNotPresentException(
					"Edge could not be loaded: source element " + source + " is not part of the graph.");
		}else if(indexTarget==-1) {
			throw new ElementNotPresentException(
					"Edge could not be loaded: target element " + target + " is not part of the graph.");
		}
		else if(edgeWeight<=0) {
			throw new IllegalArgumentException(
					"Weight edge could not be less or equals to 0");
		}
		else if(getEdge(source, target)!=-1) {
			return false;
		}
		super.edges[indexSource][indexTarget]=true;	
		super.weights[indexSource][indexTarget]=edgeWeight;
		return true;
	}

	/**
	 * Borra la arista del grafo que conecta dos nodos.
	 * Si la arista existe, se borra y devuelve true.
	 * Si los nodos source o target no existen, lanza una excepcion de tipo ElementNotPresentException
	 * Si tanto source como target existen, pero la arista a eliminar no, devuelve false.
	 * @param source El nodo origen
	 * @param target El nodo destino
	 * @return true si se ha eliminado la arista, false en caso contrario
	 * @throws ElementNotPresentException si el nodo fuente o destino no existe
	 * 
	 */
	public boolean removeEdge(T source, T target) {
		int indexSource=getNode(source);
		int indexTarget=getNode(target);
		if(indexSource==-1) {
			throw new ElementNotPresentException(
					"Edge could not be removed: " + source + " is not present in the current graph.");
		}else if(indexTarget==-1) {
			throw new ElementNotPresentException(
					"Edge could not be removed: " + source + " is not present in the current graph.");
		}
		double weigthEdge=getEdge(source, target);
		if(weigthEdge==-1) {
			return false;
		}
		super.edges[indexSource][indexTarget]=false;
		super.weights[indexSource][indexTarget]=-2; //esto no es obligatorio hacerlo
		
		return true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos.
	 * Si los nodos source o target no existen, lanza una excepcion de tipo ElementNotPresentException
	 * Si tanto source como target existen, pero la arista a eliminar no, devuelve -1
	 * @param source El nodo origen
	 * @param target El nodo destino
	 * @return el peso de la arista, -1 si no existe
	 * @throws ElementNotPresentException si el nodo fuente o destino no existe
	 * 
	 */

	public double getEdge(T source, T target) {
		int indexSource=getNode(source);
		int indexTarget=getNode(target);
		if(indexSource==-1) {
			throw new ElementNotPresentException(
					"Edge weight could not be obtained: " + source + " is not present in the current graph.");
		}else if(indexTarget==-1) {
			throw new ElementNotPresentException(
					"Edge weight could not be obtained: " + target + " is not present in the current graph.");
		}
		if(super.edges[indexSource][indexTarget]==true) {
			return super.weights[indexSource][indexTarget];
		}
		return -1;
	}

	/**
	 * Aplica el algoritmo de Floyd al grafo actual
	 * Devuelve true si lo aplica y false en caso contrario (no hay nodos en el grafo) 
	 * @return true si aplica el algoritmo, false en caso contrario
	 */
	public boolean floyd() {
		
		if(numNodes==0) {
			return false;
		}
		// inicializacion
		super.aFloyd=new double[numNodes][numNodes];
		super.pFloyd=new int[numNodes][numNodes];
		for(int i=0;i<numNodes;i++) {
			for(int j=0;j<numNodes;j++) {
				if(i==j) {
					super.aFloyd[i][j]=0;
				}
				else if(edges[i][j]==false) {
					super.aFloyd[i][j]=Double.POSITIVE_INFINITY;
				}else {
					super.aFloyd[i][j]=super.weights[i][j];
				}
				super.pFloyd[i][j]=-1;
			}
		}
		//busqueda de camino de coste minimo
		for(int k=0;k<numNodes;k++) { //k=nodo intermedio
			for(int i=0;i<numNodes;i++) { //i=nodo inicial
				for(int j=0;j<numNodes;j++) { //j=nodo final
					if(super.aFloyd[i][k]+super.aFloyd[k][j]<super.aFloyd[i][j]) {
						super.aFloyd[i][j]=super.aFloyd[i][k]+super.aFloyd[k][j];
						super.pFloyd[i][j]=k;
					}
				}
			}
		}
		return true;
	}
	

	/**
	 * Aplica el algoritmo de Floyd al grafo actual
	 * Devuelve true si lo aplica y false en caso contrario (no hay nodos en el grafo) 
	 * @return true si aplica el algoritmo, false en caso contrario
	 */
	public boolean floydPivots(T[] pivots) {
		if(pivots.length==0) {
			super.pFloyd=null;
			super.aFloyd=null;
			return false;
		}
		if(numNodes==0) {
			return false;
		}
		// inicializacion
		super.aFloyd=new double[numNodes][numNodes];
		super.pFloyd=new int[numNodes][numNodes];
		for(int i=0;i<numNodes;i++) {
			for(int j=0;j<numNodes;j++) {
				if(i==j) {
					super.aFloyd[i][j]=0;
				}
				else if(edges[i][j]==false) {
					super.aFloyd[i][j]=Double.POSITIVE_INFINITY;
				}else {
					super.aFloyd[i][j]=super.weights[i][j];
				}
				super.pFloyd[i][j]=-1;
			}
		}
		int[] indexPivotes=new int[pivots.length];
		for(int i=0;i<indexPivotes.length;i++) {
			indexPivotes[i]=getNode(pivots[i]);
			if(indexPivotes[i]==-1) {
				super.pFloyd=null;
				super.aFloyd=null;
				return false;
			}
		}
		//busqueda de camino de coste minimo
		for(int k: indexPivotes) { //k=nodo intermedio
			for(int i=0;i<numNodes;i++) { //i=nodo inicial
				for(int j=0;j<numNodes;j++) { //j=nodo final
					if(super.aFloyd[i][k]+super.aFloyd[k][j]<super.aFloyd[i][j]) {
						super.aFloyd[i][j]=super.aFloyd[i][k]+super.aFloyd[k][j];
						super.pFloyd[i][j]=k;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado, No
	 * necesariamente recorre todos los nodos. Al recorrer cada nodo hace un
	 * tratamiento del mismo que consiste en un concatenar el toString del nodo y un
	 * tabulador detras
	 * @param nodo El nodo desde el que se quiere obtener el recorrido en profundidad
	 * @return una cadena con el recorrido en profundidad
	 * 
	 */
	public String recorridoProfundidad(T nodo) {
		int indexNodo=getNode(nodo);
		if(indexNodo==-1) { //si no existe el nodo
			return "";
		}
		boolean visited[]=new boolean[numNodes]; //matriz con los nodos visitados
		return DFPrint(indexNodo, visited);
	}
	
	/**
	 * Método auxiliar para obtener el recorrido en profundidad desde un nodo.
	 * Como el recorrido en profundidad se obtiene aplicando el algoritmo DFPrint este método se
	 * encarga de realizar su funcionamiento, es decir, va recorriendo los nodos que tienen una arista desde
	 * el nodo dado y siempre priorizando el orden y primero los nodos hijos que los hermanos.
	 * Se ejecuta de forma recursiva y, en cada interacción, devuelve una cadena con el nodo que es descendiente 
	 * si no ha sido visitado (siguiendo las prioridades de orden e hijos primero), hasta que finalmente devuelve
	 * la cadena con el recorrido en profundidad completo
	 * @param indexNodo El nodo desde el que parten las aristas
	 * @param visited Una lista con los nodos visitados
	 * @return el camino en profundidad desde el nodo dado al principio
	 */
	private String DFPrint(int indexNodo, boolean visited[]) {
		visited[indexNodo]=true;
		String str=super.nodes[indexNodo]+"\t";
		for(int i=0;i<super.nodes.length;i++) {
			if(existsEdge(super.nodes[indexNodo], super.nodes[i])&&!visited[i]) { //si existe una arista que parte del nodo dado y no se ha visitado
				str+=DFPrint(i, visited); //ejecuta el método de forma recursiva pero desde los nodos a los que llega el nodo inicial y lo añade a la cadena
			}
		}
		return str;
	}

	/**
	 * Indica el camino de coste mínimo entre los nodos que se le pasan como parametros de esta
	 * Se aplica floyd() si no se ha aplicado antes
	 * forma: donde cada nodo (Origen, Destino, IntermedioN,...) se refiere al
	 * toString del nodo correspondiente
	 * Origen<tabulador>(coste0)<tabulador>Intermedio1<tabulador>(coste1)<tabulador>IntermedioN<tabulador>(costeN)<tabulador>Destino<tabulador>
	 * Si no hay camino: Origen<tabulador>(Infinity)<tabulador>Destino<tabulador>
	 * Si Origen y Destino coinciden: Origen<tabulador> 
	 * Si no existen los 2 nodos devuelve una cadena vacia
	 * @param origen El nodo donde empieza el camino
	 * @param destino El nodo donde termina el camino
	 * @return una cadena con la información anterior
	 * 
	 */
	public String path(T origen, T destino) {
		int indexOrigen=getNode(origen);
		int indexDestino=getNode(destino);
		String str="";
		if(indexOrigen==-1 || indexDestino==-1) {
			return "";
		}
		if(getAFloyd()==null||getPFloyd()==null|| getAFloyd().length==0 || getPFloyd().length==0) {
			floyd();
		}
		//nodo auxiliar para almacenar el nodo intermedio entre origen y destino. -1 si no hay nodo intermedio
		int indexCamino=getPFloyd()[indexOrigen][indexDestino];
		if(indexCamino==-1 && origen!=destino) { //si no hay nodo intermedio y origen y destino no coinciden
			return super.nodes[indexOrigen]+"\t("+getAFloyd()[indexOrigen][indexDestino]+")\t"+super.nodes[indexDestino]+"\t";
		}
		else if(indexOrigen==indexDestino) { //si el nodo origen y el destino son el mismo
			return super.nodes[indexOrigen]+"\t";
		}
		else { //en caso de que exista nodo intermedio
			str=super.nodes[indexDestino]+"\t";
			/*nodo auxiliar para no modificar el nodo destino. Va cogiendo el indice del nodo final de cada camino,
			mientras que indiceCamino va cogiendo el indice del nodo intermedio*/
			int indexNodoAuxiliar=indexDestino; 
			while(indexCamino!=-1) { //mientras haya nodo intermedio
				str=super.nodes[indexCamino]+"\t("+getAFloyd()[indexCamino][indexNodoAuxiliar]+")\t"+str; //añade al principio de la cadena el nodo del que parte(intermedio) y el nodo al que llega(destino o alguno anterior) con el peso entre ellos
				indexNodoAuxiliar=indexCamino; //cambia el valor del indexNodoAuxiliar para establecerlo como el siguiente nodo destino (ya que los siguientes ya están en la cadena)
				indexCamino=getPFloyd()[indexOrigen][indexNodoAuxiliar]; //cambia el valor del indiceCamino para establecerlo como el nodo intermedio entre el nodo origen y el nodoAuxiliar
			}
			str=super.nodes[indexOrigen]+"\t("+getAFloyd()[indexOrigen][indexNodoAuxiliar]+")\t"+str; //añade al principio de la cadena el nodo origen y el coste con el primer nodo intermedio
			return str;
		}
		
	}
	
	/**
	 * Indica el camino de coste mínimo entre los nodos que se le pasan como parametros de esta
	 * Se aplica floyd() si no se ha aplicado antes
	 * forma: donde cada nodo (Origen, Destino, IntermedioN,...) se refiere al
	 * toString del nodo correspondiente
	 * Origen<tabulador>(coste0)<tabulador>Intermedio1<tabulador>(coste1)<tabulador>IntermedioN<tabulador>(costeN)<tabulador>Destino<tabulador>
	 * Si no hay camino: Origen<tabulador>(Infinity)<tabulador>Destino<tabulador>
	 * Si Origen y Destino coinciden: Origen<tabulador> 
	 * Si no existen los 2 nodos devuelve una cadena vacia
	 * @param origen El nodo origen
	 * @param destino El nodo destino
	 * @return Una cadena con el camino de coste mínimo entre los nodos dados como parámetro
	 */
	public String pathRecursivo(T origen, T destino) {//No estoy seguro si funciona
		int indexOrigen=getNode(origen);
		int indexDestino=getNode(destino);
		
		String str="";
		if(indexOrigen==-1 || indexDestino==-1) {
			return "";
		}
		if(getAFloyd()==null||getPFloyd()==null|| getAFloyd().length==0 || getPFloyd().length==0) {
			floyd();
		}
		//nodo auxiliar para almacenar el nodo intermedio entre origen y destino. -1 si no hay nodo intermedio
		int indexCamino=getPFloyd()[indexOrigen][indexDestino];
		if(indexCamino==-1 && origen!=destino) { //si no hay nodo intermedio y origen y destino no coinciden
			str=super.nodes[indexOrigen]+"\t("+getAFloyd()[indexOrigen][indexDestino]+")\t"+super.nodes[indexDestino]+"\t";
		}
		else if(indexOrigen==indexDestino) { //si el nodo origen y el destino son el mismo
			str=super.nodes[indexOrigen]+"\t";
		}
		else {
			str="("+getAFloyd()[indexCamino][indexDestino]+")\t"+super.nodes[indexDestino]+"\t";
			if(indexCamino!=-1) {
				str=pathRecursivo(origen, super.nodes[indexCamino])+str;
			}

		}
		return str;
	}

	/**
	 * Algoritmo de Dijkstra para encontrar el camino de coste minimo desde
	 * nodoOrigen hasta el resto de nodos
	 * Se le pasa el nodo origen como parametro
	 * Devuelve una DijkstraDataClass con los vectores D y P correspondientes si se aplica o null si no se puede aplicar
	 * @param nodoOrigen El nodo desde donde hay que empezar a aplicar Dijkstra
	 * @return Un objeto de la clase DijkstraDataClass con los resultados de aplicar Dijkstra desde el nodo origen en el grafo actual
	 */
	public DijkstraDataClass dijkstra(T nodoOrigen) {
		if(!existsNode(nodoOrigen)) {
			return null;
		}
		int numNodes=super.nodes.length;
		int index=getNode(nodoOrigen);
		double[] vectorD=new double[numNodes];
		int[] vectorP=new int[numNodes];
		boolean[] visited=new boolean[numNodes];
		for(int i=0;i<numNodes;i++) {
			if(i==index) {
				vectorP[i]=-1;
				continue;
			}else if(edges[index][i]==false) {
				vectorD[i]=Double.POSITIVE_INFINITY;
				vectorP[i]=-1;
			}
			else {
				vectorD[i]=weights[index][i];
				vectorP[i]=index;
			}
		}
		visited[index]=true;
		int min=minCost(vectorD, visited);
		while(min!=-1) {
			for(int i=0;i<numNodes;i++) {
				if(edges[min][i]&&((vectorD[min]+weights[min][i])<vectorD[i])) {
					vectorD[i]=vectorD[min]+weights[min][i];
					vectorP[i]=min;
				}
			}
			visited[min]=true;
			min=minCost(vectorD, visited);
		}
		
		return new DijkstraDataClass(numNodes, index, vectorD, vectorP);
	}

	/** 
	 * Busca el nodo con distancia minima en D al resto de nodos
	 * 
	 * Es una recomendación pero no es imprescindible...
	 * 
	 * Se le pasa el vector d de dijkstra actual
	 * Un conjunto de visitados (conjunto S) como vector de booleanos 
	 * 	si se usa otro tipo de conjunto adaptarlo
	 * Se devuelve el indice del nodo buscado o -1 si el grafo es no conexo o no quedan
	 *         nodos sin visitar
	 * @param vectorD El vectorD
	 * @param visited Una lista con los nodos visitados aplicando Dijkstra
	 * @return El índice que tiene el mínimo coste en el vectorD y que no se ha visitado
	 */
	private int minCost(double[] vectorD, boolean[] visited) {
		
		double costeMinimo=Double.POSITIVE_INFINITY;
		int indiceCosteMinimo=-1;
		for(int i=0;i<vectorD.length;i++) {
			if(!visited[i] && (costeMinimo>vectorD[i])) {
				costeMinimo=vectorD[i];
				indiceCosteMinimo=i;
			}
		}
		return indiceCosteMinimo;
	}

	/**
	 * Devuelve el coste del camino de coste minimo entre origen y destino segun Floyd.
	 * Si los nodos source o target no existen, lanza una excepcion de tipo ElementNotPresentException
	 * Si no se ha aplicado Floyd, lo aplica. (OJO que este metodo SI lo invoca) 
	 * @param origen El nodo donde empieza el camino
	 * @param destino El nodo donde termina el camino
	 * @return El coste del camino de coste mínimo entre el origen y el destino
	 * @throws ElementNotPresentException Si el origen o el destino no existen
	**/ 
	public double minCostPath(T origen, T destino) {
		int indexOrigen=getNode(origen);
		int indexDestino=getNode(destino);
		if(indexOrigen==-1) {
			throw new ElementNotPresentException(
					"Path could not be obtained: " + origen + " is not present in the current graph.");
		}
		if(indexDestino==-1) {
			throw new ElementNotPresentException(
					"Path could not be obtained: " + destino + " is not present in the current graph.");
		}
		if(getAFloyd()==null||getPFloyd()==null|| getAFloyd().length==0 || getPFloyd().length==0) {
			floyd();
		}
		return getAFloyd()[indexOrigen][indexDestino]; //devuelve el coste del camino de coste mínimo entre el origen y el destino, que lo obtiene de la matriz A de floyd
	}

}

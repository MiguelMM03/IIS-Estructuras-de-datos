package p3Arboles;

/**
 * @author Profesores ED
 * @version 2022-23 distribuible
 */
 public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T>{
	protected T [] elementos;
	protected int numElementos;

	/**
	 * Constructor
	 * @param numMaxElementos El número máximo de elementos del montículo
	 */
	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {
		elementos = (T[]) new Comparable[numMaxElementos];
	}

	/**
	 * Añade el elemento dado al montículo
	 * @param info El elemento que se quiere añadir
	 * @return true si ha podido añadir, false en caso contrario
	 */
	@Override
	public boolean add(T info) {
		if(numElementos==elementos.length || info==null) {
			return false;
		}
		elementos[numElementos]=info;
		filtradoAscendente(numElementos);
		numElementos++;
		return true;
	}

	/**
	 * Elimina el primer elemento del montículo y lo devuelve
	 * @return El elemento eliminado
	 */
	@Override
	public T poll() {
		if(!isEmpty()) {
			T t=elementos[0];
			elementos[0]=elementos[numElementos-1];
			numElementos--;
			filtradoDescendente(0);
			return t;
		}
		return null;
	}

	/**
	 * @param info La información del elemento que se quiere eliminar
	 * @return true si se ha podido eliminar, false en caso contrario
	 */
	@Override
	public boolean remove(T info) {
		int index=getIndex(info);
		if(index==-1) {
			return false;
		}
		elementos[index]=elementos[numElementos-1];
		int compare=elementos[numElementos-1].compareTo(info);
		if(compare<0) {
			elementos[numElementos-1]=null;
			numElementos--;
			System.out.println("A");
			filtradoAscendente(index);
		}
		else if(compare==0) {
			elementos[index]=null;
			numElementos--;
		}
		else {
			elementos[numElementos-1]=null;
			numElementos--;
			
			filtradoDescendente(index);
		}
		return true;
	}

	/**
	 * @return true si el montículo está vacío, false en caso contrario
	 */
	@Override
	public boolean isEmpty() {
		return numElementos==0;
	}

	/**
	 * Elimina todos los elementos del montículo
	 */
	@Override
	public void clear() {
		numElementos=0;
		for(int i=0;i<elementos.length;i++) {
			elementos[i]=null;
		}
	}

	/**  
	 * Devuelve una cadena con la informacion de los elementos que contiene el  
	 * monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado) 
	 * @return una cadena con información de los elementos del montículo 
	*/
	public String toString() {
	// Por ejemplo el arbol tumbado...  
		StringBuilder cadena = new StringBuilder();  
		cadena.append(inOrdenDerechaTabulado(0,0));  
		return cadena.toString();   
	}


    // el arbol que empieza en indice p tumbado con esp tabulaciones...
	/**
	 * 
	 * @param p Indice donde empieza el arbol que se quiere mostrar
	 * @param esp Espaciado(tabulaciones)
	 * @return Una cadena con información de los elementos del árbol que comienza en el indice p
	 */
    private String inOrdenDerechaTabulado(int p,int esp) {
        String cadena="";
        if (p<numElementos) {
            int izq = Math.abs(2*p+1);
            int der = Math.abs(2*p+2);
            cadena+=inOrdenDerechaTabulado(der,esp+1);
            for(int i = 0;i<esp;i++)
                cadena+="\t";
            cadena += elementos[p]+"\n";
            cadena+=inOrdenDerechaTabulado(izq,esp+1);
        }
        return cadena;
    }

    /**
     * Realiza una filtrado ascendente de minimos en el monticulo
     * 
     *@param i el indice del elemento a filtrar
     */
    protected void filtradoAscendente(int i) {
    	int compare=elementos[(i-1)/2].compareTo(elementos[i]);
    	if(compare<0) {
    		filtradoAscendente(i-1);
    	}
    	
    	else if(compare>0) {
    		T aux=elementos[(i-1)/2];
    		elementos[(i-1)/2]=elementos[i];
    		elementos[i]=aux;
    		filtradoAscendente(i-1);
    	}
    }
    

    /**
     * Realiza una filtrado descendente de minimos en el monticulo
     * 
     * @param i el indice del elemento a filtrar
     */
    protected void filtradoDescendente(int i) {
    	int hijoIzquierdo=2*i+1;
    	if(numElementos==2*i+2 && elementos[i].compareTo(elementos[hijoIzquierdo])>0) { //Solo tiene hijo izquierdo
    		T aux=elementos[i];
    		elementos[i]=elementos[hijoIzquierdo];
    		elementos[hijoIzquierdo]=aux;
    	}
    	else if(numElementos>2*i+2) { //tiene dos hijos
    		int mayor=mayorDeLosHijos(i);
    		int menor=menorDeLosHijos(i);
    		if(mayor!=-1 && elementos[i].compareTo(elementos[menor])>0) {
	    		T aux=elementos[i];
	    		
	    		if(menor!=-1) {
		    		elementos[i]=elementos[menor];
		    		elementos[menor]=aux;
		    		filtradoDescendente(menor);
	    		}
    		}
    	}
    }
    /**
     * Método para encontrar el mayor de los hijos del padre dado
     * @param padre El indice del padre
     * @return El indice del mayor de los hijos
     */
    private int mayorDeLosHijos(int padre) {
    	int mayor=-1;
    	if(numElementos>2*padre+1) {//tiene hijo izquierdo
    		mayor=2*padre+1;
    	}
    	if(numElementos>2*padre+2) { //tiene hijo derecho
    		//compara el hijo derecho con el izquierdo. No puede tener hijo derecho y no hijo izquierdo
    		if((elementos[2*padre+2]).compareTo(elementos[2*padre+1])>0) { 
    			mayor=2*padre+2;
    		}
    	}
    	return mayor;
    }
    /**
     * Método para encontrar el menor de los hijos del padre dado
     * @param padre El indice del padre
     * @return El indice del menor de los hijos
     */
    private int menorDeLosHijos(int padre) {
    	int menor=-1;
    	if(numElementos>2*padre+1) {//tiene hijo izquierdo
    		menor=2*padre+1;
    	}
    	if(numElementos>2*padre+2) { //tiene hijo derecho
    		//compara el hijo derecho con el izquierdo. No puede tener hijo derecho y no hijo izquierdo
    		if((elementos[2*padre+2]).compareTo(elementos[2*padre+1])<0) { 
    			menor=2*padre+2;
    		}
    	}
    	return menor;
    }
    /**
     * Devuelve el indice del elemento dado
     * @param info El elemento del que se quiere obtener el indice
     * @return El indice del elemento dado
     */
    private int getIndex(T info) {
    	if(info==null) {
    		return -1;
    	}
    	for(int i=0;i<numElementos;i++) {
    		if(info.equals(elementos[i])){
    			return i;
    		}
    	}
    	return -1;
    }

}

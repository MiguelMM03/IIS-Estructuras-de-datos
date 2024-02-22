package p3Arboles;

/**
* @author Prodesores ED (Español)
* @version 2022-23 distribuible
*/
public class BSTree <T extends Comparable<T>> extends AbstractTree <T>{

/**
 * Constructor
 */
public BSTree() {
	super();
}

/**
 * getter del nodo raiz del arbol
 */
protected BSTNode<T> getRoot() {
	return (BSTNode<T>) super.getRoot();
}


/**
 * Se le pasa el objeto comparable que hay que insertar
 * devuelve true si lo inserta
 * Si ya existe, no lo inserta y devuelve false (implementado mas tarde). 
 * Si recibe un nodo nulo, no lo inserta y lanza una NullPointerException
 * @param x El nodo que se quiere añadir
 * @return true si se ha añadido, false en caso contrario
 * @throws NullPointerException si el nodo dado es null
 */
public boolean addNode(T x) {
	if(x==null) {
		throw new NullPointerException("Element to insert is null.");
	}
	if(searchNode(x)!=null) {
		return false;
	}
	super.setRoot(add(x,getRoot()));
	return true;
}

/**
 * Método que permite añadir el nodo dado al nodo del árbol binario pasado
 * como parámetro.
 * Se ejecuta de forma recursiva
 * @param x El nodo que se quiere añadir
 * @param nodoDelArbol El nodo del arbol
 * @return True si se ha añadido el nodo, false en caso contrario
 */
private BSTNode<T> add(T x, BSTNode<T> nodoDelArbol) {
	if(nodoDelArbol==null) {
		return new BSTNode<>(x);
	}
	int i=x.compareTo(nodoDelArbol.getInfo());
	if(i<0) {
		nodoDelArbol.setLeft(add(x,nodoDelArbol.getLeft()));
	}
	else {
		nodoDelArbol.setRight(add(x,nodoDelArbol.getRight()));
	}	
	return nodoDelArbol;
}

/**
* Se le pasa un objeto comparable que se busca
* Devuelve el objeto encontrado que cumple que es "equals" con el
* buscado, null si no lo encuentra por cualquier motivo
* @param x El nodo que se quiere buscar
* @return null si el nodo no existe o el nodo en caso de que se haya encontrado
*/
public T searchNode(T x) {
	if(x==null) {
		return null;
	}
	return searchNodeRecursivo(x,getRoot());
}
/**
 * Método auxiliar para buscar un nodo de forma recursiva
 * @param x El nodo que se quiere buscar
 * @param nodoDelArbol El BSTNode
 * @return El nodo si se ha encontrado o null en caso contrario
 */
private T searchNodeRecursivo(T x, BSTNode<T> nodoDelArbol) {
	if(nodoDelArbol==null) {
		return null;
	}
	if(x.equals(nodoDelArbol.getInfo())) {
		return nodoDelArbol.getInfo();
	}
	int i=x.compareTo(nodoDelArbol.getInfo());
	if(i<0) {
		return searchNodeRecursivo(x,nodoDelArbol.getLeft());
	}
	else if(i>0){
		return searchNodeRecursivo(x,nodoDelArbol.getRight());
	}
	return null;
}


/**
* Genera un String con el recorrido en pre-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
* @return el recorrido pre-orden
*/
public String preOrder() {
	return preOrderRec("",getRoot());
}

/**
 * Método recursivo para generar el recorrido pre-orden
 * @param str La cadena
 * @param nodoDelArbol El nodo desde el que se genera el recorrido
 * @return La cadena con el recorrido pre-orden
 */
private String preOrderRec(String str, BSTNode<T> nodoDelArbol) {
	if(nodoDelArbol==null) {
		return str;
	}
	str+=nodoDelArbol.toString()+"\t";
	str=preOrderRec(str, nodoDelArbol.getLeft());
	str=preOrderRec(str, nodoDelArbol.getRight());
	return str;
}

/**
* Genera un String con el recorrido en post-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
* @return el recorrido post-orden
*/
public String postOrder() {
	return postOrderRec("",getRoot());
}
/**
 * Método recursivo para generar el recorrido post-orden
 * @param str La cadena
 * @param nodoDelArbol El nodo desde el que se genera el recorrido
 * @return La cadena con el recorrido post-orden
 */
private String postOrderRec(String str, BSTNode<T> nodoDelArbol) {
	if(nodoDelArbol==null) {
		return str;
	}
	str=nodoDelArbol.toString()+"\t"+str;
	str=postOrderRec(str, nodoDelArbol.getRight());
	str=postOrderRec(str, nodoDelArbol.getLeft());
	
	return str;
}
/**
 * Método recursivo para generar el recorrido in-orden
 * @param str La cadena
 * @param nodoDelArbol El nodo desde el que se genera el recorrido
 * @return La cadena con el recorrido in-orden
 */
private String inOrderRec(String str, BSTNode<T> nodoDelArbol) {
	if(nodoDelArbol==null) {
		return str;
	}
	
	str=inOrderRec(str, nodoDelArbol.getRight());
	str=nodoDelArbol.toString()+"\t"+str;
	str=inOrderRec(str, nodoDelArbol.getLeft());
	return str;
}


/**
* Genera un String con el recorrido en in-orden (izquierda-derecha)
* (toString de los nodos separados por tabuladores)
* @return el recorrido in-orden
*/
public String inOrder() {
	return inOrderRec("",getRoot());
}


/**
* Se le pasa el objeto que se quiere borrar que coincida con equals
* Si recibe un nodo nulo, lanza una NullPointerException
* Devuelve true si lo ha borrado, false caso contrario
* @param x El nodo que se quiere eliminar
* @return true si se ha conseguido eliminar, false en caso contrario
* @throws NullPointerException si el nodo pasado como parámetro es null
*/
public boolean removeNode (T x) {
	if(x==null) {
		throw new NullPointerException("Element to remove is null.");
	}
	if(searchNode(x)==null) {
		return false;
	}
	if(getRoot()==null) {
		return false;
	}
	setRoot(removeNodeRecursivo(x,getRoot())); 
	return true;
	
}

/**
 * Método recursivo que permite eliminar el nodo dado como parámetro. Para ello, se le pasa la raíz del árbol 
 * como segundo parámetro y en el momento en que la información de ese nodo y el nodo que se quiere eliminar coinciden,
 * se procede a aplicar el algoritmo de eliminación en función de los hijos que tenga el nodo a eliminar.
 * Si no tiene hijos, lo borra y no hace nada más.
 * Si tiene un hijo izquierdo, sustituye el nodo por su hijo izquierdo.
 * Si tiene un hijo derecho, sustituye el nodo por su hijo derecho.
 * Si tiene dos hijos, sustituye el nodo por el mayor de los nodos del subarbol izquierdo del nodo y elimina este nodo
 * @param x El nodo que se quiere eliminar
 * @param nodo El BSTNode que comienza siendo nodo raiz y se va modificando hasta que su información coincida con el nodo que se quiere eliminar
 * @return El nodo raiz, de forma que devuelve el árbol completo
 */
private BSTNode<T> removeNodeRecursivo(T x, BSTNode<T> nodo){
	int i=x.compareTo(nodo.getInfo());
	if(i<0) {
		nodo.setLeft(removeNodeRecursivo(x, nodo.getLeft()));
		return nodo;
	}
	else if(i==0) {
		if(nodo.getLeft()==null && nodo.getRight()==null) {
			return null;
		}else if(nodo.getLeft()==null) {
			return nodo.getRight();
		}else if(nodo.getRight()==null) {
			return nodo.getLeft();
		}else {
			BSTNode<T> mayorSubArbolIzquierdo=searchMayor(nodo.getLeft());
			removeNode(mayorSubArbolIzquierdo.getInfo());
			mayorSubArbolIzquierdo.setRight(nodo.getRight());
			mayorSubArbolIzquierdo.setLeft(nodo.getLeft());
			return mayorSubArbolIzquierdo;
		}
	}
	else {
		nodo.setRight(removeNodeRecursivo(x, nodo.getRight()));
		return nodo;
	}
	
}

/**
 * Busca el mayor nodo a partir del nodo dado como parámetro
 * @param nodoDelArbol El nodo desde el que se quiere buscar el mayor
 * @return El mayor nodo
 */
private BSTNode<T> searchMayor(BSTNode<T> nodoDelArbol){
	if(nodoDelArbol==null) {
		return null;
	}
	BSTNode<T> drch=nodoDelArbol.getRight();
	if(drch==null) {
			return nodoDelArbol;
	}
	while(drch.getRight()!=null) {
		drch=drch.getRight();
	}
	return drch;
}



}

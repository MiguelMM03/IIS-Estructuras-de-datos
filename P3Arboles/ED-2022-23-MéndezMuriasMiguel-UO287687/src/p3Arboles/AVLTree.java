package p3Arboles;

/** 
* Clase derivada de BSTree añadiendo funcionalidad de AVL 
* @author Profesores ED (Español) 
* @version 2022-23 distribuible 
*/ 
public class AVLTree <T extends Comparable<T> >extends BSTree <T>{ 
 
	/** 
	* Constructor  
	*/ 
	public AVLTree() {
		super();//
	} 
	 
	 
	/** (non-Javadoc) 
	* @see BSTree#addNode(java.lang.Comparable) 
	* Redefine inserción para funcionalidad AVL 
	*/ 
	public boolean addNode (T x){
		if(x==null) {
			throw new NullPointerException("Element to insert is null.");
		}
		if(searchNode(x)!=null) {
			return false;
		}
		super.setRoot(addNodeRec(x,(AVLNode<T>) super.getRoot()));
		return true;
	} 
	
	/**
	 * Se le pasa un T y un AVLNode donde se quiere añadir y tiene que devolver la referencia al nuevo árbol
	 * @param x
	 */
	private AVLNode<T> addNodeRec(T x, AVLNode<T> raiz){
		if(raiz==null) {
			return new AVLNode<>(x);
		}
		int i=x.compareTo(raiz.getInfo());
		if(i<0) {
			raiz.setLeft(addNodeRec(x,raiz.getLeft()));
		}
		else {
			raiz.setRight(addNodeRec(x,raiz.getRight()));
		}
		raiz=updateAndBalanceIfNecesary(raiz);
		return raiz;
	}
	 
	 
	/** 
	* se le pasa el arbol que se quiere actualizar Height, BF  
	*      y balancear si fuese necesario 
	* y devuelve la raiz del arbol por si ha cambiado 
	*/ 
	private AVLNode<T> updateAndBalanceIfNecesary (AVLNode<T> x){
		x=updateHeight(x);
		x= updateBalanceFactor(x);
		return balanceIfNecesary(x);
	
	}


	/**
	 * Balancea el árbol si es necesario
	 * @param x El nodo del árbol a balancear
	 * @return La raiz balanceada
	 */
	private AVLNode<T> balanceIfNecesary(AVLNode<T> x) {
		if(x.getBF()==-2) {
			if(x.getLeft().getBF()==1) {
				return doubleLeftRotation(x);
			}
			else {
				return singleLeftRotation(x);
			}
		}
		if(x.getBF()==2) {
			if(x.getRight().getBF()==-1) {
				return doubleRightRotation(x);
				
			}
			else {
				return singleRightRotation(x);
			}
		}
		return x;
	} 
	 
	 
	/**
	 * Actualiza el factor de balance
	 * Se ejecuta de forma recursiva
	 * @param x El nodo del que se quiere actualizar el factor de balance
	 * @return El nodo con el factor de balance actualizado
	 */
	private AVLNode<T> updateBalanceFactor(AVLNode<T> x) {
		if(x.getRight()!=null) {
			x.setRight(updateAndBalanceIfNecesary(x.getRight()));
		}
		if(x.getLeft()!=null) {
			x.setLeft(updateAndBalanceIfNecesary(x.getLeft()));
		}
		x.updateBalanceFactor();
		return x;
	}


	/**
	 * Actualiza la altura del nodo dado.
	 * Se ejecuta de forma recursiva llamando a los nodos hijos
	 * @param x El nodo del que se quiere actualizar la altura
	 * @return El nodo con la altura actualizada
	 */
	private AVLNode<T> updateHeight(AVLNode<T> x) {
		if(x.getLeft()!=null) {
			x.setLeft(updateHeight(x.getLeft()));
		}if(x.getRight()!=null) {
			x.setRight(updateHeight(x.getRight()));
		}
		x.updateHeight();
		return x;
	}


	/** 
	* Se le pasa la raiz del arbol a balancear con rotacion simple 
	* devuelve la raiz del nuevo arbol que ha cambiado 
	* @param x El nodo al que se le quiere hacer la rotacion
	* @return el nodo que actúa como raíz en la rotación
	*/ 
	private AVLNode<T> singleLeftRotation (AVLNode<T> x){
		AVLNode<T> aux=x.getLeft();
		x.setLeft(aux.getRight());
		aux.setRight(x);
		aux=updateAndBalanceIfNecesary(aux);
		return aux;
	} 
	 
	 
	/** 
	* Se le pasa la raiz del arbol a balancear con rotacion doble 
	* devuelve la raiz del nuevo arbol que ha cambiado 
	* @param x El nodo al que se le quiere hacer la rotacion
	* @return el nodo que actúa como raíz en la rotación
	*/ 
	private AVLNode<T> doubleLeftRotation(AVLNode<T> x) {
		x.setLeft(singleRightRotation(x.getLeft()));
		return singleLeftRotation(x);
	} 
	 
	 
	/** 
	* Se le pasa la raiz del arbol a balancear con rotacion simple 
	* devuelve la raiz del nuevo arbol que ha cambiado 
	* @param x El nodo al que se le quiere hacer la rotacion
	* @return el nodo que actúa como raíz en la rotación
	*/ 
	private AVLNode<T> singleRightRotation (AVLNode<T> x){
		
		AVLNode<T> aux=x.getRight();
		x.setRight(aux.getLeft());
		aux.setLeft(x);
		aux=updateAndBalanceIfNecesary(aux);
		return aux;
	} 
	 
	 
	/** 
	* Se le pasa la raiz del arbol a balancear con rotacion doble 
	* Devuelve la raiz del nuevo arbol que ha cambiado 
	* @param x El nodo al que se le quiere hacer la rotacion
	* @return el nodo que actúa como raíz en la rotación
	*/ 
	private AVLNode<T> doubleRightRotation(AVLNode<T> x) {
		x.setRight(singleLeftRotation(x.getRight()));
		return singleRightRotation(x);
	} 
	 
	 
	/** (non-Javadoc) 
	* @see BSTree#removeNode(java.lang.Comparable) 
	* Redefinición para incluir características AVL 
	*/ 
	public boolean removeNode (T x){
		if(x==null) {
			throw new NullPointerException("Element to remove is null.");
		}
		if(searchNode(x)==null) {
			return false;
		}
		if(getRoot()==null) {
			return false;
		}
		setRoot(removeNodeRecursivo(x,(AVLNode<T>)getRoot())); 
		return true;
		
	} 
	
	/**
	 * 
	 * Método recursivo que permite eliminar el nodo dado como parámetro. Para ello, se le pasa la raíz del árbol 
	 * como segundo parámetro y en el momento en que la información de ese nodo y el nodo que se quiere eliminar coinciden,
	 * se procede a aplicar el algoritmo de eliminación en función de los hijos que tenga el nodo a eliminar.
	 * Si no tiene hijos, lo borra y no hace nada más.
	 * Si tiene un hijo izquierdo, sustituye el nodo por su hijo izquierdo.
	 * Si tiene un hijo derecho, sustituye el nodo por su hijo derecho.
	 * Si tiene dos hijos, sustituye el nodo por el mayor de los nodos del subarbol izquierdo del nodo y elimina este nodo
	 * Es el mismo método que el removeNodeRecursivo del BSTree pero incorpora características AVL
	 * @param x El nodo que se quiere eliminar
	 * @param nodo El AVLNode que comienza siendo nodo raiz y se va modificando hasta que su información coincida con el nodo que se quiere eliminar
	 * @return El nodo raiz, de forma que devuelve el árbol completo
	 */
	private AVLNode<T> removeNodeRecursivo(T x, AVLNode<T> nodo){
		int i=x.compareTo(nodo.getInfo());
		if(i<0) {
			nodo.setLeft(removeNodeRecursivo(x, nodo.getLeft()));
			nodo=updateAndBalanceIfNecesary(nodo);
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
				AVLNode<T> mayorSubArbolIzquierdo=searchMayor(nodo.getLeft());
				//se llama a este en vez de al remove() para evitar rebalanceos en todo el árbol
				nodo.setLeft(removeNodeRecursivo(mayorSubArbolIzquierdo.getInfo(),nodo.getLeft()));
				mayorSubArbolIzquierdo.setRight(nodo.getRight());
				mayorSubArbolIzquierdo.setLeft(nodo.getLeft());
				mayorSubArbolIzquierdo=updateAndBalanceIfNecesary(mayorSubArbolIzquierdo);
				return mayorSubArbolIzquierdo;
			}
		}
		else {
			nodo.setRight(removeNodeRecursivo(x, nodo.getRight()));
			nodo=updateAndBalanceIfNecesary(nodo);
			return nodo;
		}
		
	}
	/**
	 * Busca el mayor nodo a partir del nodo dado como parámetro
	 * @param nodoDelArbol El nodo desde el que se quiere buscar el mayor
	 * @return El mayor nodo
	 */
	private AVLNode<T> searchMayor(AVLNode<T> nodoDelArbol){
		if(nodoDelArbol==null) {
			return null;
		}
		AVLNode<T> drch=nodoDelArbol.getRight();
		if(drch==null) {
				return nodoDelArbol;
		}
		while(drch.getRight()!=null) {
			drch=drch.getRight();
		}
		return drch;
	}

 
} 
 
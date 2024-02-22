package p3Arboles;

/**
 * Implementa un nodo de un arbol binario de busqueda con un objeto "Comparable" como informacion
 * @author Nestor
 * @version 2022-23
 * 
*/
public class BSTNode <T extends Comparable<T>> extends AbstractTreeNode<T>{

	/**
	 * Constructor
	 * @param info La información del nodo
	 */
	@SuppressWarnings("unchecked")
	public BSTNode(Comparable<T> info) {
		super((T) info);
	}

	/**
	 * @return El subarbol izquierdo
	 */
	public BSTNode<T> getLeft () {
		return (BSTNode<T>) super.getLeft();
	}

	/**
	 * @return El subarbol derecho
	 */
	public BSTNode<T> getRight () {
		return (BSTNode<T>) super.getRight();
	}
	
}

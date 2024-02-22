package p3Arboles;

/**
* Clase derivada de BSTNode añadiendo funcionalidad de AVL
* @author Profesores ED (Español)
* @version 2022-23 distribuible
*
*/
public class AVLNode<T extends Comparable<T>> extends BSTNode<T>  {

	/**
	* Para almacenar la altura del nodo
	*/
	protected int height;
	
	/**
	* Para almacenar al Factor de balance.
	* Puede no existir y calcularse cada vez a partir de la altura de los hijos.
	*/
	protected int balanceFactor;
	
	
	/**
	* Llama al padre y añade la informacion propia
	* se le pasa la informacion que se mete en el nuevo nodo
	* @param x La informacion del nodo
	*/
	public AVLNode(T x){
		super(x);
		this.height=1;
	}
	
	
	
	/**
	* devuelve la altura del arbol del cual es raiz el nodo en cuestion
	* @return la altura del arbol
	*/
	public int getHeight() {
		return this.height;
	}
	
	
	
	/**
	* Devuelve el factor de balance segun equilibrio del arbol del cual es raiz
	* @return El factor de balance
	*/
	public int getBF() {
		return this.balanceFactor;
	}
	
	
	/**
	* Actualiza la altura del nodo en el arbol utilizando la altura de los hijos
	* y si es preciso actualiza el FB
	*/
	protected void updateHeight() {
		AVLNode<T> derecho=getRight();
		AVLNode<T> izquierdo=getLeft();
		int maxHeight=0;
		if(izquierdo!=null) {
			maxHeight=izquierdo.getHeight();
		}
		if(derecho!=null && maxHeight<getRight().getHeight() ) {
			maxHeight=getRight().getHeight();
		}
		
		this.height=maxHeight+1;
		
	}
	
	/**
	 * Actualiza el factor de balance
	 */
	public void updateBalanceFactor() {
		if(getRight()==null && getLeft()==null) {
			this.balanceFactor=0;
		}else if(getRight()==null) {
			this.balanceFactor=-getLeft().getHeight();
		}
		else if(getLeft()==null) {
			this.balanceFactor=getRight().getHeight();
		}
		else {
			this.balanceFactor=getRight().getHeight()-getLeft().getHeight();
		}
		
	}



	/** (non-Javadoc)
	* @see BSTNode#getLeft()
	*/
	public AVLNode<T> getLeft(){
		return (AVLNode<T>) super.getLeft();
	}
	
	
	/** (non-Javadoc)
	* @see BSTNode#getRight()
	*/
	public AVLNode<T> getRight() {
		return (AVLNode<T>) super.getRight();
	}
	

	/** (non-Javadoc)
	* @see BSTNode#toString()
	* Añade factor de balance
	*/
	public String toString() {
		return super.toString()+":BF="+ getBF();
	}
}


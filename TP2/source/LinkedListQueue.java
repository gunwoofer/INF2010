
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexit� asymptotique: O(1)
	public AnyType peek() 
	{
		if(empty())
			return null;
		else
			return last.getNext().getData();
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if(size == 1) // 1 element dans la file
		{
			last = null; 
			size = 0;
		}
		else if(empty()) 		   // file vide
			throw new EmptyQueueException();
		else 					   // cas normal
		{
			last.setNext(last.getNext().getNext());
			size--;
		}
	}
	
	//Ajoute un element a la fin de la file
	//complexit� asymptotique: O(1)
	public void push(AnyType item)
	{	if(empty())
		{
			Node node = new Node(item, null);
			node.setNext(node);
			last = node;
		}
		else
		{
			Node node = new Node(item, last.getNext()); // on cree un nouveau noeud pointant sur le dernier element de la file
			last.setNext(node); // on change le pointeur de l'ancien dernier element de la file
			last = node; // on alloue le pointeur last sur le nouveau dernier element de la file
		}
		size++;
	}  
}
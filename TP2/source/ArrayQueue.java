

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		size = 0;
		startindex = 0;
		table = (AnyType[])new Object[1];
	
	}
	
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
			return table[startindex];
	}
	
	//Retire l'element en tete de file
	//complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if(empty())
			throw new EmptyQueueException();
		if(startindex == table.length - 1)
		{
			table[startindex] = null;
			startindex = (startindex+1)%table.length;
			size--;
		}
		else
		{
			table[startindex] = null;
			startindex=(startindex+1)%table.length;
			size--;
		}
		

	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire
	//complexit� asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		if(size ==( table.length-1) )
		{
			this.resize(2);
			table[size] = item;

		}
		else 
		{
			int pos=(size+startindex)%table.length;
			table[pos]=item;
		}
		size++;
	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexit� asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		/*ici pour le resize, plutot que de garder l'ordre du tableau, on tri les element
		selon leur anciennete. Cela permet de simplifier l'acces au element apres un resize. 
		 Cela permet ainsi d'avoir le premier element(le plus ancien), en 0*/
		AnyType[] temp = (AnyType[]) new Object[table.length * resizeFactor];
		int startindexTemp=startindex;
		for(int i = 0; i<size; i++)
		{
			temp[i]=table[startindexTemp];
			startindexTemp=(startindexTemp+1)%table.length;

		}
		table = temp;
		startindex=0;
	}   
}

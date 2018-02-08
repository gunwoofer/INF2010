import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_element = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_element));
		
		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
    
	private static int size(Stack<Integer> stack) {
		//A completer
		if(stack.empty()) { return 0; }
		
		int size = 0;
		Stack<Integer> stackTemporaire = new Stack<Integer>();
		
		while(!stack.empty()) {
			stackTemporaire.push(stack.pop());
			size++;
		}
		
		while(!stackTemporaire.empty()) {
			stack.push(stackTemporaire.pop());	//On oublie pas de le remplir de nouveau
		}
		
		return size;
    }
    
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		int pivot, element;
		Stack<Integer> stackTemporaire  = new Stack<Integer>();
		
		for(int size = 1; size < SortStackMain.size(stack); size++) {
			size = 1;
			pivot = stack.pop();
			
			while(!stack.empty()){
				element = stack.pop();
				if(element < pivot)
					stackTemporaire.push(element);
				else{
					stackTemporaire.push(pivot);
					pivot = element;
				}
			}
			
			while(!stackTemporaire.empty()){
				element = stackTemporaire.pop();
				if(element > pivot)
					stack.push(element);
				else{
					stack.push(pivot);
					pivot = element;
					size++;
				}
			}
		
			stack.push(pivot);
		}
		return stack;
	}
}

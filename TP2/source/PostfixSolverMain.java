import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		double a, b;
		//L'expression est separee en tokens selon les espaces
		for(String token : s.split("\\s")) 
		{
			//A completer
			switch(token) {
			case "+" :
				a = stack.pop();
				b = stack.pop();
				stack.push(a + b);
				break;
			case "*" :
				a = stack.pop();
				b = stack.pop();
				stack.push(a * b);
				break;
			case "-" :
				a = stack.pop();
				b = stack.pop();
				stack.push(b - a);
				break;
			case "/" :
				a = stack.pop();
				b = stack.pop();
				stack.push(b / a);
				break;
			default:
				stack.push(Double.parseDouble(token));
			}
			
		}
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}

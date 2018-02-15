import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
   static int p = 46337;
   
   int a, b;
   AnyType[] items;
      
   QuadraticSpacePerfectHashing()
   {
      a=b=0; items = null;
   }
   
   QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public int Size()
   {
      if( items == null ) return 0;
      
      return items.length;
   }
   
   public boolean contains(AnyType x )
   {
      if( Size() == 0 ) return false;
      
      if( a == 0 ) return ( items[0].equals(x) );
      int m = items.length;
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ( ( items[index] != null ) &&
             ( items[index].equals(x) ) );
   }
   
   
   @SuppressWarnings("unchecked")
   private void AllocateMemory(ArrayList<AnyType> array)
   {
      Random generator = new Random( System.nanoTime() );
      
      if(array == null || array.size() == 0)
      {
         // A completer
         return;
      }
      if(array.size() == 1)
      {
         a = b = 0;
         
         // A completer		
         int key = getKey(array.get(0), array);
         items[key] = array.get(0);
         return;
      }
      
      do
      {
    	  
         items = null;
         items = (AnyType[]) new Object[array.size()*array.size()];
         // A completer
         
         a = generator.nextInt(p) + 1;
         b = generator.nextInt(p);
         
         for (int i = 0; i < array.size(); i++) {
        	 int key = getKey(array.get(i), array);
        	 items[key] = array.get(i);
         }
         
      }
      while( collisionExists( array ) ); //Si collision avec ces valeurs de a et b on refait avec d autres valeurs
   }
   
   private int getKey(AnyType x, ArrayList<AnyType> array) {
	   // ((a·x.hashCode() + b) mod p) mod m
	   return ((a * x.hashCode() + b) % p ) % (array.size() * array.size());
   }
   
   @SuppressWarnings("unchecked")
   private boolean collisionExists(ArrayList<AnyType> array)
   {
      // A completer
	  int key1, key2;
      for (int i = 0; i < array.size(); i++) {
    	  key1 = getKey(array.get(i), array);
    	  for (int j = i + 1; j < array.size(); j++) {
    		  key2 = getKey(array.get(j), array);
    		  if(key1 == key2) {
    			  return true;
    		  }
    	  }
      }
      return false;
   }
}

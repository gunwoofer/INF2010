import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
   static int p = 46337;
   
   QuadraticSpacePerfectHashing<AnyType>[] data;
   int a, b;
   
   LinearSpacePerfectHashing()
   {
      a=b=0; data = null;
   }
   
   LinearSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
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
         data = (QuadraticSpacePerfectHashing<AnyType>[]) new Object[1];
         data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
         return;
      }
      
      // A completer
      
      this.data = new QuadraticSpacePerfectHashing[array.size()];
      
      //Tableau qui contient pour chaque index tous les elements qui ont la meme position
      ArrayList<ArrayList<AnyType>> tableauTemp = new ArrayList<ArrayList<AnyType>>(array.size());
      for(int i = 0; i < array.size(); i++) {
    	  tableauTemp.add(new ArrayList<AnyType>());	//Instancie chaque sous tableau
      }
      
      a = generator.nextInt(p) + 1;
      b = generator.nextInt(p);
      
      for (int i = 0; i < array.size(); i++) {
     	 int key = getKey(array.get(i), array);
     	 tableauTemp.get(key).add(array.get(i));
      }
      
      //Pour chaque sous tableau on utilise le hash quadratic parfait
      for (int i = 0; i < tableauTemp.size(); i++) {
    	  this.data[i] = new QuadraticSpacePerfectHashing<AnyType>();
    	  //System.out.println(i);
    	  //System.out.println(this.data[i]);
    	  //System.out.println(tableauTemp.get(i));
      	 this.data[i].SetArray(tableauTemp.get(i));
       }
      
   }
   
   
   private int getKey(AnyType x, ArrayList<AnyType> array) {
	   return ((a * x.hashCode() + b) % p ) % array.size();
   }
   
   public int Size()
   {
      if( data == null ) return 0;
      
      int size = 0;
      for(int i=0; i<data.length; ++i)
      {
         size += (data[i] == null ? 1 : data[i].Size());
      }
      return size;
   }
   
   public boolean contains(AnyType x )
   {
      if( Size() == 0 ) return false;
      
      int m = data.length;
      
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ((data[index] != null) && (data[index].contains(x)));
   }
}

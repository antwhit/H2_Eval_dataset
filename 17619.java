import java.util.*;

public class FireHelper{

  public static String[] watchList = {"n","k"};
  public static ArrayList<String> baseList = new ArrayList<String>(); 
  public static ArrayList<String> recurseList = new ArrayList<String>(); 
  public static ArrayList<String> paramList = new ArrayList<Stirng>(): 
 
  public static int permutations(int n,int k, Node parent){

    Node a = new Node();
    Fire.totalSteps += 2;
    if(parent != null)
    {
      parent.addChild(a);
      a.depth = parent.depth + 1; 
      if(a.depth > Fire.maxDepth)
      {
        Fire.maxDepth = a.depth;
      }
      a.parent = parent;
    }
    else
    {
        root = a;
        a.depth = 0;
        
    }
    
     
      if(k==0)
        a.retVal = String.valueOf(0);
      if(k==1)
        a.retVal = String.valueOf(n);
      return permcoeff(n,k)*permutations(n,k-1);
     
  }
  
  public static void doRecursive(Object[] params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    Class c = Class.forName("FireHelper");
    Method[] m = c.getDeclaredMethods();
    Object i = c.newInstance();
    
    m[0].invoke(i, params);
    
  }
  
   public static int permcoeff(int n,int k){
     return n-k+1;
   }
    
   /**GUI HELPER FUNCTIONS**/
   public static void buildCondLists(){ 
  
    baseList.add("{k == 0} 0");
    baseList.add("{k == 1} n");
    recurseList.add("{} permcoeff(n, k) * permutations(n, k - 1)");
  }
  
  
  public static void buildParamList(){
  
    paramList.add("int");
    paramList.add("int");
  
  }
  
  public static void makeLists(){
    buildCondList(); 
    buildParamList(); 
  }
  

  
}
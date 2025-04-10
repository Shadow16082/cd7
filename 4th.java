
import java.util.*;
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
       System.out.println("Enter productions:");
        while(true){
            String s=sc.nextLine();
            if(s.equals("done"))break;
        eliminateLeft(s);
        }
        sc.close();
    }
    public static void eliminateLeft(String prod){
        String []parts=prod.split("->");
        String lhs=parts[0].trim();
        String[] rhs=parts[1].split("\\|");
        String pre=findPre(rhs);
        if(pre.isEmpty())
        {
            System.out.println(prod);
        }
        System.out.println(lhs+"->"+pre+lhs+"'");
        List<String>suffixes=new ArrayList<>();
        for(String r:rhs){
            String suffix=r.trim().substring(pre.length());
            suffixes.add(suffix.isEmpty() ? "E" : suffix);
        }
        System.out.println(lhs+"->"+String.join("|",suffixes));
    }
    public static String findPre(String arr[]){
    String pre=arr[0].trim();
    for(String s:arr)
    while(!s.trim().startsWith(pre))
    pre=pre.substring(0,pre.length()-1);
    return pre;
 
}   
}

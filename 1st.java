import java.util.*;
class Main{
    public static void main(String args[]){
    Scanner s=new Scanner(System.in);
    String str=s.nextLine();
    for(int i=0;i<str.length();i++){
        int c=0;
        if(str.charAt(i)!='a'){
            c++;
        }
         if(str.charAt(i)!='b'){
            c++;
        } if(str.charAt(i)!='c'){
            c++;
        }
        if(c==3){
            System.out.println("String not valid");
            return;
        }
    }
    String ct=str.substring(str.length()-3);
    if(ct.equals("abc")){
        System.out.println("String Accaepted");
    }
    else{
        System.out.println("String not Accepted");
    }
}
}

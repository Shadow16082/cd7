import java.util.*;
public class shiftReduce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("No.of production : ");
        int n = sc.nextInt();
        sc.nextLine(); 
        String lhs[]=new String[n];
        String rhs[]=new String[n];
        System.out.println("Grammar : ");
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().split("->");
            lhs[i]=p[0];
            rhs[i]=p[1];
        }
        System.out.print("String : ");
        String inp = sc.nextLine();
        System.out.println("\nStack\tInputBuffer\tAction");
        String stk="";
        while (true) {
            char ch = inp.charAt(0);
            stk += ch;
            inp=inp.substring(1);
            System.out.println(stk + "\t\t" + inp+"\t\tShift " + ch);
            for (int j = 0; j < n; j++) {
                int idx = stk.indexOf(rhs[j]);
                if (idx != -1) {
                    stk = stk.substring(0, idx) + lhs[j];
                    System.out.println(stk+"\t\t"+inp+"\t\tReduce " + lhs[j] + "->" + rhs[j]);
                    j = -1; 
                }
            }
            if (stk.equals(lhs[0]) && inp.length()==0) {
                System.out.println("Accepted");
                break;
            }
            if (inp.length()==0) {
                System.out.println("Not Accepted");
                break;
            }
        }
    }
}

output:
No.of production : 4
Grammar : 
E->E+E
E->E*E
E->(E)
E->i
String : i*i+i

Stack	InputBuffer	Action
i		*i+i		Shift i
E		*i+i		Reduce E->i
E*		i+i		Shift *
E*i		+i		Shift i
E*E		+i		Reduce E->i
E		+i		Reduce E->E*E
E+		i		Shift +
E+i				Shift i
E+E				Reduce E->i
E				Reduce E->E+E
Accepted

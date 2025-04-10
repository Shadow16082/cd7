import java.util.*;
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of stack");
        int n=sc.nextInt();
        Stack<Integer>st=new Stack<>();
        while(true){
            System.out.println("Enter your option 1.Push\n2.Pop\n3.Display\n4.Exit\n");
            int pt=sc.nextInt();
            switch(pt){
                case 1:if(st.size()>=n){
                    System.out.println("Overflow");
                }
                else{
                    System.out.println("Enter your element to push");
                    int ele=sc.nextInt();
                    st.push(ele);
                }
                break;
                case 2:if(st.isEmpty()){
                    System.out.println("Underflow");
                }
                else{
                    System.out.println("Popped element "+st.pop());
                }
                break;
                case 3:if(st.isEmpty()){
                    System.out.println("Stack is empty");
                }
                else{
                    System.out.println(st);
                }
                break;
                case 4:
                    System.exit(0);
            }
        }
    }
}

output:
Enter the size of stack:
10
Enter your option 1.Push
2.Pop
3.Display
4.Exit

1
Enter your element to push
5
Enter your option 1.Push
2.Pop
3.Display
4.Exit

1
Enter your element to push
8
Enter your option 1.Push
2.Pop
3.Display
4.Exit

1
Enter your element to push
3
Enter your option 1.Push
2.Pop
3.Display
4.Exit

3
[5, 8, 3]
Enter your option 1.Push
2.Pop
3.Display
4.Exit

2
Popped element 3
Enter your option 1.Push
2.Pop
3.Display
4.Exit

3
[5, 8]
Enter your option 1.Push
2.Pop
3.Display
4.Exit

4

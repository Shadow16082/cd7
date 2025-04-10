import java.util.*;
public class ex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();
        String[] w = str.split(" ");

        for (int i = 0; i < w.length; i++) {
            if (w[i].equals("int") || w[i].equals("float") || w[i].equals("double") || 
                w[i].equals("char") || w[i].equals("String")) {
                System.out.println("'" + w[i] + "' is a Data Type ");
            } 
            else if (w[i].equals("=") || w[i].equals("+") || w[i].equals("-") || 
                     w[i].equals("*") || w[i].equals("/")) {
                System.out.println("'" + w[i] + "' is an Operator ");
            } 
            else if (w[i].equals(";") || w[i].equals(",") || w[i].equals("{") || 
                     w[i].equals("}") || w[i].equals("(") || w[i].equals(")")) {
                System.out.println("'" + w[i] + "' is a Symbol ");
            } 
            else if (w[i].matches("-?\\d+(\\.\\d+)?")) {  // Regex to check for numbers (integer or decimal)
                System.out.println("'" + w[i] + "' is a Number ");
            }
            else {
                System.out.println("'" + w[i] + "' is a Variable ");
            }
        }
        sc.close();
    }
}

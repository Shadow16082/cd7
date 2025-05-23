import java.util.Scanner;
public class LeftRecursionElimination
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Number of Productions: ");
    int num = scanner.nextInt();
    scanner.nextLine(); 
    System.out.println("Enter the grammar as A -> Aa / b:");
    for (int i = 0; i < num; i++)
    {
      String production = scanner.nextLine().trim();
      eliminateLeftRecursion(production);
    }
    scanner.close();
  }
  public static void eliminateLeftRecursion(String production)
  {
    String[] parts = production.split("->");
    char nonTerminal = parts[0].charAt(0);
    String[] choices = parts[1].split("/");
    System.out.println("GRAMMAR: " + production); 
    if (choices[0].startsWith("" + nonTerminal))
    {
      String beta = choices[0].substring(1); 
      System.out.println(nonTerminal + " is left recursive.");
      System.out.println(nonTerminal + " -> " + choices[1].trim() + nonTerminal + "'");
      System.out.println(nonTerminal + "' -> " + beta + nonTerminal + "' / epsilon");
    }
    else
    {
      System.out.println(nonTerminal + " is not left recursive.");
    }
  }
}

OUTPUT:
Enter Number of Productions: 1
Enter the grammar as A -> Aa / b:
E->E+T/T
GRAMMAR: E->E+T/T
E is left recursive.
E -> TE'
E' -> +TE' / epsilon

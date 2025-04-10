import java.util.*;
public class LL1Parser {
    static String[][] grammar;
    static List<String> nonTerminals = new ArrayList<>();
    static String[] first, follow;
    static Map<String, Map<Character, String>> parsingTable = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of productions:");
        int n = sc.nextInt();
        sc.nextLine();
        grammar = new String[n][2];
        System.out.println("Enter productions (use '@' for epsilon):");
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split("->");
            grammar[i][0] = parts[0].trim();
            grammar[i][1] = parts[1].trim();
            nonTerminals.add(parts[0].trim());
        }
        for (String nt : nonTerminals)
            parsingTable.put(nt, new HashMap<>());
        first = new String[n];
        follow = new String[n];
        for (int i = 0; i < n; i++)
            first[i] = computeFirst(i);
        for (int i = 0; i < n; i++)
            follow[i] = computeFollow(i);
        for (int i = 0; i < n; i++) {
            if (first[i].contains("@")) {
                for (char c : follow[i].toCharArray())
                    parsingTable.get(grammar[i][0]).put(c, "@");
            }
        }
        System.out.println("Enter string to check:");
        String inputStr = sc.nextLine();
        if (parse(inputStr + "$"))
            System.out.println("String is accepted.");
        else
            System.out.println("String is rejected.");
    }
    static String computeFirst(int i) {
        String[] productions = grammar[i][1].split("\\|");
        StringBuilder result = new StringBuilder();
        for (String prod : productions) {
            char firstChar = prod.charAt(0);
            if (!nonTerminals.contains(firstChar + "")) {
                result.append(firstChar);
                parsingTable.get(grammar[i][0]).put(firstChar, prod);
            } else {
                String tempFirst = computeFirst(nonTerminals.indexOf(firstChar + ""));
                result.append(tempFirst);
                char nextChar = (prod.length() > 1) ? prod.charAt(1) : 0;
                if (tempFirst.contains("@") && nextChar != 0) {
                    if (!nonTerminals.contains(nextChar + ""))
                        result.append(nextChar);
                    else
                        result.append(computeFirst(nonTerminals.indexOf(nextChar + "")));
                }
                parsingTable.get(grammar[i][0]).put(tempFirst.charAt(tempFirst.length() - 1), prod);
            }
        }
        return result.toString();
    }
    static String computeFollow(int i) {
        Set<Character> result = new HashSet<>();
        if (i == 0)
            result.add('$'); 
        for (int j = 0; j < grammar.length; j++) {
            if (grammar[j][0].equals(nonTerminals.get(i)))
                continue;
            for (String prod : grammar[j][1].split("\\|")) {
                String nt = nonTerminals.get(i);
                int pos = prod.indexOf(nt);
                if (pos != -1) {
                    if (pos == prod.length() - 1) {
                        String followOfLeft = follow[nonTerminals.indexOf(grammar[j][0])];
                        if (followOfLeft == null)
                            followOfLeft = computeFollow(nonTerminals.indexOf(grammar[j][0]));
                        for (char c : followOfLeft.toCharArray())
                            result.add(c);
                    } else {
                        char nextChar = prod.charAt(pos + 1);
                        if (!nonTerminals.contains(nextChar + "")) {
                            result.add(nextChar);
                        } else {
                            String firstNext = first[nonTerminals.indexOf(nextChar + "")];
                            for (char c : firstNext.toCharArray())
                                if (c != '@') result.add(c);

                            if (firstNext.contains("@")) {
                                String followOfLeft = follow[nonTerminals.indexOf(grammar[j][0])];
                                if (followOfLeft == null)
                                    followOfLeft = computeFollow(nonTerminals.indexOf(grammar[j][0]));
                                for (char c : followOfLeft.toCharArray())
                                    result.add(c);
                            }
                        }
                    }
                }
            }
        }
        StringBuilder followStr = new StringBuilder();
        for (char c : result)
            followStr.append(c);
        return followStr.toString();
    }
    static boolean parse(String input) {
        Stack<Character> stack = new Stack<>();
        stack.push('$');
        stack.push(nonTerminals.get(0).charAt(0));
        int i = 0;
        while (i < input.length()) {
            if (stack.isEmpty())
                return false;
            if (stack.peek() == input.charAt(i)) {
                stack.pop();
                i++;
                continue;
            }
            char top = stack.pop();
            String production = parsingTable.getOrDefault(top + "", new HashMap<>()).get(input.charAt(i));
            if (production == null)
                return false;
            if (!production.equals("@")) {
                for (int j = production.length() - 1; j >= 0; j--)
                    stack.push(production.charAt(j));
            }
        }
        return stack.isEmpty();
    }
}

output:
Enter number of productions:
5
Enter productions (use '@' for epsilon):
E->TA
A->+TA|@
T->*FB|@
F->(E)|i
T->FB
Enter string to check:
i+i*i
String is rejected.

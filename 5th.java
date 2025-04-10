import java.util.*;
import java.io.*;
public class FstNFlw {
    static char[] nonTerminals, terminals;
    static int ntLen, tLen;
    static String[][] grammar;
    static String[] firstSet, followSet;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the non-terminals (e.g., EAT)");
        String nt = br.readLine();
        ntLen = nt.length();
        nonTerminals = nt.toCharArray();
        System.out.println("Enter the terminals (e.g., +*i)");
        String t = br.readLine();
        tLen = t.length();
        terminals = t.toCharArray();
        grammar = new String[ntLen][];
        System.out.println("Specify the grammar (Use '9' for epsilon)");
        for (int i = 0; i < ntLen; i++) {
            System.out.println("Enter the number of productions for " + nonTerminals[i] + ":");
            int n = Integer.parseInt(br.readLine());
            grammar[i] = new String[n];
            System.out.println("Enter the productions:");
            for (int j = 0; j < n; j++) {
                grammar[i][j] = br.readLine();
            }
        }
        firstSet = new String[ntLen];
        for (int i = 0; i < ntLen; i++) {
            firstSet[i] = first(i);
        }
        System.out.println("\nFIRST sets:");
        for (int i = 0; i < ntLen; i++) {
            System.out.println(nonTerminals[i] + ": " + removeDuplicates(firstSet[i]));
        }
        followSet = new String[ntLen];
        for (int i = 0; i < ntLen; i++) {
            followSet[i] = follow(i);
        }
        System.out.println("\nFOLLOW sets:");
        for (int i = 0; i < ntLen; i++) {
            System.out.println(nonTerminals[i] + ": " + removeDuplicates(followSet[i]));
        }
    }
    static String first(int i) {
        String result = "";
        for (String prod : grammar[i]) {
            for (int k = 0; k < prod.length(); k++) {
                char symbol = prod.charAt(k);
                boolean found = false;
                for (int l = 0; l < ntLen; l++) {
                    if (symbol == nonTerminals[l]) {
                        String str = first(l);
                        if (!(str.length() == 1 && str.charAt(0) == '9')) {
                            result += str;
                        }
                        if (str.contains("9")) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result += symbol;
                    break;
                }
            }
        }
        return result;
    }
    static String follow(int i) {
        String result = "";
        if (i == 0) result += "$"; 
        for (int j = 0; j < ntLen; j++) {
            for (String prod : grammar[j]) {
                for (int l = 0; l < prod.length(); l++) {
                    if (prod.charAt(l) == nonTerminals[i]) {
                        if (l == prod.length() - 1) {
                            if (j < i) result += followSet[j];
                        } else {
                            char next = prod.charAt(l + 1);
                            boolean isNT = false;
                            for (int m = 0; m < ntLen; m++) {
                                if (next == nonTerminals[m]) {
                                    for (char c : firstSet[m].toCharArray()) {
                                        if (c != '9') result += c;
                                    }
                                    if (firstSet[m].contains("9")) {
                                        if (l + 1 == prod.length() - 1)
                                            result += follow(j);
                                        else
                                            result += follow(m);
                                    }
                                    isNT = true;
                                    break;
                                }
                            }
                            if (!isNT) result += next;
                        }
                    }
                }
            }
        }
        return result;
    }
    static String removeDuplicates(String str) {
        boolean[] seen = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (!seen[ch]) {
                seen[ch] = true;
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}

output:
Enter the non-terminals (e.g., EAT)
SABC
Enter the terminals (e.g., +*i)
ab
Specify the grammar (Use '9' for epsilon)
Enter the number of productions for S:
1
Enter the productions:
aABC
Enter the number of productions for A:
2
Enter the productions:
a
bb
Enter the number of productions for B:
2
Enter the productions:
a
a
Enter the number of productions for C:
2
Enter the productions:
b
a

FIRST sets:
S: a
A: ab
B: a
C: ba

FOLLOW sets:
S: $
A: a
B: ba
C: $

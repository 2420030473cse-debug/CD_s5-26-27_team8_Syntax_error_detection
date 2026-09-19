import java.util.*;

public class SyntaxErrorDetector {

    static int parentheses = 0;
    static int braces = 0;

    static void checkLine(String line, int lineNumber) {

        String code = line.trim();

        if (code.isEmpty()) {
            return;
        }

        for (char ch : code.toCharArray()) {

            if (ch == '(') {
                parentheses++;
            }

            if (ch == ')') {
                parentheses--;

                if (parentheses < 0) {
                    System.out.println(
                        "Line " + lineNumber +
                        ": Unexpected ')'"
                    );
                    parentheses = 0;
                }
            }

            if (ch == '{') {
                braces++;
            }

            if (ch == '}') {
                braces--;

                if (braces < 0) {
                    System.out.println(
                        "Line " + lineNumber +
                        ": Unexpected '}'"
                    );
                    braces = 0;
                }
            }
        }

        if (code.startsWith("int ")
                || code.startsWith("float ")
                || code.startsWith("double ")
                || code.startsWith("char ")) {

            if (!code.endsWith(";")
                    && !code.endsWith("{")) {

                System.out.println(
                    "Line " + lineNumber +
                    ": Missing semicolon ';'"
                );
            }
        }

        else if (code.contains("=")
                && !code.startsWith("if")
                && !code.startsWith("while")
                && !code.startsWith("for")) {

            if (!code.endsWith(";")) {

                System.out.println(
                    "Line " + lineNumber +
                    ": Missing semicolon ';'"
                );
            }
        }

        if (code.startsWith("if")
                || code.startsWith("while")
                || code.startsWith("for")) {

            if (!code.contains("(")
                    || !code.contains(")")) {

                System.out.println(
                    "Line " + lineNumber +
                    ": Missing parentheses"
                );
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
            "=================================="
        );

        System.out.println(
            "     SYNTAX ERROR DETECTOR"
        );

        System.out.println(
            "=================================="
        );

        System.out.println(
            "Enter your code."
        );

        System.out.println(
            "Type END to finish."
        );

        System.out.println();

        int lineNumber = 1;

        boolean errorFound = false;

        while (true) {

            String line = scanner.nextLine();

            if (line.equals("END")) {
                break;
            }

            checkLine(line, lineNumber);

            lineNumber++;
        }

        if (parentheses != 0) {

            System.out.println(
                "Error: Unbalanced parentheses"
            );

            errorFound = true;
        }

        if (braces != 0) {

            System.out.println(
                "Error: Unbalanced braces"
            );

            errorFound = true;
        }

        if (!errorFound) {

            System.out.println();
            System.out.println(
                "Syntax checking completed."
            );

            System.out.println(
                "No major syntax errors found."
            );
        }

        scanner.close();
    }
}
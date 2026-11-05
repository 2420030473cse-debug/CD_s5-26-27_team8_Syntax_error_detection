import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Stack;

public class SyntaxErrorDetector extends JFrame {

    private JTextArea codeArea;
    private JTextArea resultArea;
    private JLabel statusLabel;

    // Colors
    private final Color BACKGROUND = new Color(245, 247, 252);
    private final Color DARK = new Color(30, 35, 50);
    private final Color BLUE = new Color(52, 120, 246);
    private final Color GREEN = new Color(34, 197, 94);
    private final Color RED = new Color(239, 68, 68);
    private final Color ORANGE = new Color(245, 158, 11);

    public SyntaxErrorDetector() {

        setTitle("Syntax Error Detection & Correction Tool");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK);
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel(
                "SYNTAX ERROR DETECTION & CORRECTION"
        );

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));

        JLabel subtitle = new JLabel(
                "Compiler Design Mini Project"
        );

        subtitle.setForeground(
                new Color(190, 200, 220)
        );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(
                new BoxLayout(titlePanel, BoxLayout.Y_AXIS)
        );

        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        JLabel icon = new JLabel("⌘");
        icon.setForeground(
                new Color(100, 180, 255)
        );

        icon.setFont(
                new Font("Segoe UI", Font.BOLD, 40)
        );

        header.add(icon, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ================= CENTER =================

        JPanel centerPanel =
                new JPanel(new GridLayout(1, 2, 15, 0));

        centerPanel.setBackground(BACKGROUND);

        // ================= INPUT =================

        JPanel inputPanel =
                new JPanel(new BorderLayout(10, 10));

        inputPanel.setBackground(Color.WHITE);

        inputPanel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(220, 225, 235)
                        ),
                        new EmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        JLabel inputLabel =
                new JLabel("SOURCE CODE");

        inputLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        inputLabel.setForeground(DARK);

        codeArea = new JTextArea();

        codeArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        15
                )
        );

        codeArea.setBackground(
                new Color(25, 29, 40)
        );

        codeArea.setForeground(
                new Color(230, 235, 245)
        );

        codeArea.setCaretColor(Color.WHITE);

        codeArea.setTabSize(4);

        codeArea.setBorder(
                new EmptyBorder(
                        12, 12, 12, 12
                )
        );

        JScrollPane codeScroll =
                new JScrollPane(codeArea);

        inputPanel.add(
                inputLabel,
                BorderLayout.NORTH
        );

        inputPanel.add(
                codeScroll,
                BorderLayout.CENTER
        );

        // ================= OUTPUT =================

        JPanel outputPanel =
                new JPanel(new BorderLayout(10, 10));

        outputPanel.setBackground(Color.WHITE);

        outputPanel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(220, 225, 235)
                        ),
                        new EmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );

        JLabel outputLabel =
                new JLabel("ANALYSIS RESULT");

        outputLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        outputLabel.setForeground(DARK);

        resultArea = new JTextArea();

        resultArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        resultArea.setBackground(
                new Color(248, 250, 252)
        );

        resultArea.setForeground(DARK);

        resultArea.setEditable(false);

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                new EmptyBorder(
                        12, 12, 12, 12
                )
        );

        JScrollPane resultScroll =
                new JScrollPane(resultArea);

        outputPanel.add(
                outputLabel,
                BorderLayout.NORTH
        );

        outputPanel.add(
                resultScroll,
                BorderLayout.CENTER
        );

        centerPanel.add(inputPanel);
        centerPanel.add(outputPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ================= BUTTONS =================

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.setBackground(BACKGROUND);

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                5
                        )
                );

        buttonPanel.setOpaque(false);

        JButton checkButton =
                createButton(
                        "CHECK CODE",
                        BLUE
                );

        JButton clearButton =
                createButton(
                        "CLEAR",
                        RED
                );

        JButton sampleButton =
                createButton(
                        "LOAD SAMPLE",
                        ORANGE
                );

        buttonPanel.add(checkButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(sampleButton);

        statusLabel =
                new JLabel("Ready");

        statusLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                statusLabel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ================= EVENTS =================

        checkButton.addActionListener(
                e -> checkCode()
        );

        clearButton.addActionListener(
                e -> clearCode()
        );

        sampleButton.addActionListener(
                e -> loadSample()
        );
    }

    // ==================================================
    // BUTTON CREATION
    // ==================================================

    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        12, 22, 12, 22
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // ==================================================
    // LOAD SAMPLE
    // ==================================================

    private void loadSample() {

        String sample =
                "int a = 10;\n" +
                "int b = 20;\n" +
                "\n" +
                "if (a < b) {\n" +
                "    System.out.println(a);\n" +
                "}\n";

        codeArea.setText(sample);

        resultArea.setText("");

        statusLabel.setText(
                "Sample code loaded"
        );

        statusLabel.setForeground(ORANGE);
    }

    // ==================================================
    // CLEAR
    // ==================================================

    private void clearCode() {

        codeArea.setText("");
        resultArea.setText("");

        statusLabel.setText("Ready");
        statusLabel.setForeground(DARK);
    }

    // ==================================================
    // MAIN CHECKING METHOD
    // ==================================================

    private void checkCode() {

        String sourceCode =
                codeArea.getText();

        if (sourceCode.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter source code.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String[] lines =
                sourceCode.split("\\r?\\n");

        StringBuilder result =
                new StringBuilder();

        int errorCount = 0;

        Stack<Character> bracketStack =
                new Stack<>();

        Stack<Integer> lineStack =
                new Stack<>();

        // ==============================================
        // CHECK EACH LINE
        // ==============================================

        for (int i = 0; i < lines.length; i++) {

            String originalLine = lines[i];

            String code = originalLine.trim();

            int lineNumber = i + 1;

            if (code.isEmpty()) {
                continue;
            }

            // ==========================================
            // 1. CHECK BRACKETS
            // ==========================================

            for (char ch : code.toCharArray()) {

                if (
                        ch == '(' ||
                        ch == '{' ||
                        ch == '['
                ) {

                    bracketStack.push(ch);
                    lineStack.push(lineNumber);
                }

                else if (
                        ch == ')' ||
                        ch == '}' ||
                        ch == ']'
                ) {

                    if (bracketStack.isEmpty()) {

                        addError(
                                result,
                                lineNumber,
                                "Unexpected '" + ch + "'",
                                "Check whether the opening bracket is missing."
                        );

                        errorCount++;
                    }

                    else {

                        char opening =
                                bracketStack.pop();

                        lineStack.pop();

                        if (
                                !isMatchingBracket(
                                        opening,
                                        ch
                                )
                        ) {

                            addError(
                                    result,
                                    lineNumber,
                                    "Mismatched bracket '" + ch + "'",
                                    "Check the matching opening and closing brackets."
                            );

                            errorCount++;
                        }
                    }
                }
            }

            // ==========================================
            // 2. CHECK MISSING SEMICOLON
            // ==========================================

            if (needsSemicolon(code)) {

                if (!code.endsWith(";")) {

                    addError(
                            result,
                            lineNumber,
                            "Missing semicolon ';'",
                            "Add ';' at the end of the statement."
                    );

                    errorCount++;
                }
            }

            // ==========================================
            // 3. CHECK IF / WHILE / FOR
            // ==========================================

            if (
                    code.startsWith("if")
                    ||
                    code.startsWith("while")
                    ||
                    code.startsWith("for")
            ) {

                if (
                        !code.contains("(")
                        ||
                        !code.contains(")")
                ) {

                    addError(
                            result,
                            lineNumber,
                            "Missing parentheses",
                            "Write the condition inside '( )'."
                    );

                    errorCount++;
                }
            }

            // ==========================================
            // 4. CHECK COMMON KEYWORD SPELLINGS
            // ==========================================

            String keywordError =
                    findIncorrectKeyword(code);

            if (keywordError != null) {

                String correction =
                        getKeywordCorrection(
                                keywordError
                        );

                addError(
                        result,
                        lineNumber,
                        "Incorrect keyword '" +
                                keywordError +
                                "'",
                        "Did you mean '" +
                                correction +
                                "'?"
                );

                errorCount++;
            }
        }

        // ==============================================
        // 5. CHECK REMAINING BRACKETS
        // ==============================================

        while (!bracketStack.isEmpty()) {

            char opening =
                    bracketStack.pop();

            int lineNumber =
                    lineStack.pop();

            char closing =
                    getClosingBracket(opening);

            addError(
                    result,
                    lineNumber,
                    "Unmatched '" +
                            opening +
                            "'",
                    "Add '" +
                            closing +
                            "'."
            );

            errorCount++;
        }

        // ==============================================
        // DISPLAY RESULT
        // ==============================================

        if (errorCount == 0) {

            resultArea.setForeground(GREEN);

            resultArea.setText(
                    "SUCCESS!\n\n" +
                    "No major syntax errors found.\n\n" +
                    "The source code passed all basic\n" +
                    "syntax checks."
            );

            statusLabel.setText(
                    "No errors found"
            );

            statusLabel.setForeground(GREEN);
        }

        else {

            result.insert(
                    0,
                    "SYNTAX ERRORS FOUND\n\n"
            );

            result.append(
                    "\nTotal Errors Detected: "
                            + errorCount
            );

            resultArea.setForeground(RED);

            resultArea.setText(
                    result.toString()
            );

            statusLabel.setText(
                    errorCount +
                            " error(s) detected"
            );

            statusLabel.setForeground(RED);
        }
    }

    // ==================================================
    // SEMICOLON CHECK
    // ==================================================

    private boolean needsSemicolon(
            String code
    ) {

        // Variable declarations

        if (
                code.startsWith("int ")
                ||
                code.startsWith("float ")
                ||
                code.startsWith("double ")
                ||
                code.startsWith("char ")
                ||
                code.startsWith("boolean ")
                ||
                code.startsWith("String ")
        ) {

            return true;
        }

        // Assignment statements

        if (
                code.contains("=")
                &&
                !code.contains("==")
                &&
                !code.startsWith("if")
                &&
                !code.startsWith("while")
                &&
                !code.startsWith("for")
        ) {

            return true;
        }

        // Print statements

        if (
                code.contains("System.out.print")
        ) {

            return true;
        }

        // Return statement

        if (
                code.startsWith("return ")
                ||
                code.equals("return")
        ) {

            return true;
        }

        return false;
    }

    // ==================================================
    // KEYWORD CHECK
    // ==================================================

    private String findIncorrectKeyword(
            String code
    ) {

        String[] words =
                code.split("[\\s();{}\\[\\].]+");

        String[] wrongKeywords = {

                "innt",
                "flot",
                "duble",
                "charr",
                "booleean",
                "retrun",
                "publc",
                "privte",
                "statc",
                "clas",
                "vood",
                "whille",
                "iff",
                "els",
                "fr"
        };

        for (String word : words) {

            for (String wrong : wrongKeywords) {

                if (word.equals(wrong)) {

                    return wrong;
                }
            }
        }

        return null;
    }

    // ==================================================
    // KEYWORD CORRECTION
    // ==================================================

    private String getKeywordCorrection(
            String wrong
    ) {

        switch (wrong) {

            case "innt":
                return "int";

            case "flot":
                return "float";

            case "duble":
                return "double";

            case "charr":
                return "char";

            case "booleean":
                return "boolean";

            case "retrun":
                return "return";

            case "publc":
                return "public";

            case "privte":
                return "private";

            case "statc":
                return "static";

            case "clas":
                return "class";

            case "vood":
                return "void";

            case "whille":
                return "while";

            case "iff":
                return "if";

            case "els":
                return "else";

            case "fr":
                return "for";

            default:
                return "correct keyword";
        }
    }

    // ==================================================
    // BRACKET MATCHING
    // ==================================================

    private boolean isMatchingBracket(
            char opening,
            char closing
    ) {

        return
                (opening == '(' && closing == ')')
                ||
                (opening == '{' && closing == '}')
                ||
                (opening == '[' && closing == ']');
    }

    private char getClosingBracket(
            char opening
    ) {

        if (opening == '(') {
            return ')';
        }

        if (opening == '{') {
            return '}';
        }

        return ']';
    }

    // ==================================================
    // DISPLAY ERROR
    // ==================================================

    private void addError(
            StringBuilder result,
            int line,
            String error,
            String suggestion
    ) {

        result.append(
                "ERROR\n"
        );

        result.append(
                "Line "
        );

        result.append(line);

        result.append(
                ": "
        );

        result.append(error);

        result.append(
                "\n"
        );

        result.append(
                "Suggestion: "
        );

        result.append(suggestion);

        result.append(
                "\n\n"
        );
    }

    // ==================================================
    // MAIN
    // ==================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    SyntaxErrorDetector app =
                            new SyntaxErrorDetector();

                    app.setVisible(true);
                }
        );
    }
}
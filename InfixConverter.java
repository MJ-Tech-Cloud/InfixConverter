import java.util.Stack;

public class InfixConverter {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    static String infixToPostfix(String exp) {
        static String infixToPrefix(String exp) {
    StringBuilder input = new StringBuilder(exp);

    // Step 1: Reverse string
    input.reverse();

    // Step 2: Swap brackets
    for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) == '(')
            input.setCharAt(i, ')');
        else if (input.charAt(i) == ')')
            input.setCharAt(i, '(');
    }

    // Step 3: Convert to postfix
    String postfix = infixToPostfix(input.toString());

    // Step 4: Reverse again
    return new StringBuilder(postfix).reverse().toString();
}
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(c))
                result += c;

            // (
            else if (c == '(')
                stack.push(c);

            // )
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();
                stack.pop();
            }

            // Operator
            else {
                while (!stack.isEmpty() &&
                        precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty())
            result += stack.pop();

        return result;
    }

    public static void main(String[] args) {
        String infix = "A+B*C";
        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix: " + infixToPrefix(infix));
    }
}
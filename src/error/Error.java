package error;

import lexer.token.Token;
import utils.TextInput;

/**
 * 用于记录 token 的行数和列数
 */
public class Error extends RuntimeException {
    public static Integer lineno = 1;
    public static Integer column = 1;

    public static void showError(Token token, String mesg) {
        System.out.println("\033[31m" + mesg);
        System.out.println("at Row " + token.getLineno() + ", Column " + token.getColumn());
        System.out.println();
        String[] split = TextInput.inputText.split("\n");

        for (int i = 0; i < split.length; i++) {
            if (i == token.getLineno()) {
                System.out.println(split[i - 1]);
            }
        }

        for (int i = 0; i < token.getColumn() - 1; i++) {
            System.out.print(" ");
        }

        System.out.println("^");
        System.exit(0);
    }
}

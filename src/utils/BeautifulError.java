package utils;

import lexer.token.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BeautifulError {

    public static void print(Token token, String mesg) {


        System.out.println("\033[31m" + mesg);
        System.out.println("@ Lineno:" + token.getLineno() + ", Column:" + token.getColumn());
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

    private static String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        //将文件读取到字节数组
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}

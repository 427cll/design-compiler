package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextInput {
    public static String inputText;

    static {
        inputText = getInputText();
    }

    private static String getInputText()  {
        String filePath = "src/source-code";
        return readFile(filePath);
    }

    private static String readFile(String fileName)  {
        Path path = Paths.get(fileName);
        //将文件读取到字节数组
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}

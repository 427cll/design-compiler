import lexer.Lexer;
import parser.Parser;
import parser.nodes.Program;
import visitor.SemanticAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        //1. 读入源代码
        String filePath = "src/source-code";
        String inputText = readFile(filePath);

        //2. 词法分析
        Lexer lexer = new Lexer(inputText);
        //3. 语法分析
        Parser parser = new Parser(lexer, inputText); //初始化语法分析器
        Program program = parser.parse();
        System.out.println(program.toString());
        //4. 语义分析
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        semanticAnalyzer.analyze(program);
        //5. 代码生成

    }

    private static String readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        //将文件读取到字节数组
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}

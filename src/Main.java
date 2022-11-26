import analyzer.Analyzer;
import generator.Generator;
import lexer.Lexer;
import parser.Parser;
import parser.nodes.Program;
import utils.TextInput;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //1. 读入源代码
        String inputText = TextInput.inputText;

        //2. 词法分析
        Lexer lexer = new Lexer(inputText);

        //3. 语法分析
        Parser parser = new Parser(lexer, inputText); //初始化语法分析器
        Program program = parser.parse();

        //4. 语义分析
        Analyzer analyzer = new Analyzer();
        analyzer.analyze(program);

        //5. 代码生成
        Generator generator = new Generator();
        generator.generate(program);


    }


}

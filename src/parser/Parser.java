package parser;

import lexer.Lexer;
import lexer.token.Token;
import lexer.token.TokenType;
import parser.nodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private final Lexer lexer;
    private Token lookAhead;
    String text;

    public Parser(Lexer lexer, String text) {
        this.lexer = lexer;
        lookAhead = lexer.getNextToken();
        this.text = text;
    }

    public Program parse() {
        return this.Program();
    }

    private Program Program() {
        List<FuncDecl> funcDeclList = new ArrayList<>();
        while (lookAhead.getType() != TokenType.TK_EOF) {
            funcDeclList.add(this.FunctionDeclaration());
        }
        return new Program(funcDeclList);
    }

    /*
     * ★★★★★★★★★★★ 核心方法 ★★★★★★★★★★★★
     */
    private FuncDecl FunctionDeclaration() {
        Type type = this.Type();

        Token funcNameToken = this.eat(TokenType.TK_IDENT);

        List<FormalParam> params = this.FormalParameterList();

        BlockStatement blockStatement = ((BlockStatement) this.BlockStatement().get("BlockStatement"));

        return new FuncDecl(type, funcNameToken.getValue(), params, blockStatement);
    }


    private List<FormalParam> FormalParameterList() {
        List<FormalParam> formalParams = new ArrayList<>();
        this.eat(TokenType.TK_LPAREN);// 把这个 eat 圆括号推迟到方法内部了，因为我感觉，FormalList只会出现一次。
        if (this.lookAhead.getType() != TokenType.TK_RPAREN) {
            //这里还不好用 do while
            formalParams.add(this.FormalParam());
            while (this.lookAhead.getType() != TokenType.TK_RPAREN) {
                this.eat(TokenType.TK_COMMA);
                formalParams.add(this.FormalParam());
            }
        }
        this.eat(TokenType.TK_RPAREN);
        return formalParams;
    }


    /**
     * 本来有个 stopLookAhead参数，在这里用不着
     */
    private List<Object> StatementList() {

        List<Object> statementList = new ArrayList<>();
        while (this.lookAhead.getType() != TokenType.TK_EOF && this.lookAhead.getType() != TokenType.TK_RBRACE) {
            statementList.add(this.Statement());
        }
        return statementList;
    }

    /**
     * statement: ExpressionStatement
     * | ReturnStatement
     * | BlockStatement
     * | VariableStatement
     * ;
     *
     * @return 注意返回值不同情况下类型不同
     */
    private Map<String, Object> Statement() {
        Token token = this.lookAhead;
        return switch (token.getType()) {
            case TK_RETURN -> this.ReturnStatement();
            case TK_LBRACE -> this.BlockStatement();
            case TK_INT -> this.VariableStatement();// 只用 int 类型
            default -> this.ExpressionStatement();
        };
    }

    private Map<String,Object> ExpressionStatement() {
        /*
         * 目前的表达式只能是简单的  赋值表达式。。。
         */


        return this.AssignmentStatement();
    }

    private Map<String,Object> AssignmentStatement() {
        Identifier left = this.Identifier();
        String operator = this.AssignmentOperator().getValue();
        Integer right = Integer.parseInt(this.NumericLiteral().getValue());
        this.eat(TokenType.TK_SEMICOLON);
        Map<String,Object> map=new HashMap<>();
        map.put("ExpressionStatement",new AssignmentStatement(left, operator, right) );
        return map;
    }

    private Token NumericLiteral() {
        return this.eat(TokenType.TK_INTEGER_CONST);
    }

    private Token AssignmentOperator() {
        return this.eat(TokenType.TK_ASSIGN);
    }


    private Map<String, Object> BlockStatement() {
        this.eat(TokenType.TK_LBRACE);
        List<Object> statementNodes = this.StatementList();
        this.eat(TokenType.TK_RBRACE);
        Map<String, Object> map = new HashMap<>();
        map.put("BlockStatement", new BlockStatement(statementNodes));
        return map;
    }

    private FormalParam FormalParam() {
        Type type = this.Type();
        Identifier var = this.Identifier();
        return new FormalParam(type, var);
    }

    private Identifier Identifier() {
        Token token = this.eat(TokenType.TK_IDENT);
        return new Identifier(token);
    }

    private Type Type() {
        /*
          类型暂时只用 int
        */
        Token token = this.eat(TokenType.TK_INT);
        return new Type(token);
    }

    private Map<String, Object> VariableStatement() {
        Type type = this.Type();
        List<VariableDecl> declarations = this.VariableDeclList();
        this.eat(TokenType.TK_SEMICOLON);
        Map<String, Object> map = new HashMap<>();
        map.put("VariableStatement", new VariableStatement(type, declarations));
        return map;
    }

    private List<VariableDecl> VariableDeclList() {
        List<VariableDecl> declarations = new ArrayList<>();
        declarations.add(this.VariableDecl());
        while (this.lookAhead.getType() == TokenType.TK_COMMA) {
            this.eat(TokenType.TK_COMMA);
            declarations.add(this.VariableDecl());
        }
        return declarations;
    }

    private VariableDecl VariableDecl() {
        Identifier identifier = this.Identifier();

        Integer init =
                this.lookAhead.getType() == TokenType.TK_COMMA || this.lookAhead.getType() == TokenType.TK_SEMICOLON
                        ? null
                        : this.VariableInitializer();
        return new VariableDecl(identifier, init);
    }

    private Integer VariableInitializer() {
        this.eat(TokenType.TK_ASSIGN);
        int value = Integer.parseInt(this.eat(TokenType.TK_INTEGER_CONST).getValue());
        return value;
    }


    private Map<String, Object> ReturnStatement() {
        Token token = this.eat(TokenType.TK_RETURN);
        Integer right = Integer.parseInt(this.eat(TokenType.TK_INTEGER_CONST).getValue());
        this.eat(TokenType.TK_SEMICOLON);
        Map<String, Object> map = new HashMap<>();
        map.put("ReturnStatement", new ReturnStatement(token, right));
        return map;
    }

    private Token eat(TokenType tokenType) {
        Token token = this.lookAhead;
        if (token.getType() != tokenType) {
            System.out.println("\033[31m" + "@ Lineno:" + token.getLineno() + ", Column:" + token.getColumn());
            System.out.println("Unexpected token '" + token.getType() + "', expected :'" + tokenType + "'; ");
            System.out.println();

            String[] split = text.split("\n");

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
        this.lookAhead = this.lexer.getNextToken();
        return token;
    }
}
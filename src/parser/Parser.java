package parser;

import error.UnexpectedTokenException;
import lexer.Lexer;
import lexer.token.Token;
import lexer.token.TokenType;
import parser.nodes.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;
    private Token lookAhead;
    String text;
    String currFuncName;

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

        Identifier identifier = this.Identifier();

        this.currFuncName = identifier.getName();

        List<FormalParam> params = this.FormalParameterList();

        BlockStatement blockStatement = ((BlockStatement) this.BlockStatement());


        return new FuncDecl(type, identifier, params, blockStatement);
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
     * 本来有个 stopLookAhead 参数，在这里用不着
     */
    private List<ASTNode> StatementList() {
        List<ASTNode> statementList = new ArrayList<>();
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
    private ASTNode Statement() {
        Token token = this.lookAhead;
        return switch (token.getType()) {
            case TK_RETURN -> this.ReturnStatement();
            case TK_LBRACE -> this.BlockStatement();
            case TK_INT, TK_BOOL -> this.VariableStatement();
            case TK_IF -> this.IfStatement();
            case TK_WHILE -> this.WhileStatement();
            default -> this.ExpressionStatement();
        };
    }

    private ASTNode WhileStatement() {
        return null;
    }

    private ASTNode IfStatement() {
        return null;
    }

    private ASTNode ExpressionStatement() {
        ASTNode astNode = this.AssignmentExpression();
        this.eat(TokenType.TK_SEMICOLON);
        return astNode;
    }

    private ASTNode AssignmentExpression() {

        ASTNode left = this.AdditiveExpression();

        if (!this.isAssignmentOperator(this.lookAhead)) {
            return left;
        }

        String operator = this.AssignmentOperator().getValue();

        ASTNode right = this.AssignmentExpression();


        return new AssignmentExpression(left, operator, right);
    }


    private ASTNode PrimaryExpression() {
        return switch (this.lookAhead.getType()) {
            case TK_INTEGER_CONST, TK_BOOL_CONST_TRUE, TK_BOOL_CONST_FALSE -> this.Literal();
            case TK_LPAREN -> this.ParenthesizedExpression();
            default -> this.LeftHandExpression();
        };
    }

    private ASTNode LeftHandExpression() {
        return this.Identifier();
    }

    private ASTNode ParenthesizedExpression() {
        return null;
    }

    private boolean isAssignmentOperator(Token lookAhead) {
        return lookAhead.getType() == TokenType.TK_ASSIGN;
    }

    private ASTNode AdditiveExpression() {
        ASTNode left = this.PrimaryExpression();

        while (this.lookAhead.getType() == TokenType.TK_PLUS) {
            Token operator = this.eat(TokenType.TK_PLUS);
            ASTNode right = this.AdditiveExpression();
            left = new BinaryExpression(operator, left, right);
        }
        return left;
    }

    private ASTNode Literal() {
        return switch (this.lookAhead.getType()) {
            case TK_INTEGER_CONST -> new NumericLiteral(this.NumericLiteral());
            case TK_BOOL_CONST_TRUE -> new BooleanLiteral(this.BooleanLiteral(0));
            case TK_BOOL_CONST_FALSE -> new BooleanLiteral(this.BooleanLiteral(1));
            default -> throw new IllegalStateException("Unexpected value: " + this.lookAhead.getType());
        };
    }

    private Token NumericLiteral() {
        return this.eat(TokenType.TK_INTEGER_CONST);
    }

    private Token BooleanLiteral(int i) {
        if (i == 1)
            return this.eat(TokenType.TK_BOOL_CONST_FALSE);
        else
            return this.eat(TokenType.TK_BOOL_CONST_TRUE);
    }

    private Token AssignmentOperator() {
        return this.eat(TokenType.TK_ASSIGN);
    }

    private ASTNode BlockStatement() {
        this.eat(TokenType.TK_LBRACE);
        List<ASTNode> statementNodes = this.StatementList();
        this.eat(TokenType.TK_RBRACE);
        return new BlockStatement(statementNodes);
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
        Token token = null;
        switch (this.lookAhead.getType()) {
            case TK_INT -> token = this.eat(TokenType.TK_INT);
            case TK_BOOL -> token = this.eat(TokenType.TK_BOOL);
        }
        return new Type(token);
    }

    private ASTNode VariableStatement() {
        Type type = this.Type();
        List<VariableDecl> declarations = this.VariableDeclList();
        this.eat(TokenType.TK_SEMICOLON);
        return new VariableStatement(type, declarations);
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
        ASTNode init =
                this.lookAhead.getType() == TokenType.TK_COMMA || this.lookAhead.getType() == TokenType.TK_SEMICOLON
                        ? null
                        : this.VariableInitializer();
        return new VariableDecl(identifier, init);
    }

    private ASTNode VariableInitializer() {
        this.eat(TokenType.TK_ASSIGN);
        return this.AssignmentExpression();
    }

    private ASTNode ReturnStatement() {
        Token token = this.eat(TokenType.TK_RETURN);
        ASTNode right = this.AssignmentExpression();
        this.eat(TokenType.TK_SEMICOLON);
        return new ReturnStatement(token, right, currFuncName);
    }

    private Token eat(TokenType tokenType) {
        Token token = this.lookAhead;
        if (token.getType() != tokenType) {
            throw new UnexpectedTokenException(token);
        }
        this.lookAhead = this.lexer.getNextToken();
        return token;
    }


}

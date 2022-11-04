package parser.nodes;

import java.util.List;

public class VariableStatement {
    Type type;
    List<VariableDecl> declarations;

    public VariableStatement(Type type, List<VariableDecl> declarations) {
        this.type = type;
        this.declarations = declarations;
    }

    @Override
    public String toString() {
        return "VariableStatement{" +
                "type=" + type +
                ", declarations=" + declarations +
                '}';
    }
}

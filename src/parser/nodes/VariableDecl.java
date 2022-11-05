package parser.nodes;

import java.util.Map;

public class VariableDecl {
//    String id;
//    Token id;
    Identifier id;
    Map<String,Object> init;

    public VariableDecl(Identifier id, Map<String, Object> init) {
        this.id = id;
        this.init = init;
    }

    @Override
    public String toString() {
        return "VariableDecl{" +
                "id=" + id +
                ", init=" + init +
                '}';
    }
}

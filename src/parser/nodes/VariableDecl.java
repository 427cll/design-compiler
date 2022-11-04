package parser.nodes;

public class VariableDecl {
//    String id;
//    Token id;
    Identifier id;
    Integer init;

    public VariableDecl(Identifier id, Integer init) {
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

package parser.nodes;


public class FormalParam {
    Type type;
    Identifier var;

    public FormalParam(Type type, Identifier var) {
        this.type = type;
        this.var = var;
    }

    @Override
    public String toString() {
        return "FormalParam{" +
                "type=" + type +
                ", var=" + var +
                '}';
    }
}

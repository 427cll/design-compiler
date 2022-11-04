package parser.nodes;

import java.util.List;

public class BlockStatement {

    List<Object> body;

    public BlockStatement(List<Object> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "BlockStatement{" +
                "body=" + body +
                '}';
    }
}

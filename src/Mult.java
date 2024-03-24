import java.util.concurrent.*;

public class Mult extends BinOp {
	public Mult(Expression g, Expression d) { super("*", g, d); }

	public int eval() { return super.getLeft().eval()*super.getRight().eval(); }

	public <T> T accept(IVisitor<T> vts) { return vts.visit(this); }

	public <T> T accept(IVisitorThExc<T> vts) throws InterruptedException, ExecutionException { return vts.visit(this); }
}
import java.util.concurrent.*;

public class Add extends BinOp {
	public Add(Expression g, Expression d) { super("+", g, d); }

	@Override
	public String toString() { return "("+super.toString()+")"; }

	public int eval() { return super.getLeft().eval()+super.getRight().eval(); }

	public <T> T accept(IVisitor<T> vts) { return vts.visit(this); }

	public <T> T accept(IVisitorThExc<T> vts) throws InterruptedException, ExecutionException { return vts.visit(this); }
}
import java.util.concurrent.*;

public class Constant implements Expression {
	private int val;

	public Constant(int val) { this.val=val; }

	public int getValue() { return val; }

	@Override
	public String toString() { return ""+val; }

	@Override
	public boolean equals(Object o) { return (o instanceof Constant) && (((Constant)o).val==this.val); }

	public int eval() { return val; }

	public 	<T> T accept(IVisitor<T> vts) { return vts.visit(this); }

	public <T> T accept(IVisitorThExc<T> vts) throws InterruptedException, ExecutionException { return vts.visit(this); }
}
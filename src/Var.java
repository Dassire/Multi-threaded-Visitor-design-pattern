import java.util.concurrent.*;

public class Var implements Expression {
	private final String name;

	public Var(String name) { this.name=name; }

	public String getName() { return toString(); }

	@Override
	public String toString() { return new String(name); }

	@Override
	public boolean equals(Object o) { return (o instanceof Var) && name.equals(((Var)o).name); }

	public int eval() { throw new UnsupportedOperationException(); }

	public <T> T accept(IVisitor<T> vts) { return vts.visit(this); }

	public <T> T accept(IVisitorThExc<T> vts) throws InterruptedException, ExecutionException { return vts.visit(this); }
}
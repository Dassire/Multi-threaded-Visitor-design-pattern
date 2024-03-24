import java.util.concurrent.*;

class ThVisitor<T> implements Callable<T> {
	private IVisitorThExc<T> v;
	private Expression e;

	ThVisitor(Expression expression, IVisitorThExc<T> visitor) {
		this.v=visitor;
		this.e=expression;
	}

	@Override
	public T call() throws InterruptedException, ExecutionException {
		return e.accept(v);
	}
}
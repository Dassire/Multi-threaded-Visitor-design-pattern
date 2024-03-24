import java.util.concurrent.*;

public interface Expression {
	// :)
	public int eval();

	public <T> T accept(IVisitor<T> vts);
	public <T> T accept(IVisitorThExc<T> vts) throws InterruptedException, ExecutionException;
}
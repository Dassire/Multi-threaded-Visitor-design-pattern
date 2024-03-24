import java.util.concurrent.*;

public class VisitorEval implements IVisitorThExc<Integer> {
	private ExecutorService exec;

	VisitorEval(ExecutorService exec) {
		this.exec=exec;
	}

	public Integer visit(Constant c) { return Integer.valueOf(c.getValue()); }
	public Integer visit(Var x) { throw new UnsupportedOperationException(); }

	public Integer visit(Add e) throws InterruptedException, ExecutionException {
		Future<Integer> tl=exec.submit(new ThVisitor<Integer>(e.getLeft(), this));
		Future<Integer> tr=exec.submit(new ThVisitor<Integer>(e.getRight(), this));
		return Integer.valueOf(tl.get().intValue()+tr.get().intValue());
	}
	public Integer visit(Mult e) throws InterruptedException, ExecutionException {
		Future<Integer> tl=exec.submit(new ThVisitor<Integer>(e.getLeft(), this));
		Future<Integer> tr=exec.submit(new ThVisitor<Integer>(e.getRight(), this));
		return Integer.valueOf(tl.get().intValue()*tr.get().intValue());
	}
}
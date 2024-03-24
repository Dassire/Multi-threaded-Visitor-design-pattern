import java.util.concurrent.*;

public class VisitorToString implements IVisitor<String> {
	private ExecutorService exec;

	VisitorToString(ExecutorService exec) {
		this.exec=exec;
	}

	VisitorToString() { this(Executors.newCachedThreadPool()); }

	public String visit(Constant c) {
		return Integer.toString(c.getValue());
	}

	public String visit(Var x) {
		return x.getName();
	}

	public String visit(Add e) {
		Future<String> tl=exec.submit(new ThVisitor<String>(e.getLeft(), this));
		Future<String> tr=exec.submit(new ThVisitor<String>(e.getRight(), this));
		try {
			return "("+tl.get()+" + "+tr.get()+")";
		} catch(Exception error) {
			return e.toString();
		}
	}

	public String visit(Mult e) {
		Future<String> tl=exec.submit(new ThVisitor<String>(e.getLeft(), this));
		Future<String> tr=exec.submit(new ThVisitor<String>(e.getRight(), this));
		try {
			return tl.get()+" * "+tr.get();
		} catch(Exception error) {
			return e.toString();
		}
	}
}
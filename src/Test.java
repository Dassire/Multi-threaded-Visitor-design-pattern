import java.util.Map;
import java.util.Hashtable;
import java.util.concurrent.*;

class Test {
	static Map<String,Integer> env2() {
		Map<String,Integer> r=new Hashtable<String,Integer>();
		r.put("x", Integer.valueOf(10));
		r.put("y", Integer.valueOf(20));
		return r;
	}

	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		Expression ea=new Mult(new Add(new Constant(5), new Var("x")), new Add(new Constant(2), new Constant(3)));
		System.out.println(ExpressionStatic.toString(ea));
		try {
			try {
				System.out.println(ea+" = "+ea.eval());
			} catch(UnsupportedOperationException e) {
				System.err.println(e);
				System.out.println(ea.accept(new VisitorToString(exec))+" = "+ea.accept(new VisitorSimplify())+" = "+ea.accept(new VisitorEvalVar(env2(),exec)));
			}
			System.out.println(new Mult(new Add(new Constant(5), new Constant(7)), new Add(new Constant(2), new Constant(3))).accept(new VisitorEval(exec)));
		} catch(ExecutionException e) {
			System.err.printf("Execution :\n%s\n", e.getCause());
		} catch(InterruptedException e) {
			System.err.printf("Oups :\n%s\n", e);
		} finally {
			exec.shutdown();
		}
	}
}
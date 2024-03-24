import java.util.concurrent.*;

public class ExpressionStatic {
	public static boolean isConstant(Expression e) {
		ExecutorService exec=Executors.newCachedThreadPool();
		try {
			e.accept(new VisitorEval(exec));
			return true;
		} catch(UnsupportedOperationException w) {
			return false;
		} finally {
			exec.shutdown();
		}
	}

	public static String toString(Expression e) {
		ExecutorService exec=Executors.newCachedThreadPool();
		String r=e.accept(new VisitorToString(exec));
		exec.shutdown();
		return r;
	}

	public static int evalConstantExpression(Expression e) {
		return e.accept(new VisitorEval(Executors.newCachedThreadPool())).intValue();
	}
}
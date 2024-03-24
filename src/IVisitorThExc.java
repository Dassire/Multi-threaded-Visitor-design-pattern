import java.util.concurrent.*;

interface IVisitorThExc<T> {
	T visit(Constant c);
	T visit(Var x);
	T visit(Add e) throws InterruptedException, ExecutionException;
	T visit(Mult e) throws InterruptedException, ExecutionException;
}
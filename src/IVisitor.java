public interface IVisitor<T> extends IVisitorThExc<T> {
	T visit(Add e);
	T visit(Mult e);
}
public abstract class BinOp implements Expression {
	private final Expression gauche, droite;
	private String op;

	public BinOp(String op, Expression g, Expression d) {
		this.op=new String(op);
		gauche=g;
		droite=d;
	}

	protected Expression getLeft() { return gauche; }
	protected Expression getRight() { return droite; }

	@Override
	public String toString() { return gauche+" "+op+" "+droite; }

	@Override
	public boolean equals(Object o) { 
		return (o instanceof BinOp) 
			&& op.equals(((BinOp)o).op) 
			&& gauche.equals(((BinOp)o).gauche) 
			&& droite.equals(((BinOp)o).droite);
	}
}
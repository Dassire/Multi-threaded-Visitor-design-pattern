public class VisitorSimplify implements IVisitor<Expression> {
	public Expression visit(Constant c) { return new Constant(c.getValue()); }
	public Expression visit(Var x) { return new Var(x.getName()); }
	
	public Expression visit(Add e) {
		Expression g=e.getLeft().accept(this);
		Expression d=e.getRight().accept(this);

		if((g instanceof Constant) && (((Constant)g).getValue()==0)) {
			return d;
		}

		if((d instanceof Constant) && (((Constant)d).getValue()==0)) {
			return g;
		}

		if((g instanceof Constant) && (d instanceof Constant)) {
			return new Constant(((Constant)g).getValue()+((Constant)d).getValue());
		} else {
			return new Add(g,d);
		}
	}

	public Expression visit(Mult e) {
		Expression g=e.getLeft().accept(this);
		Expression d=e.getRight().accept(this);

		if(g instanceof Constant) {
			int vG=((Constant)g).getValue();

			if(vG==0) {
				return new Constant(0);
			}
			if(vG==1) {
				return d;
			}
			if(d instanceof Constant) {
				int vD=((Constant)d).getValue();

				if(vD==0) {
					return new Constant(0);
				}
				if(vD==1) {
					return g;
				}
				return new Constant(vG*vD);
			}
			return new Mult(g,d);
		} else {
			if(d instanceof Constant) {
				int vD=((Constant)d).getValue();

				if(vD==0) {
					return new Constant(0);
				}
				if(vD==1) {
					return g;
				}
				return new Mult(d,g);
			}
			return new Mult(g,d);
		}
	}
}
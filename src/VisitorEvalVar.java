import java.util.Map;
import java.util.Hashtable;
import java.util.concurrent.*;

public class VisitorEvalVar extends VisitorEval {
	private Map<String,Integer> env;

	public VisitorEvalVar(Map<String,Integer> env, ExecutorService exec) {
		super(exec);
		this.env=env;
	}

	public VisitorEvalVar(ExecutorService exec) { this(new Hashtable<String,Integer>(1), exec); }

	public Integer visit(Var x) {
		Integer r=env.get(x.getName());
		if(r==null) {
			throw new UnsupportedOperationException(x+" n'as pas de valeur");
		}
		return r;
	}
}
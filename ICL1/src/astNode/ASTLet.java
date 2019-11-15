package astNode;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import compiler.Code;
import iValue.IValue;

/**
 * Class to declare vars
 * @author Miguel Araujo 45699
 *
 */
public class ASTLet implements ASTNode{

	private Map<ASTNode, ASTNode> nodeVals;
	private ASTNode exp;
	
	public ASTLet( Map<ASTNode, ASTNode> nodeVals, ASTNode exp) {
		this.nodeVals = nodeVals;
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment<IValue> env) {
		env = env.beginScope();
		
		Set<ASTNode> keys = nodeVals.keySet();
		Iterator<ASTNode> it = keys.iterator();
		while ( it.hasNext() ) {
			ASTNode key = it.next();
			try {
				env.assoc( (ASTId) key , nodeVals.get(key).eval(env));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IValue v = exp.eval(env);
		env = env.endScope();
		return v;
	}

	@Override
	public void compile(Code code) {
		// TODO Auto-generated method stub
		
	}

}

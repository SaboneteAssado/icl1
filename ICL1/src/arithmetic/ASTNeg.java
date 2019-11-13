package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import iValue.IValue;
import iValue.VInt;

/**
 * Class to evaluate the symmetric value. 
 * @author Miguel Araujo 45699
 *
 */
public class ASTNeg implements ASTNode {

	private ASTNode num;
	private int nMinus;

	public ASTNeg(ASTNode num, int nMinus) {
		this.nMinus = nMinus;
		this.num = num;
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = num.eval(env);
		return new VInt( -((VInt)v).getVal() );
	}
}

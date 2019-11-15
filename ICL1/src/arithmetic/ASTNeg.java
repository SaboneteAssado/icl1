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

	public ASTNeg(ASTNode num) {
		this.num = num;
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = num.eval(env);
<<<<<<< HEAD
		return new VInt( -((VInt) v).getVal() );
=======
		return new VInt( -((VInt)v).getVal() );
>>>>>>> branch 'master' of https://github.com/SaboneteAssado/icl1
	}
}

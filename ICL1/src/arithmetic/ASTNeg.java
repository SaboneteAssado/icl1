package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import compiler.Code;
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
		return new VInt(-((VInt)v).getVal());
	}
	
	public void compile (Code code) {
		num.compile(code);
		code.emit("ineg");
	}
}

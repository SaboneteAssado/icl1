package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import compiler.Code;
import iValue.IValue;
import iValue.VInt;

/**
 * Class that evaluates muls.
 * @author Miguel Araujo
 *
 */
public class ASTMul implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTMul(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates each expression and then muls.
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		VInt v1 = (VInt) expr1.eval(env);
		VInt v2 = (VInt) expr2.eval(env);
		return v1.multiply(v2);
	}
	
	public void compile (Code code) {
		expr1.compile(code);
		expr2.compile(code);
		code.emit("imul");
	}
}

package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import compiler.Code;
import iValue.IValue;
import iValue.VInt;

/**
 * Class to evaluate divisions.
 * @author Miguel Araujo 45699
 *
 */
public class ASTDiv implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTDiv(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates each expression and divides.
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		VInt v1 = (VInt) expr1.eval(env);
		VInt v2 = (VInt) expr2.eval(env);
		if (v2.isZero()) {
			throw new ArithmeticException("Division by zero");
		}
		return new VInt(v1.getVal() / v2.getVal());

	}
	
	public void compile (Code code) {
		expr1.compile(code);
		expr2.compile(code);
		code.emit("idiv");
	}
}

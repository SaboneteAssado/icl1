package arithmetic;

import astNode.ASTNode;
import astNode.Environment;
import compiler.Code;
import iValue.IValue;
import iValue.VInt;

/**
 * Class that evaluates additions.
 * @author Miguel Araujo 45699
 *
 */
public class ASTPlus implements ASTNode {

	private ASTNode expr1, expr2;

	public ASTPlus(ASTNode expr1, ASTNode expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	/**
	 * Evaluates each expression and adds.
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		VInt v1 = (VInt) expr1.eval(env);
		VInt v2 = (VInt) expr2.eval(env);
		return v1.add(v2);
	}
	
	public void compile (Code code) {
		expr1.compile(code);
		expr2.compile(code);
		code.emit("iadd");
	}
}

package astNode;

import environment.Environment;
import iValue.IValue;

/**
 * Interface for abstract syntax tree.
 * @author Miguel Araujo 45699
 *
 */
public interface ASTNode {

	/**
	 * Evaluates the value of an expression.
	 * @param env - Environment of IValues.
	 * @return the value of the expression.
	 */
	IValue eval(Environment<IValue> env);
}
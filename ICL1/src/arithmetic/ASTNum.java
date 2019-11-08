package arithmetic;
import astNode.ASTNode;
import astNode.Environment;
import iValue.IValue;
import iValue.VInt;

public class ASTNum implements ASTNode{
	private int num;

	public ASTNum(int num) {
		this.num = num;
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue result = new VInt(num);
		return result;
	}
}

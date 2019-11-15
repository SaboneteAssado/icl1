package arithmetic;
import astNode.ASTNode;
import compiler.Code;
import compiler.Environment;
import iValue.IValue;
import iValue.VInt;

public class ASTNum implements ASTNode{
	
	private VInt num;

	public ASTNum(int num) {
		this.num = new VInt(num);
	}

	/**
	 * Evaluates the value and symmetric
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		return num;
	}
	
	public void compile (Code code) {
		code.emit("sipush " + num.getVal() );
	}
}

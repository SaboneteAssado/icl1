package astNode;

import compiler.Code;
import iValue.IValue;

/**
 * Class that represents vars
 * @author Miguel Araujo 45699
 *
 */
public class ASTId implements ASTNode{

	private String id;
	
	public ASTId ( String id ) {
		this.id = id;
	}
	
	/**
	 * gets the id from env
	 * @param env
	 */
	@Override
	public IValue eval(Environment<IValue> env) {
		IValue v = env.findId(id);
		return v;
	}

	/**
	 * Gets the id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets id to id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void compile(Code code) {
		// TODO Auto-generated method stub
		
	}
	
}

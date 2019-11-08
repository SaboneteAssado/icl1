package astNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.NodeSetData;

import iValue.IValue;

/**
 * Environment class.
 * @author Miguel Araujo 45699 
 *
 */
public class Environment<E> {

	/**
	 * An environment needs to store, besides its parent environment, the list of associations
	 * between identifiers and values. 
	 */
	private Environment<E> prev;

	private Map<String, E> nodeVals;

	public Environment(Environment<E> prev) {
		this.prev = prev;
		nodeVals = new HashMap<String, E>();
	}

	/**
	 * Begins a new scope of environment that is the child of the current environment.
	 * @return the child of the current environment.
	 */
	public Environment<E> beginScope() {
		return new Environment<E>(this);
	}

	/**
	 * Ends this scope of environment by going to its parent environment.
	 * @return the parent environment.
	 */
	public Environment<E> endScope() {
		return prev;
	}

	/**
	 * Associates a identifier with a value.
	 * @param id
	 * @param val
	 * @throws Exception 
	 */
	public void assoc(String id, E val) throws Exception {
		E envVal = findId(id);
		if ( envVal == null || ( envVal instanceof IValue && val instanceof IValue) )
			nodeVals.put(id, val);
		else {
			throw new Exception("ID Already Assigned");
		}
	}

	/**
	 * Searches for an identifier in an environment.
	 * @param id.
	 * @return val.
	 */
	private E findId(String id){
		E val = nodeVals.get(id);
		if ( val != null)
			return val;
		else if ( prev != null )
			return prev.findId(id);
		else return val;
	}
}

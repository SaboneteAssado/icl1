package environment;

import java.util.ArrayList;
import java.util.List;

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

	private 

	public Environment(Environment<E> prev) {
		this.prev = prev;
		ids = new ArrayList<ASTId>();
		values = new ArrayList<E>();
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
	 * @param id identifier.
	 * @param value
	 * @throws IdDeclaredException is thrown if the identifier is already declared.
	 */
	public void assoc(String id, E value) throws IdDeclaredException {
		E resultInEnv = searchInEnv(id);
		E result = null;
		if (resultInEnv != null) {
			if(resultInEnv instanceof IValueRef && value instanceof IValue) {
				((IValueRef)resultInEnv).setValue((IValue)value);
			}
		} else {
			try {
				result = find(id);
			} catch (DontFindException e) {
				ids.add(new ASTId(id));
				values.add(value);
			}
			if (result != null && resultInEnv == null) {
				ids.add(new ASTId(id));
				values.add(value);
			} else if (result != null && resultInEnv != null) {
				throw new IdDeclaredException("Id is Already Declared");
			}
		}
	}

	/**
	 * Searches for an id in the current environment and its parent environments.
	 * @param name of identifier.
	 * @return the value associated with the name.
	 * @throws DontFindException is thrown if no value for the identifier is found.
	 */
	public E find(String name) throws DontFindException {
		E result = searchInEnv(name);
		if (result != null)
			return result;
		else if (prev != null)
			return prev.find(name);
		throw new DontFindException("Can't Find the Element");
	}

	/**
	 * Searches for an identifier in an environment.
	 * @param name of identifier.
	 * @return the value.
	 */
	private E searchInEnv(String name) {
		E val = null;
		for (int i = 0; i < this.ids.size(); i++) {
			if(this.ids.get(i).getId().equals(name)) {
				val = values.get(i);
			}
		}
		return val;
	}
}

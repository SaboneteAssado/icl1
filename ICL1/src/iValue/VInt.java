package iValue;

/**
 * Integer value.
 * @author Miguel Araujo 45699
 *
 */
public class VInt implements IValue {

	private int num;
	
	public VInt(int num) {
		this.num = num;
	}
	
	public int getVal() {
		return num;
	}

	/**
	 * Returns a new integer value with the sum of this value and another.
	 * @param other another integer value
	 * @return new summed value
	 */
	public VInt add(VInt other) {
		return new VInt(this.num + other.num);
	}

	/**
	 * Returns a new integer value with the subtraction of another from this value.
	 * @param other another integer value
	 * @return new subtracted value
	 */
	public VInt subtract(VInt other) {
		return new VInt(this.num - other.num);
	}

	/**
	 * Returns a new integer value with the multiplication of this value and another.
	 * @param other another integer value
	 * @return new multiplied value
	 */
	public VInt multiply(VInt other) {
		return new VInt(this.num * other.num);
	}

	/**
	 * Indicates whether the value is zero.
	 * @return true when the value is zero
	 */
	public boolean isZero() {
		return this.num == 0;
	}

	@Override
	public void show() {
		System.out.print(num);
	}
}

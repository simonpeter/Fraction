/*
 * Fraction.java
 */

package org.simonpeter.examples.fraction;

/**
 * A classic mathematical fraction.
 * 
 * @author Simon Peter Chappell
 * @version 20100728
 */
public class Fraction {

	final private int _numerator;
	final private int _denominator;

	public Fraction(int n, int d) {
		if (d == 0) { throw new IllegalArgumentException(); }
		_numerator = n;
		_denominator = d;
	}

	public int getNumerator() {
		return _numerator;
	}

	public int getDenominator() {
		return _denominator;
	}

	/**
	 * Indicates whether another {@link Fraction} is "equal to" this one.
	 * <p>
	 * A good description of the requirements for object equality is given in
	 * the Java API document entry for {link Object}. An exhaustive explanation
	 * of the qualities of a good {@code equals()} method is given in
	 * <cite>Effective Java</cite> by Joshua Bloch.
	 * 
	 * @param obj - the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
	 * @see java.util.Object#equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) { return true; }
		if (!(obj instanceof Fraction)) { return false; }
		Fraction that = (Fraction) obj;
		return (this._numerator   == that.getNumerator()
		     && this._denominator == that.getDenominator());
	}

	/**
	 * Returns a hash code value for the {@link Fraction}.
	 * <p>
	 * This method is supported for the benefit of hashtables, such as those
	 * provided by {@link java.util.Hashtable}.
	 * <p>
	 * A good description of the requirements for object hashcodes is given in
	 * the Java API document entry for {link Object}. An exhaustive explanation
	 * of the qualities of a good {@code hashCode()} method is given in
	 * <cite>Effective Java</cite> by Joshua Bloch.
	 * 
	 * @return a hash code value for this object.
	 * @see java.util.Object#hashCode
	 * @see java.util.Hashtable
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + _numerator;
		result = 37 * result + _denominator;
		return result;
	}

	public Fraction reduce() {
		int u = _numerator;
		int v = _denominator;
		int temp;

		u = Math.abs(u);
		while (v != 0) {
			temp = u % v;
			u = v;
			v = temp;
		}
		return new Fraction(_numerator / u, _denominator / u);
	}

	public boolean equivalent(Fraction that) {
		return this.reduce().equals(that.reduce());
	}

	public Fraction add(Fraction f) {
		// a/b + c/d = ((a*d) + (b*c)) / (b*d)
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = ((a * d) + (b * c));
		int den = (b * d);

		return new Fraction(num, den).reduce();
	}

	public Fraction sub(Fraction f) {
		// a/b - c/d = ((a*d) - (b*c)) / (b*d)
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = ((a * d) - (b * c));
		int den = (b * d);

		return new Fraction(num, den).reduce();
	}

	public Fraction mul(Fraction f) {
		// a/b * c/d = (a*c) / (b*d)
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = a * c;
		int den = b * d;

		return new Fraction(num, den).reduce();
	}

	public Fraction div(Fraction f) {
		// a/b / c/d = (a*d) / (b*c)
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = a * d;
		int den = b * c;

		return new Fraction(num, den).reduce();
	}
}

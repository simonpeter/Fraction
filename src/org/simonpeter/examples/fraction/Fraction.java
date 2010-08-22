/*
 * Fraction.java
 */

package org.simonpeter.examples.fraction;

/**
 * Represents a classic mathematical fraction.
 * <p>
 * This object was expressly created using Test Driven Design. It is fully
 * tested and both it and its tests are documented to a greater extent
 * than normal to allow the student of JUnit and unit testing an opportunity
 * to review how and why things were done they way they were.
 * <p>
 * Because fractions do not change their values, this object is implemented
 * in immutable fashion.
 * <p>
 * More information available on the Wikipedia
 * <a href="http://en.wikipedia.org/wiki/Fraction_(mathematics)">
 * Fraction</a> page.
 * 
 * @author Simon Peter Chappell
 * @version 20100822
 */
public class Fraction {

	final private int _numerator;
	final private int _denominator;


	/**
	 * Construct a {@link Fraction}.
	 * <p>
	 * This is the public constructor. The fraction does apply validation
	 * to the denominator and will reject any zero values. The type system
	 * of Java does the rest of the work and will ensure that the numerator
	 * and denominator are integers as we have declared them of type 'int'.
	 * 
	 * @param n - the numerator
	 * @param d - the denominator
	 * 
	 * @throws java.lang.IllegalArgumentException
	 */
	public Fraction(int n, int d) {
		if (d == 0) { throw new IllegalArgumentException(); }
		_numerator = n;
		_denominator = d;
	}

	/**
	 * Return the numerator of the fraction.
	 * <p>
	 * This is the value on the top of the fraction.
	 * 
	 * @return the numerator of the fraction.
	 */
	public int getNumerator() {
		return _numerator;
	}

	/**
	 * Return the denominator of the fraction.
	 * <p>
	 * This is the value on the bottom of the fraction.
	 * 
	 * @return the denominator of the fraction.
	 */
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


	/**
	 * Reduces a fraction to it's smallest numerically equivalent form.
	 * 
	 * @return a fraction that represents the reduced form of the original fraction.
	 */
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

	/**
	 * Two fractions are equivalent if their reduced forms are equal.
	 * 
	 * For example, 1/2 and 2/4 are equivalent. They both represent 50% of
	 * a value. They are stated differently, so while they are not equal
	 * by strict comparison, they are equal numerically, so I have created
	 * this method to test that equivalence.
	 * 
	 * @param that - the fraction to be compared against.
	 * @return a boolean true if the two fractions are equivalent, otherwise false.
	 */
	public boolean equivalent(Fraction that) {
		return this.reduce().equals(that.reduce());
	}

	/**
	 * Add a fraction to the current value.
	 * <p>
	 * This uses the classic equation:
	 * <blockquote>
	 * {@code a/b + c/d = ((a*d) + (b*c)) / (b*d)}
	 * </blockquote>
	 * 
	 * @param f - the fraction to add
	 * @return a fraction representing the sum of the two fractions.
	 */
	public Fraction add(Fraction f) {
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = ((a * d) + (b * c));
		int den = (b * d);

		return new Fraction(num, den).reduce();
	}

	/**
	 * Subtract a fraction from the current value.
	 * <p>
	 * This uses the classic equation:
	 * <blockquote>
	 * {@code a/b - c/d = ((a*d) - (b*c)) / (b*d)}
	 * </blockquote>
	 * 
	 * @param f - the fraction to subtract
	 * @return a fraction representing the difference of the two fractions.
	 */
	public Fraction sub(Fraction f) {
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = ((a * d) - (b * c));
		int den = (b * d);

		return new Fraction(num, den).reduce();
	}

	/**
	 * Multiply a fraction by the current value.
	 * <p>
	 * This uses the classic equation:
	 * <blockquote>
	 * {@code a/b * c/d = (a*c) / (b*d)}
	 * </blockquote>
	 * 
	 * @param f - the fraction to multiply by
	 * @return a fraction representing the product of the two fractions.
	 */
	public Fraction mul(Fraction f) {
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = a * c;
		int den = b * d;

		return new Fraction(num, den).reduce();
	}

	/**
	 * Divide the current value by a fraction.
	 * <p>
	 * This uses the classic equation:
	 * <blockquote>
	 * {@code a/b / c/d = (a*d) / (b*c)}
	 * </blockquote>
	 * 
	 * @param f - the fraction to divide by
	 * @return a fraction representing the quotient of the two fractions.
	 */
	public Fraction div(Fraction f) {
		int a = _numerator;
		int b = _denominator;
		int c = f.getNumerator();
		int d = f.getDenominator();
		int num = a * d;
		int den = b * c;

		return new Fraction(num, den).reduce();
	}
}

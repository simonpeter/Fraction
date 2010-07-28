/*
 * Fraction.java
 */

package org.simonpeter.examples.fraction;
import java.lang.IllegalArgumentException;

/**
 * @author  Simon P. Chappell
 * @version $Id: Fraction.java 11 2008-07-18 21:21:13Z Simon $
 */
public class Fraction {

    final private int _numerator;
    final private int _denominator;

    public Fraction(int n, int d) {
        if (d == 0) {
            throw new IllegalArgumentException();
        }
        _numerator = n;
        _denominator = d;
    }

    public int getNumerator() {
        return _numerator;
    }

    public int getDenominator() {
        return _denominator;
    }

    public boolean equals(Fraction f) {
        boolean retVal = false;
        
        //
        // Is the object not null?
        //
        if (f != null) {
            //
            // Are the objects of the same type?
            //
            if (f instanceof Fraction) {
                //
                // Are the instance variables identical?
                //
                if (_numerator == f.getNumerator() &&
                    _denominator == f.getDenominator()) {
                        retVal = true;
                }
            }
        }
        return (retVal);
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
        
        return new Fraction(_numerator/u, _denominator/u);
    }

    public boolean equivalent(Fraction f) {
        boolean retVal = false;
        
        //
        // Is the object not null?
        //
        if (f != null) {
            //
            // Are the objects of the same type?
            //
            if (f instanceof Fraction) {
                //
                // Are the reduced fractions identical?
                //
                Fraction f1 = this.reduce();
                Fraction f2 = f.reduce();
                if (f1.equals(f2)) {
                    retVal = true;
                }
            }
        }
        return (retVal);
    }

    public Fraction add(Fraction f) {
        // a/b + c/d = ((a*d) + (b*c)) / (b*d)
        int a = _numerator;
        int b = _denominator;
        int c = f.getNumerator();
        int d = f.getDenominator();
        int num = ((a*d) + (b*c));
        int den = (b*d);

        return new Fraction(num, den).reduce();
    }

    public Fraction sub(Fraction f) {
        // a/b - c/d = ((a*d) - (b*c)) / (b*d)
        int a = _numerator;
        int b = _denominator;
        int c = f.getNumerator();
        int d = f.getDenominator();
        int num = ((a*d) - (b*c));
        int den = (b*d);

        return new Fraction(num, den).reduce();
    }

    public Fraction mul(Fraction f) {
        // a/b * c/d = (a*c) / (b*d)
        int a = _numerator;
        int b = _denominator;
        int c = f.getNumerator();
        int d = f.getDenominator();
        int num = a*c;
        int den = b*d;

        return new Fraction(num, den).reduce();
    }

    public Fraction div(Fraction f) {
        // a/b / c/d = (a*d) / (b*c)
        int a = _numerator;
        int b = _denominator;
        int c = f.getNumerator();
        int d = f.getDenominator();
        int num = a*d;
        int den = b*c;

        return new Fraction(num, den).reduce();
    }
}

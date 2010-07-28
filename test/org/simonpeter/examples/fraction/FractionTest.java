/*
 * FractionTest.java
 */

package org.simonpeter.examples.fraction;
import junit.framework.TestCase;
import java.lang.IllegalArgumentException;

/**
 * @author  Simon P. Chappell
 * @version $Id: FractionTest.java 11 2008-07-18 21:21:13Z Simon $
 */
public class FractionTest extends TestCase {

    public void testConstructor() {
        //
        // Known good value.
        //
        Fraction f1 = new Fraction(1, 2);
        
        //
        // Known bad value.
        //
        try {
            Fraction f2 = new Fraction(1, 0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            assertTrue(true);
        }
    }
    
    
    public void testNumeratorAccessor() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f2.getNumerator());
    }
    
    
    public void testDenominatorAccessor() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 3);
        assertEquals(2, f1.getDenominator());
        assertEquals(3, f2.getDenominator());
    }

    public void testEquals() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 2);
        assertTrue(f1.equals(f2));
        
        //
        // Known bad values.
        //
        Fraction f3 = new Fraction(1, 3);
        assertFalse(f1.equals(f3));
        assertFalse(f1.equals(null));
    }

    public void testReduce() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = f2.reduce();
        assertTrue(f1.equals(f3));

        Fraction f4 = new Fraction(3, 5);
        Fraction f5 = new Fraction(9, 15);
        Fraction f6 = f5.reduce();
        assertTrue(f4.equals(f6));
    }
    
    public void testEquivalent() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        assertTrue(f1.equivalent(f2));

        Fraction f3 = new Fraction(1, 3);
        Fraction f4 = new Fraction(2, 7);
        assertFalse(f3.equivalent(f4));
    }

    public void testAddition() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(1, 5);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(3, 5);
        Fraction f4 = f1.add(f2);
        assertTrue(f4.equals(f3));

        Fraction f5 = new Fraction(1, 3);
        Fraction f6 = new Fraction(2, 5);
        Fraction f7 = new Fraction(11, 15);
        Fraction f8 = f5.add(f6);
        assertTrue(f8.equals(f7));

        //
        // Known bad values.
        //
        f1 = new Fraction(2, 5);
        f2 = new Fraction(2, 5);
        f3 = new Fraction(3, 5);
        f4 = f1.add(f2);
        assertFalse(f4.equals(f3));
    }

    public void testSubtraction() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(3, 5);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(1, 5);
        Fraction f4 = f1.sub(f2);
        assertTrue(f4.equals(f3));

        Fraction f5 = new Fraction(2, 3);
        Fraction f6 = new Fraction(1, 5);
        Fraction f7 = new Fraction(7, 15);
        Fraction f8 = f5.sub(f6);
        assertTrue(f8.equals(f7));

        //
        // Known bad values.
        //
        f1 = new Fraction(3, 5);
        f2 = new Fraction(1, 5);
        f3 = new Fraction(3, 5);
        f4 = f1.add(f2);
        assertFalse(f4.equals(f3));   
    }
    
    public void testMultiplication() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(3, 5);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(6, 25); // expected answer
        Fraction f4 = f1.mul(f2);
        assertTrue(f4.equals(f3));

        Fraction f5 = new Fraction(1, 2);
        Fraction f6 = new Fraction(1, 2);
        Fraction f7 = new Fraction(1, 4); // expected answer
        Fraction f8 = f5.mul(f6);
        assertTrue(f8.equals(f7));
    }

    public void testDivision() {
        //
        // Known good values.
        //
        Fraction f1 = new Fraction(3, 5);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(3, 2); // expected answer
        Fraction f4 = f1.div(f2);
        assertTrue(f4.equals(f3));

        Fraction f5 = new Fraction(1, 2);
        Fraction f6 = new Fraction(1, 2);
        Fraction f7 = new Fraction(1, 1); // expected answer
        Fraction f8 = f5.div(f6);
        assertTrue(f8.equals(f7));
    }
}

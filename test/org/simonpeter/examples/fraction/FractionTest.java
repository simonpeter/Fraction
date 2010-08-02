/*
 * FractionTest.java
 */

package org.simonpeter.examples.fraction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * JUnit tests for {@link Fraction}.
 *
 * @author  Simon Peter Chappell
 * @version 20100731
 * @see     org.simonpeter.examples.fraction.Fraction
 */
public class FractionTest {

	Fraction f1, f2, f3, f4;
	Fraction oneFifth, twoFifths, threeFifths;
	Fraction oneHalf, twoFourths, threeSixths;

    /**
     * This method runs before each test and allows us to
     * create a couple of standard objects for the test.
     */
	@Before public void before() {
		f1 = new Fraction(1,2);
		f2 = new Fraction(1,2);
		f3 = new Fraction(1,2);
		f4 = new Fraction(3,7);
		oneFifth = new Fraction(1,5);
		twoFifths = new Fraction(2,5);
		threeFifths = new Fraction(3,5);
		oneHalf = new Fraction(1,2);
		twoFourths = new Fraction(2,4);
		threeSixths = new Fraction(3,6);
	}

    /**
     * This method runs after each test and allows us to
     * clear out the test environment and dispose of our
     * standard test objects.
     */
	@After public void after() {
		f1 = null;
		f2 = null;
		f3 = null;
		f4 = null;
		oneFifth = null;
		twoFifths = null;
		threeFifths = null;
		oneHalf = null;
		twoFourths = null;
		threeSixths = null;
	}

	/**
	 * Ensure that we can build a few fractions, using
	 * known valid values.
	 */
	@Test public void testConstructorWithGoodValues() {
		new Fraction(1, 3);
		new Fraction(3, 2);
		new Fraction(1, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithZeroDenominator() {
		new Fraction(1, 0);
	}

	@Test public void testAccessors() {
		assertEquals(1, f1.getNumerator());
		assertEquals(2, f1.getDenominator());
	}

	@Test public void testReduce() {
		assertEquals(oneHalf, twoFourths.reduce());
		assertEquals(oneHalf, threeSixths.reduce());
	}

	@Test public void testEquivalent() {
		assertTrue(oneHalf.equivalent(oneHalf));
		assertTrue(oneHalf.equivalent(twoFourths));
		assertFalse(oneHalf.equivalent(threeFifths));
	}

    /**
     * Test that the {@code equals()} method handles nulls correctly.
     * <p>
     * An object is never equal to null.
     */
    @Test public void testNullBehaviorOfEquals() {
    	assertFalse(f1.equals(null));
    }

    /**
     * Test that the {@code equals()} method is reflexive.
     * <p>
     * An object is always equal to itself.
     */
    @Test public void testReflexiveBehaviorOfEquals() {
    	assertEquals(f1, f1);
    	assertTrue(f1.equals(f1));
    }

    /**
     * Test that the {@code equals()} method handles unequal values correctly.
     * <p>
     * If o1 does not equal o2, then o2 must not equal o1.
     */
    @Test public void testUnequalValuesOfEqual() {
    	assertFalse(f1.equals(f4));
    	assertFalse(f4.equals(f1));
    }

    /**
     * Test that the {@code equals()} method is symmetrical.
     * <p>
     * If o1 is equal to o2, then o2 must be equal to o1.
     */
    @Test public void testSymmetricalBehaviorOfEquals() {
    	assertTrue(f1.equals(f2));
    	assertTrue(f2.equals(f1));
    }

    /**
     * Test that the {@code equals()} method is transitive.
     * <p>
     * If o1 is equal to o2 and o2 is equal to o3, then o3 must be equal to o1.
     */
    @Test public void testTransitiveBehaviorOfEquals() {
    	assertTrue(f1.equals(f2));
    	assertTrue(f2.equals(f3));
    	assertTrue(f3.equals(f1));
    }

    /**
     * Test that hashcodes are equal for equal objects.
     * <p>
     * Objects that are equal, must have equal hashcodes.
     * Interestingly, there is no requirement for unequal
     * objects to have different hashcodes.
     * <p>
     * A classic cheat for hashcodes is to always return
     * an integer literal, such as 1. This is legal, but
     * causes very inefficient hashing behavior in most
     * collection objects.
     */
    @Test public void testEqualObjectsHaveEqualHashCodes() {
    	assertEquals(f1.hashCode(), f2.hashCode());
    }

    /**
     * Test that an object has a constant hashcode.
     * <p>
     * An unchanged object should always have the same hashcode within
     * any lifetime within an instance of the JVM.
     * <p>
     * The default behavior for the {@code hashCode()} method is to return
     * the memory address of the object instance. Obviously this can change
     * between runs of a program, but would be a constant through the
     * duration of each run.
     */
    @Test public void testSameObjectAlwaysSameHashCode() {
    	int h1 = f1.hashCode();
    	int h2 = f1.hashCode();
    	assertEquals(h1, h2);
    }

	@Test public void testAddition() {
		assertEquals(threeFifths, oneFifth.add(twoFifths));
	}

	@Test public void testSubtraction() {
		assertEquals(twoFifths, threeFifths.sub(oneFifth));
	}

	@Test public void testMultiplication() {
		assertEquals(new Fraction(6,25), threeFifths.mul(twoFifths));
	}

	@Test public void testDivision() {
		assertEquals(new Fraction(3,2), threeFifths.div(twoFifths));
		assertEquals(new Fraction(1,1), oneHalf.div(oneHalf));
	}
}

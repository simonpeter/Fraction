/*
 * FractionTest.java
 */

package org.simonpeter.examples.fraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * JUnit tests for {@link Fraction}.
 * <p>
 * In reality, I almost never comment my unit test methods. If the methods
 * are short enough and have descriptive names, then they really do document
 * themselves. These are teaching tests, so the normal rules do not apply
 * and over-explanation is likely good.
 *
 * @author  Simon Peter Chappell
 * @version 20100822
 * @see     org.simonpeter.examples.fraction.Fraction
 */
public class FractionTest {

	private static Fraction f1, f2, f3, f4;
	private static Fraction oneFifth, twoFifths, threeFifths;
	private static Fraction oneHalf, twoFourths, threeSixths;

    /**
     * This method runs before each test and allows us to
     * create a couple of standard objects for the tests.
     */
	@BeforeClass public static void beforeEverything() {
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
	@AfterClass public static void afterEverything() {
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
	 * <p>
	 * None of these should throw exceptions, so if they do, JUnit will
	 * catch them and alert us to the fact that we have a problem.
	 * <p>
	 * I'm using a language feature of Java where you don't have to
	 * use the return value from a method. In this instance, I only want
	 * to create a fraction and be sure that it didn't throw an exception.
	 */
	@Test public void testConstructorWithGoodValues() {
		new Fraction(1, 3);
		new Fraction(3, 2);
		new Fraction(1, 1);
	}

	/**
	 * Fractions may not have a zero denominator.
	 * <p>
	 * In this case I expect to see an exception, specifically an
	 * {@code IllegalArgumentException} thrown when attempting to
	 * create a fraction with a zero value denominator.
	 * <p>
	 * Think of it as not failing is failing, for this test.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithZeroDenominator() {
		new Fraction(1, 0);
	}

	/**
	 * Test the accessors of the object.
	 * <p>
	 * As this is an immutable object, there are no setter methods, but
	 * we are still wise to test the getters. I used to test them all
	 * individually, but these days I test all the accessors in a single
	 * method to emphasize their similar purpose.
	 */
	@Test public void testAccessors() {
		assertEquals(1, f1.getNumerator());
		assertEquals(2, f1.getDenominator());
	}

	/**
	 * Test that reduced fractions are equal.
	 * <p>
	 * This is where meaningful variable names help to make the tests
	 * more clear. Here we are testing that one half is equal to two
	 * fourths reduced. As we don't write tests for tests, the best way
	 * to make our tests correct is to keep them short and easy to read.
	 */
	@Test public void testReduce() {
		assertEquals(oneHalf, twoFourths.reduce());
		assertEquals(oneHalf, threeSixths.reduce());
	}

	/**
	 * Test that numerically equal fractions are equivalent.
	 * <p>
	 * We test at least one actually equal fraction combination.
	 * And then we test a couple more fraction combinations, where
	 * one is known to be equivalent and one is known not to be.
	 */
	@Test public void testEquivalent() {
		assertTrue(oneHalf.equivalent(f1));
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

    /**
     * Test fraction addition.
     * <p>
     * I had originally written this one using {@code assertEquals},
     * but I think that it reads better this way: we are asserting that
     * it is a true statement that three fifths is equal to the sum of
     * one fifth and two fifths. Again, descriptive variable names help.
     */
	@Test public void testAddition() {
		assertTrue(threeFifths.equals(oneFifth.add(twoFifths)));
	}

	/**
	 * Test fraction subtraction.
	 * <p>
	 * Using the same approach in our test as we did testing addition.
	 */
	@Test public void testSubtraction() {
		assertTrue(twoFifths.equals(threeFifths.sub(oneFifth)));
	}

	/**
	 * Test fraction multiplication.
	 * <p>
	 * Using the same approach in our test as we did testing addition.
	 * <p>
	 * Although, notice that I changed the unnamed fraction (6/25) to
	 * be at the end of the test line, as I felt it read better.
	 */
	@Test public void testMultiplication() {
		assertTrue(threeFifths.mul(twoFifths).equals(new Fraction(6,25)));
	}

	/**
	 * Test fraction division.
	 * <p>
	 * Using the same approach in our test as we did testing addition
	 * and with the unnamed fractions at the end of the lines as we did
	 * in our testing multiplication.
	 */
	@Test public void testDivision() {
		assertTrue(threeFifths.div(twoFifths).equals(new Fraction(3,2)));
		assertTrue(oneHalf.div(oneHalf).equals(new Fraction(1,1)));
	}
}

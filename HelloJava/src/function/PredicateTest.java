package function;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateTest {
	public static void main(String[] args) {
		Predicate<Integer> p1 = i -> i >= 0;
		System.out.println("p1.test(1) = " + p1.test(1));
		System.out.println("p1.test(-1) = " + p1.test(-1));
		
		Predicate<Integer> p2 = i -> i < 0;
		System.out.println("p2.test(1) = " + p2.test(1));
		System.out.println("p2.test(-1) = " + p2.test(-1));
		
		Predicate<Integer> p3 = p1.or(p2);
		System.out.println("p3.test(1) = " + p3.test(1));
		System.out.println("p3.test(-1) = " + p3.test(-1));

		Predicate<Integer> p4 = p1.and(p2);
		System.out.println("p4.test(1) = " + p4.test(1));
		System.out.println("p4.test(-1) = " + p4.test(-1));

		Predicate<Integer> p5 = i -> i == 0;
		System.out.println("p5.test(1) = " + p5.test(1));
		System.out.println("p5.test(0) = " + p5.test(0));
		
		Predicate<Integer> p5n = p5.negate();
		System.out.println("p5n.test(1) = " + p5n.test(1));
		System.out.println("p5n.test(0) = " + p5n.test(0));
		
		Predicate<Integer> p6 = Predicate.isEqual(0);
		System.out.println("p6.test(1) = " + p6.test(1));
		System.out.println("p6.test(0) = " + p6.test(0));
		
		BiPredicate<Integer, String> bp1 = (i, s) -> i > 0 && s != null && s.length() > 0;
		System.out.println("bp1.test(1, abc) = " + bp1.test(1, "abc"));
		System.out.println("bp1.test(0, abc) = " + bp1.test(0, "abc"));
		System.out.println("bp1.test(1, '') = " + bp1.test(1, ""));
	}
}

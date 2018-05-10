package thinkInJava.chapter2;

public class Exercise2 {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("need 3 parmeters!");
		} else {
			for (int i = 0; i < 3; i++) {
				System.out.println("parmeter " + i + " is " + args[i]);
			}
		}
	}
}

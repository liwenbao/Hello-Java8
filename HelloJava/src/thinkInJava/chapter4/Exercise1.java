package thinkInJava.chapter4;

public class Exercise1 {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ClassA a;
		/* Exercise 1 */
		a = new ClassA();
		/* Exercise 2 */
		a = new ClassA("abc");
		/* Exercise 3 */
		ClassA[] aList = new ClassA[10];
		/* Exercise 4 */
		aList[0] = new ClassA();
	}
}

class ClassA{
	public ClassA() {
		System.out.println("创建了一个ClassA的对象。");
	}
	
	public ClassA(String s) {
		System.out.println("创建了一个ClassA的对象。使用参数" + s);
	}
}

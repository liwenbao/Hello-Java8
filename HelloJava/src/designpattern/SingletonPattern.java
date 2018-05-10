package designpattern;

/*
 * 单例模式
 */
public class SingletonPattern {
	public static void main(String[] args) {
		Singleton1 s11 = Singleton1.getInstance();
		Singleton1 s12 = Singleton1.getInstance();
		System.out.println(s11 == s12);
		System.out.println(Singleton1.GetInstanceNumber());

		Singleton2 s21 = Singleton2.getInstance();
		Singleton2 s22 = Singleton2.getInstance();
		System.out.println(s21 == s22);
		System.out.println(Singleton2.GetInstanceNumber());
		
	}
}

/*
 * 实现方式一
 * 延迟加载：否
 * 线程安全：是
 */
class Singleton1{
	
	private static Singleton1 instance = new Singleton1();
	
	private static int INSTANCENUM;
	
	private Singleton1() {INSTANCENUM++;}
	
	public static Singleton1 getInstance() {
		return instance;
	}
	
	public static int GetInstanceNumber() {
		return INSTANCENUM;
	}
}

/*
 * 实现方式一
 * 延迟加载：是
 * 线程安全：是
 */
class Singleton2{
	
	private static class Singleton2Holder {
		private static final Singleton2 INSTANCE = new Singleton2();
	}
	
	private static int INSTANCENUM;
	
	private Singleton2() {INSTANCENUM++;}
	
	public static Singleton2 getInstance() {
		return Singleton2Holder.INSTANCE;
	}
	
	public static int GetInstanceNumber() {
		return INSTANCENUM;
	}
}

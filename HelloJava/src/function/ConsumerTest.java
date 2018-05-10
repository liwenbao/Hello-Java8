package function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerTest {
	public static void main(String[] args) {
		Consumer<Obj> c1 = o -> o.output();
		Obj o1 = new Obj(1, "o1");
		Obj o2 = new Obj(2, "o2");
		c1.accept(o1);
		c1.accept(o2);
		
		BiConsumer<Obj, String> c2 = (o, s) -> o.output(s);
		c2.accept(o1, "c2o1");
		c2.accept(o2, "c2o2");
	}
}

class Obj {
	private int id;
	private String name;
	
	public Obj(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("ID:%s Name:%s", id, name);
	}
	
	public void output() {
		System.out.println(this.toString());
	}
	
	public void output(String msg) {
		System.out.println(this.toString() + " 第二个参数是：" + msg);
	}
}

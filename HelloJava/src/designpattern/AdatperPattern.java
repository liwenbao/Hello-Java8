package designpattern;

/*
 * 适配器模式
 * 适用场景：新旧版本接口的适配；新旧版本类型定义的适配。
 * 与装饰器较为类似，根本区别在于装饰器装饰前后接口不变，适配器适配前后接口发生了改变。
 */
public class AdatperPattern {
	public static void main(String[] args) {
		Mobile mobile = new Mobile();
		mobile.charge(new Voltage5Adapter(new Voltage220()));
	}
}

class Voltage220{
	public void output() {
		System.out.println("输出220V电压。");
	}
}

interface Voltage5{
	void output();
}

class Mobile{
	void charge(Voltage5 vol) {
		vol.output();
		System.out.println("开始使用5V电压充电。");
	}
}

class Voltage5Adapter implements Voltage5{
	
	private Voltage220 vol220;
	
	Voltage5Adapter(Voltage220 vol){
		this.vol220 = vol;
	}

	@Override
	public void output() {
		this.vol220.output();
		System.out.println("将220V电压转换为5V电压。");
		System.out.println("适配器输出5V电压。");
	}
}

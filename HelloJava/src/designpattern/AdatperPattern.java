package designpattern;

/*
 * ������ģʽ
 * ���ó������¾ɰ汾�ӿڵ����䣻�¾ɰ汾���Ͷ�������䡣
 * ��װ������Ϊ���ƣ�������������װ����װ��ǰ��ӿڲ��䣬����������ǰ��ӿڷ����˸ı䡣
 */
public class AdatperPattern {
	public static void main(String[] args) {
		Mobile mobile = new Mobile();
		mobile.charge(new Voltage5Adapter(new Voltage220()));
	}
}

class Voltage220{
	public void output() {
		System.out.println("���220V��ѹ��");
	}
}

interface Voltage5{
	void output();
}

class Mobile{
	void charge(Voltage5 vol) {
		vol.output();
		System.out.println("��ʼʹ��5V��ѹ��硣");
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
		System.out.println("��220V��ѹת��Ϊ5V��ѹ��");
		System.out.println("���������5V��ѹ��");
	}
}
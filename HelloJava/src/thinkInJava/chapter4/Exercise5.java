package thinkInJava.chapter4;

public class Exercise5 {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("need one argument!");
			return;
		}
		
		for (String s : args) {
			System.out.println(s);
		}
		
		while(!Chair.f) {
			new Chair();
			new String("this is a unuse string");
		}
		
		System.out.println("�������������������" + Chair.created + "��������" + Chair.finalized);
		
		if (args[0].equals("after")) {
			System.out.println("gc():");
			System.gc();
			System.out.println("runFinalization():");
			System.runFinalization();
		}
		
		System.out.println("bye!");
		if(args[0].equals("before"))
			System.runFinalizersOnExit(true);
	}
}

class Chair{
	static boolean gcrun = false , f = false;
	static int created = 0 , finalized = 0;
	int id;
	
	Chair(){
		id = ++created;
		if (id == 47) {
			System.out.println("�����˵�47������");
		}
	}
	
	protected void finalize() {
		if (!gcrun) {
			gcrun = true;
			System.out.println("��ʼ���ж�����գ���ʱ������" + created + "������");
		}
		if (id >= 47 && !f) {
			f = true;
			System.out.println("���յ�47������ֹͣ������");
		}
		finalized ++;
		if (finalized >= created) {
			System.out.println("���ж���������ա�");
		}
	}
}
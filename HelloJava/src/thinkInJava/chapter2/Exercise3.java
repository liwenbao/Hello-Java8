package thinkInJava.chapter2;

import java.util.Properties;

/** JavaDoc������.
 * ���ڴ�ӡϵͳ����״̬��
 * @author liwenbao
 * @version 1.0
 */
public class Exercise3 {
	/** ������ڷ�����
	 * ��ӡϵͳ����״̬.
	 * @param args ���������б�
	 */
	public static void main(String[] args) {
		System.out.println(new java.util.Date());
		Properties p = System.getProperties();
		p.list(System.out);
		Runtime r = Runtime.getRuntime();
		System.out.println("totle memory: " + r.totalMemory() + ", free memory: " + r.freeMemory());
	}
}

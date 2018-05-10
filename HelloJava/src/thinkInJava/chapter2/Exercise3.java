package thinkInJava.chapter2;

import java.util.Properties;

/** JavaDoc测试类.
 * 用于打印系统运行状态。
 * @author liwenbao
 * @version 1.0
 */
public class Exercise3 {
	/** 程序入口方法。
	 * 打印系统运行状态.
	 * @param args 启动参数列表
	 */
	public static void main(String[] args) {
		System.out.println(new java.util.Date());
		Properties p = System.getProperties();
		p.list(System.out);
		Runtime r = Runtime.getRuntime();
		System.out.println("totle memory: " + r.totalMemory() + ", free memory: " + r.freeMemory());
	}
}

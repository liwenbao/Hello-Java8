package designpattern;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/*
 * ��Ԫģʽ������������ģʽ
 * ���ڴ����ظ����ֵĶ��󣬽�����״̬���뵽����֮�⣬�Ӷ����������Խ�ʡ�ڴ濪����
 * ʹ�ó�������ʽ�﷨���еĺ�������
 */
public class Flyweight {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++) {
			new FunctionNode("abs", new DataNode(-1d)).calc();
			new FunctionNode("max", new DataNode(-1d), new DataNode(-0.5d)).calc();
		}
		
	}
}

interface Function{
	String getName();
	Object calc(Object...args);
}

class FunctionFactory{
	
	static ConcurrentHashMap<String, Function> FUNCTIONS = new ConcurrentHashMap<String, Function>();
	
	static Function getFunction(String funcName) throws Exception {
		Function oldFunc = FUNCTIONS.get(funcName);
		if (oldFunc == null) {
			Function newFunc = createFunciton(funcName);
			//ʹ��putIfAbsent�ϸ�֤ÿ����������ֻʹ��ͬһ�����󡣣����´����˶�����󡣣�
			oldFunc = FUNCTIONS.putIfAbsent(funcName, newFunc);
			if (oldFunc == null) {
				oldFunc = newFunc;
			}
		}
		System.out.println(funcName + "�� " + oldFunc);
		return oldFunc;
	}
	
	private static Function createFunciton(String funcName) throws Exception {
		switch (funcName) {
		case "abs":
			return new Abs();
		case "max":
			return new Max();
		default:
			throw new Exception("unkown function name: " + funcName);
		}
	}
}

class FunctionNode implements CalcNode{

	private Function function;
	
	private CalcNode[] args;

	public FunctionNode(String funcName, CalcNode...args) throws Exception {
		function = FunctionFactory.getFunction(funcName);
		this.args = args;
	}
	
	@Override
	public Object calc() {
		return function.calc((Arrays.asList(this.args)).stream().map(n -> n.calc()).toArray());
	}
	
}

class Abs implements Function {
	
	@Override
	public Object calc(Object...args) {
		double args0 = (double)args[0];
		return Math.abs(args0);
	}

	@Override
	public String getName() {
		return "abs";
	}
	
}

class Max implements Function {
	
	@Override
	public Object calc(Object...args) {
		double args0 = (double)args[0];
		double args1 = (double)args[1];
		return Math.max(args0, args1);
	}

	@Override
	public String getName() {
		return "max";
	}
	
}
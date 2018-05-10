package designpattern;

import java.util.Arrays;

/*
 * ���ģʽ����һ��I���Ұ����ܶ�I����������ʾ����Ͳ��ֵĲ�Σ��γ���״��ϵ��
 * ���ó�������ʽ�﷨������Ա��֯�ṹ�ȡ�
 */
public class CompositePattern {
	public static void main(String[] args) {
		// (1+3)/(2+2)*100
		Operation add = params -> (int) params[0] + (int) params[1];
		Operation muti = params -> (int) params[0] * (int) params[1];
		Operation dev = params -> (int) params[0] / (int) params[1];

		OperationNode node = new OperationNode(muti,
				new OperationNode(dev, new OperationNode(add, new DataNode(1), new DataNode(3)),
						new OperationNode(add, new DataNode(2), new DataNode(2))),
				new DataNode(100));
		
		Expression exp1 = new Expression(node);
		System.out.println("exp1 result is " + exp1.calc());

		// ? = (1+3)/(2+2)*100
		DataNode obj = new DataNode(0);
		Expression exp2 = new Expression(new AssignNode(obj, exp1));
		System.out.println("exp2 result is " + exp2.calc());
		System.out.println("obj val is " + obj.calc());
	}
}

interface CalcNode {
	Object calc();
}

class DataNode implements CalcNode {

	private Object val;

	public DataNode(Object val) {
		this.val = val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public Object getVal() {
		return this.val;
	}

	@Override
	public Object calc() {
		return this.val;
	}
}

class OperationNode implements CalcNode {

	private Operation operation;

	protected CalcNode[] arguments;

	public OperationNode(Operation op, CalcNode... args) {
		this.operation = op;
		this.arguments = args;
	}

	@Override
	public Object calc() {
		return operation.Operate((Arrays.asList(this.arguments)).stream().map(CalcNode::calc).toArray());
	}

}

class AssignNode extends OperationNode {
	
	public AssignNode(CalcNode...args) {
		super(null, args);
	}
	
	private DataNode getLeftNode() {
		return (DataNode)this.arguments[0];
	}
	
	private CalcNode getRigthNode() {
		return this.arguments[1];
	}

	@Override
	public Object calc() {
		Object val = this.getRigthNode().calc();
		this.getLeftNode().setVal(val);
		return val;
	}

}

interface Operation {
	Object Operate(Object... args);
}

class Expression implements CalcNode {

	private CalcNode rootNode;

	public Expression(CalcNode rn) {
		this.rootNode = rn;
	}

	@Override
	public Object calc() {
		return this.rootNode.calc();
	}

}
package designpattern;

/*
 * 桥接模式
 * 把抽象(Abstraction)与行为实现(Implementation)分离开来，从而可以保持各部分的独立性以及应对他们的功能扩展。
 * 适用场景：一个复杂行为内包含多个维度，多个维度都可能发生变化。
 */
public class BridgePattern {
	public static void main(String[] args) {
		
		//最初在数据库存储数据，只有JQ语法，使用树来组织公式网络。
		FormulaNet net = new TreeFormulaNet(new DBDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		
		//后来需要支持Excel语法。此时只需要实现一个新的ExcelFormulaSyntax即可。
		net = new TreeFormulaNet(new DBDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
		
		//需要支持文件数据存储。此时只需要实现一个新的FileDataAccessor即可。
		//此时如果没有桥接，则需要对多种语法分别扩展文件存储，造成类爆炸。
		net = new TreeFormulaNet(new FileDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		net = new TreeFormulaNet(new FileDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
		
		//需要验证一种新的公式网络组织形式ArrayFormulaNet。
		//此时如果没有桥接，则需要对多种数据访问器、多种语法分别扩展，造成类爆炸。
		net = new ArrayFormulaNet(new DBDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		net = new ArrayFormulaNet(new DBDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
		net = new ArrayFormulaNet(new FileDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		net = new ArrayFormulaNet(new FileDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
	}
}

/*
 * 数据访问器接口。可以通过实现该接口来支持新的数据访问形式，而不用关注公式语法和执行网络的实现形式。
 */
interface DataAccessor{
	void readData();
	void writeData();
}

/*
 * 公式语法接口。可以通过实现该接口来支持新的语法形式，而不用关注数据访问和执行网络的实现形式。
 */
interface FormulaSyntax{
	void Parse();
}

/*
 * 公式网络基类。通过数据访问接口桥接不同的数据访问实现。通过公式语法接口桥接不同的公式语法实现。
 * 以通过实现该类来支持新的公式网络，而不用关注数据访问和公式语法的实现形式。
 */
abstract class FormulaNet{
	protected DataAccessor dataAccess;
	protected FormulaSyntax formulaSyntax;
	protected FormulaNet(DataAccessor da, FormulaSyntax fs) {
		dataAccess = da;
		formulaSyntax = fs;
	}
	abstract void Calc();
}

class TreeFormulaNet extends FormulaNet{

	protected TreeFormulaNet(DataAccessor da, FormulaSyntax fs) {
		super(da, fs);
	}

	@Override
	void Calc() {
		System.out.println("====================================");
		formulaSyntax.Parse();
		dataAccess.readData();
		System.out.println("Tree公式网络进行运行。");
		dataAccess.writeData();
	}
	
}

class ArrayFormulaNet extends FormulaNet{

	protected ArrayFormulaNet(DataAccessor da, FormulaSyntax fs) {
		super(da, fs);
	}

	@Override
	void Calc() {
		System.out.println("====================================");
		formulaSyntax.Parse();
		dataAccess.readData();
		System.out.println("Array公式网络进行运行。");
		dataAccess.writeData();
	}
	
}

class DBDataAccessor implements DataAccessor{

	@Override
	public void readData() {
		System.out.println("DB数据访问器读取数据。");
	}

	@Override
	public void writeData() {
		System.out.println("DB数据访问器回写数据。");
	}
	
}

class FileDataAccessor implements DataAccessor{

	@Override
	public void readData() {
		System.out.println("File数据访问器读取数据。");
	}

	@Override
	public void writeData() {
		System.out.println("File数据访问器回写数据。");
	}
	
}

class JQFormulaSyntax implements FormulaSyntax{

	@Override
	public void Parse() {
		System.out.println("JQ语法解析公式。");
	}
	
}

class ExcelFormulaSyntax implements FormulaSyntax{

	@Override
	public void Parse() {
		System.out.println("Excel语法解析公式。");
	}
	
}

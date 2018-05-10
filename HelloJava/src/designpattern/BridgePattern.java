package designpattern;

/*
 * �Ž�ģʽ
 * �ѳ���(Abstraction)����Ϊʵ��(Implementation)���뿪�����Ӷ����Ա��ָ����ֵĶ������Լ�Ӧ�����ǵĹ�����չ��
 * ���ó�����һ��������Ϊ�ڰ������ά�ȣ����ά�ȶ����ܷ����仯��
 */
public class BridgePattern {
	public static void main(String[] args) {
		
		//��������ݿ�洢���ݣ�ֻ��JQ�﷨��ʹ��������֯��ʽ���硣
		FormulaNet net = new TreeFormulaNet(new DBDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		
		//������Ҫ֧��Excel�﷨����ʱֻ��Ҫʵ��һ���µ�ExcelFormulaSyntax���ɡ�
		net = new TreeFormulaNet(new DBDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
		
		//��Ҫ֧���ļ����ݴ洢����ʱֻ��Ҫʵ��һ���µ�FileDataAccessor���ɡ�
		//��ʱ���û���Žӣ�����Ҫ�Զ����﷨�ֱ���չ�ļ��洢������౬ը��
		net = new TreeFormulaNet(new FileDataAccessor(), new JQFormulaSyntax());
		net.Calc();
		net = new TreeFormulaNet(new FileDataAccessor(), new ExcelFormulaSyntax());
		net.Calc();
		
		//��Ҫ��֤һ���µĹ�ʽ������֯��ʽArrayFormulaNet��
		//��ʱ���û���Žӣ�����Ҫ�Զ������ݷ������������﷨�ֱ���չ������౬ը��
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
 * ���ݷ������ӿڡ�����ͨ��ʵ�ָýӿ���֧���µ����ݷ�����ʽ�������ù�ע��ʽ�﷨��ִ�������ʵ����ʽ��
 */
interface DataAccessor{
	void readData();
	void writeData();
}

/*
 * ��ʽ�﷨�ӿڡ�����ͨ��ʵ�ָýӿ���֧���µ��﷨��ʽ�������ù�ע���ݷ��ʺ�ִ�������ʵ����ʽ��
 */
interface FormulaSyntax{
	void Parse();
}

/*
 * ��ʽ������ࡣͨ�����ݷ��ʽӿ��ŽӲ�ͬ�����ݷ���ʵ�֡�ͨ����ʽ�﷨�ӿ��ŽӲ�ͬ�Ĺ�ʽ�﷨ʵ�֡�
 * ��ͨ��ʵ�ָ�����֧���µĹ�ʽ���磬�����ù�ע���ݷ��ʺ͹�ʽ�﷨��ʵ����ʽ��
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
		System.out.println("Tree��ʽ����������С�");
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
		System.out.println("Array��ʽ����������С�");
		dataAccess.writeData();
	}
	
}

class DBDataAccessor implements DataAccessor{

	@Override
	public void readData() {
		System.out.println("DB���ݷ�������ȡ���ݡ�");
	}

	@Override
	public void writeData() {
		System.out.println("DB���ݷ�������д���ݡ�");
	}
	
}

class FileDataAccessor implements DataAccessor{

	@Override
	public void readData() {
		System.out.println("File���ݷ�������ȡ���ݡ�");
	}

	@Override
	public void writeData() {
		System.out.println("File���ݷ�������д���ݡ�");
	}
	
}

class JQFormulaSyntax implements FormulaSyntax{

	@Override
	public void Parse() {
		System.out.println("JQ�﷨������ʽ��");
	}
	
}

class ExcelFormulaSyntax implements FormulaSyntax{

	@Override
	public void Parse() {
		System.out.println("Excel�﷨������ʽ��");
	}
	
}
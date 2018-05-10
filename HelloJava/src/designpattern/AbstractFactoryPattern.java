/*
 * ���󹤳�ģʽ
 * ���ó������������ϵͳ��һ����ص��ļ��������������ݿ�������sql��������ɵ�һ���Ʒ�ȡ�
 */
package designpattern;

public class AbstractFactoryPattern {
	public static void main(String[] args) throws Exception{
		IOOperator op;
		try{
			op = new IOOperator();
		}catch(Exception e) {
			throw e;
		}
		op.CreateFile("");
		op.CreateDirectory("");
	}
}

/*
 * Business use
 */
class IOOperator {

	private IOOperatorFactory operatorFactory;

	IOOperator() throws Exception {
		switch (System.getProperty("sun.desktop")) {
		case "windows":
			operatorFactory = new WindowsIOOperatorFactory();
			break;
		case "unix":
		case "linux":
			operatorFactory = new UnixIOOperatorFactory();
			break;
		default:
			throw new Exception("unknow sun.desktop");
		}
	}

	void CreateFile(String path) {
		operatorFactory.CreateFileOperator().CreateFile(path);
	}

	void CreateDirectory(String path) {
		operatorFactory.CreateDirectoryOperator().CreateDirectory(path);
	}
}

// һ���ǳ��󹤳��Ķ����ʵ�ֲ��֡�

/*
 * Product A
 */
abstract class FileOperator {
	abstract void CreateFile(String path);
}

/*
 * Product A1
 */
class WindowsFileOperator extends FileOperator {

	@Override
	void CreateFile(String path) {
		System.out.println("create file in windows.");
	}

}

/*
 * Product A2
 */
class UnixFileOperator extends FileOperator {

	@Override
	void CreateFile(String path) {
		System.out.println("create file in unix/linux.");
	}

}

/*
 * Product B
 */
abstract class DirectoryOperator {
	abstract void CreateDirectory(String path);
}

/*
 * Product B1
 */
class WindowsDirectoryOperator extends DirectoryOperator {

	@Override
	void CreateDirectory(String path) {
		System.out.println("create directory in windows.");
	}

}

/*
 * Product B2
 */
class UnixDirectoryOperator extends DirectoryOperator {

	@Override
	void CreateDirectory(String path) {
		System.out.println("create directory in unix/linux.");
	}

}

/*
 * Abstract Factory
 */
abstract class IOOperatorFactory {

	abstract FileOperator CreateFileOperator();

	abstract DirectoryOperator CreateDirectoryOperator();
}

/*
 * Factory1 used to create product for Windows.
 */
class WindowsIOOperatorFactory extends IOOperatorFactory {

	@Override
	FileOperator CreateFileOperator() {
		return new WindowsFileOperator();
	}

	@Override
	DirectoryOperator CreateDirectoryOperator() {
		return new WindowsDirectoryOperator();
	}

}

/*
 * Factory2 used to create product for Unix.
 */
class UnixIOOperatorFactory extends IOOperatorFactory {

	@Override
	FileOperator CreateFileOperator() {
		return new UnixFileOperator();
	}

	@Override
	DirectoryOperator CreateDirectoryOperator() {
		return new UnixDirectoryOperator();
	}

}
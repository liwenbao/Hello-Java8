/*
 * 工厂模式示例
 * 适用场景：创建不同的日志客户端；创建不同的数据库驱动；对接不同版本的API等。
 */
package designpattern;

public class FactoryPattern {
	public static void main(String[] args) {
		Logger logger;
		try {
			logger = LoggerFactory.CreateLogger("FileLogger");
			logger.Log("");

			logger = LoggerFactory.CreateLogger("DataBaseLogger");
			logger.Log("");

			logger = LoggerFactory.CreateLogger("ServiceLogger");
			logger.Log("");

			logger = LoggerFactory.CreateLogger("unknow");
			logger.Log("");
		} catch (Exception e) {
			System.out.println("create logger error. " + e.getMessage());
		}
	}
}

interface Logger {
	void Log(String txt);
}

final class LoggerFactory {
	static Logger CreateLogger(String loggerType) throws Exception {
		switch (loggerType) {
		case "FileLogger":
			return new FileLogger();
		case "DataBaseLogger":
			return new DataBaseLogger();
		case "ServiceLogger":
			return new ServiceLogger();
		default:
			throw new Exception("unknow logger type: " + loggerType);
		}
	}
}

class FileLogger implements Logger {

	@Override
	public void Log(String txt) {
		// TODO Auto-generated method stub
		System.out.println("log content into file");
	}

}

class DataBaseLogger implements Logger {

	@Override
	public void Log(String txt) {
		// TODO Auto-generated method stub
		System.out.println("log content into DB");
	}

}

class ServiceLogger implements Logger {

	@Override
	public void Log(String txt) {
		// TODO Auto-generated method stub
		System.out.println("log content into remote service");
	}

}

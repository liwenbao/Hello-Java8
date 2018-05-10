package designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*
 * ����ģʽ
 * ��װ����ģʽ�ӽṹ�Ͽ�ʮ�����ƣ���������ʹ��Ŀ�ġ�װ����һ��Ϊ�˱�װ�ζ��������¹��ܣ�����һ�����ڿ��Ʊ������������Ϊ��
 */
public class ProxyPattern {
	public static void main(String[] args) {
		System.out.println("================��̬����===============");
		HttpServer server = new HttpServerProxy();
		server.get("/data");
		server.get("/data");
		server.get("/data");
		server.get("/data/1");
		server.get2("/data/1");
		System.out.println("================��̬����===============");
		DaynamicProxy proxy = new DaynamicProxy(new RealHttpServer());
		HttpServer serverProxy = proxy.getProxy();
		serverProxy.get("/data");
		serverProxy.get("/data");
		serverProxy.get("/data");
		serverProxy.get("/data/1");
		serverProxy.get2("/data/1");
	}
}

interface HttpServer{
	String get(String resource);
	String get2(String resource);
}

class RealHttpServer implements HttpServer{
	
	@Override
	public String get(String resource) {
		System.out.println("ʵ����ӦHTTP����������ԴΪ��" + resource);
		return "response";
	}

	@Override
	public String get2(String resource) {
		return get(resource);
	}
}

class HttpServerProxy implements HttpServer{
	
	private ConcurrentHashMap<String, String> cachedRequest = new ConcurrentHashMap<>();
	
	private RealHttpServer realServer = new RealHttpServer();
	
	@Override
	public String get(String resource) {
		String response = cachedRequest.get(resource);
		if (response != null) {
			System.out.println("����������ӦHTTP����������ԴΪ��" + resource);
			return response;
		}
		response = realServer.get(resource);
		cachedRequest.put(resource, response);
		return response;
	}

	@Override
	public String get2(String resource) {
		return get(resource);
	}
}

// �����Ƕ�̬����ʵ��
class DaynamicProxy implements InvocationHandler{
	
	private ConcurrentHashMap<String, String> cachedRequest = new ConcurrentHashMap<>();
	private Object target;
	
	public DaynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String cacheKey = String.join(",", Arrays.asList(args).stream().map(Object::toString).collect(Collectors.toList()));
		String response = cachedRequest.get(cacheKey);
		if (response != null) {
			System.out.println("����������ӦHTTP����������ԴΪ��" + cacheKey);
			return response;
		}
		response = (String) method.invoke(this.target, args);
		cachedRequest.put(cacheKey, response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
}
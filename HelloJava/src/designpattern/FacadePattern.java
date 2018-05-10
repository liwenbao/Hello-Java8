package designpattern;

/*
 * ���ģʽ
 * ��һ���ڲ�ϵͳ���ṩһ���µĽӿڣ��ͻ���ͨ���½ӿ���ʹ���ڲ�ϵͳ��
 * ���ó�����ͨ���Լ��Ļ������ӿڰ�װRedis��JDK�����·�װ��ʽϵͳȥ���Ժ�����֧�ֵȡ�
 */
public class FacadePattern {
	public static void main(String[] args) {
		SimpleCacheAccess cacheAccess = new SimpleCacheAccess();
		cacheAccess.put("key1", "val1");
		cacheAccess.get("key1");
		cacheAccess.getRedisClient().get("key2");	//͸�������ģʽ�������Դ������ֱ��ʹ���ڲ�ҵ��
	}
}

class RedisClient{
	
	public void put(String key, String val) {
		System.out.println("put���ݵ�redis����");
	}
	
	public void get(String key) {
		System.out.println("��redis����get���ݡ�");
	}
	
	public void add(String key) {
		System.out.println("�����õ�Add������");
	}
	
	public void del(String key) {
		System.out.println("�����õ�del������");
	}
}

class SimpleCacheAccess{
	
	private RedisClient redisClient = new RedisClient();
	
	public RedisClient getRedisClient() {
		return this.redisClient;
	}
	
	public void put(String key, String val) {
		redisClient.put(key, val);
	}
	
	public void get(String key) {
		redisClient.get(key);
	}
}

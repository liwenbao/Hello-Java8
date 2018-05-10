package designpattern;

/*
 * 外观模式
 * 对一套内部系统，提供一个新的接口，客户端通过新接口来使用内部系统。
 * 适用场景：通过自己的缓存服务接口包装Redis的JDK，重新封装公式系统去掉对函数的支持等。
 */
public class FacadePattern {
	public static void main(String[] args) {
		SimpleCacheAccess cacheAccess = new SimpleCacheAccess();
		cacheAccess.put("key1", "val1");
		cacheAccess.get("key1");
		cacheAccess.getRedisClient().get("key2");	//透明的外观模式。即可以从外观上直接使用内部业务。
	}
}

class RedisClient{
	
	public void put(String key, String val) {
		System.out.println("put数据到redis服务。");
	}
	
	public void get(String key) {
		System.out.println("从redis服务get数据。");
	}
	
	public void add(String key) {
		System.out.println("不常用的Add操作。");
	}
	
	public void del(String key) {
		System.out.println("不常用的del操作。");
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

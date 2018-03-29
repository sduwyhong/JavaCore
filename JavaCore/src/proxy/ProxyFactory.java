package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public class ProxyFactory{
	
	public static Object getProxy(
			Class<?> targetClass, DynamicProxyHandler handler) {
		return Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), handler);
	}
	
}

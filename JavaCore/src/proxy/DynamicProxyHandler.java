package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wyhong
 * @date 2018-3-28
 */
//JDK动态代理，要持有被代理对象，依赖接口，生成的代理对象只能由接口类型接收（接口的一个实现类）
public class DynamicProxyHandler implements InvocationHandler {
	
	Target target;
	
	public DynamicProxyHandler(Target target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		long start = System.currentTimeMillis();
		method.invoke(target, args);
		System.out.printf("cost count by dynamic proxy object:%dms", System.currentTimeMillis() - start);
		return null;
	}

}

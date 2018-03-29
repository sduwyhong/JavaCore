package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author wyhong
 * @date 2018-3-28
 */
//cglib代理，需要cglib.jar和asm.jar，能调用无接口的被代理对象
public class CglibCallback implements MethodInterceptor {

	@Override
	public Object intercept(Object target, Method method,
			Object[] args, MethodProxy proxy)
			throws Throwable {
		long start = System.currentTimeMillis();
		proxy.invokeSuper(target, args);
		System.out.printf("cost count by cglib interceptor:%dms", System.currentTimeMillis() - start);
		return null;
	}

}

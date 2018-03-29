package proxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author wyhong
 * @date 2018-3-28
 */
public class Test {

	public static void main(String[] args) {
		TargetProxyObject proxyObject = new TargetProxyObject();
		TargetWithoutInterfaces targetWithoutInterfaces = new TargetWithoutInterfaces();
//		testStaticProxy(proxyObject);
		testDynamicProxy(proxyObject);
//		testCglib(proxyObject);
//		testCglib(targetWithoutInterfaces);
	}

	private static void testCglib(Target target) {
		TargetWithoutInterfaces cglibProxy = 
				 (TargetWithoutInterfaces) Enhancer.create(target.getClass(), new CglibCallback());
		cglibProxy.myMethod();
	}

	private static void testDynamicProxy(Target target) {
		TargetInterface dynamicProxy = (TargetInterface) ProxyFactory.getProxy(target.getClass(), new DynamicProxyHandler(target));
		dynamicProxy.targetMethod_1();
	}

	private static void testStaticProxy(TargetProxyObject target) {
		StaticProxy staticProxy = new StaticProxy(target);
		staticProxy.targetMethod_2();		
	}
	
}

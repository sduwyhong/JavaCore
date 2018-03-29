package proxy;
/**
 * @author wyhong
 * @date 2018-3-28
 */
//静态代理，依赖接口，耦合度高
public class StaticProxy implements TargetInterface {

	TargetProxyObject proxyObject;
	
	public StaticProxy(TargetProxyObject _proxyObject){
		this.proxyObject = _proxyObject;
	}
	
	@Override
	public void targetMethod_1() {
		long start = System.currentTimeMillis();
		proxyObject.targetMethod_1();
		System.out.printf("cost count by static proxy object:%dms", System.currentTimeMillis() - start);
	}

	@Override
	public void targetMethod_2() {
		long start = System.currentTimeMillis();
		proxyObject.targetMethod_2();
		System.out.printf("cost count by static proxy object:%dms", System.currentTimeMillis() - start);
	}

}

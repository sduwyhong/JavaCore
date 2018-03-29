package proxy;

public class TargetProxyObject extends Target implements TargetInterface {

	@Override
	public void targetMethod_1() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I am the method 1 that proxy class needs to handle!");
	}

	@Override
	public void targetMethod_2() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I am the method 2 that proxy class needs to handle!");
	}

}

package proxy;
/**
 * @author wyhong
 * @date 2018-3-28
 */
public class TargetWithoutInterfaces extends Target {

	public void myMethod(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I am the method own by myself that proxy class needs to handle!");
	}
	
}

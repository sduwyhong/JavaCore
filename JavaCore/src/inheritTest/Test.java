package inheritTest;

public class Test extends Father {
	//	如果说父类中有这个属性跟方法，子类有重写过，那么调用的是子类中的属性跟方法。
//	如果父类中没有这个属性跟方法，那么子类调用就会出错。
//	如果父类有这个属性跟方法，而子类没有，则调用的是父类的属性跟方法。
	public void func1(){
		System.out.println("func1:son");
	}
	public void func_son(){
		System.out.println("only-son");
	}
//	Implicit super constructor Father() is undefined. Must explicitly invoke another constructor
//	隐式的超级构造函数father()是未定义的。必须显式调用另一个构造函数
	public Test(int n){
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Father father = new Test(2);
		father.func1();
		father.func_father();
	}

}
class Father{
	
//	public Father(int n) {
//		
//	}
	public void func1(){
		System.out.println("func1:father");
	}
	protected void func_father(){
		System.out.println("only-father");
	}
}

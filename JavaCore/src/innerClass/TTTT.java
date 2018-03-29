package innerClass;

import innerClass.Outer.sInner;

public class TTTT {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//静态方法中只能操作来自外部的静态变量或方法
		//静态内部类，和普通new一样
		Outer.sInner sInner = new sInner();
	}
	
	public void newInner(){
		//非静态内部类，需要新建对象去new
		Outer o = new Outer();
		Outer.Inner inner = o.new Inner();
	}
}
class Outer{
	public void newInner(){
		//外部类内部新建普通内部类，由于已经有对象，直接new
		new  Inner();
	}
	class Inner{}
	static class sInner{}
}